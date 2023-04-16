package com.shaderun.service;


import com.shaderun.api.HistoryRequestRange;
import com.shaderun.api.Result;
import com.shaderun.api.Transaction;
import com.shaderun.api.WalletHistoryResult;
import com.shaderun.entity.TransactionEntity;
import com.shaderun.exception.ApiWalletException;
import com.shaderun.repository.BtcWalletTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

@Service
public class BtcWalletTransactionsService {

    private BtcWalletTransactionsRepository btcRepository;

    @Autowired
    public BtcWalletTransactionsService(BtcWalletTransactionsRepository btcRepository){
        this.btcRepository = btcRepository;
    }

    public Result saveTransaction(Transaction transaction){
        Result result;

        try{
            OffsetDateTime date = OffsetDateTime.parse(transaction.datetime(), ISO_OFFSET_DATE_TIME);
            TransactionEntity transactionEntity = btcRepository.save(new TransactionEntity(date, transaction.amount()));
            result = new Result(HttpStatus.CREATED, "transaction created with id "+transactionEntity.getId());
        } catch(RuntimeException e){
            throw new ApiWalletException("Transaction cannot be created !");
        }

        return result;
    }

    public WalletHistoryResult getWalletHistory(HistoryRequestRange range) {
        WalletHistoryResult walletHistoryResult;

        try {
            OffsetDateTime start = OffsetDateTime.parse(range.startDatetime(), ISO_OFFSET_DATE_TIME);
            OffsetDateTime end = OffsetDateTime.parse(range.endDatetime(), ISO_OFFSET_DATE_TIME);
            List<TransactionEntity> transactions = btcRepository.findByDateTimeBetween(start, end);
            Map<OffsetDateTime, Double> hourlySums = new HashMap<>();

            for (TransactionEntity transaction : transactions) {
                OffsetDateTime hour = transaction.getDateTime().truncatedTo(ChronoUnit.HOURS);
                double amount = transaction.getAmount();
                hourlySums.compute(hour, (k, v) -> (v == null) ? amount : v + amount);
            }

            walletHistoryResult = new WalletHistoryResult(hourlySums.entrySet().stream()
                    .map(entry -> new Transaction(entry.getKey().toString(), entry.getValue()))
                    .collect(Collectors.toSet()));
        } catch(RuntimeException e){
            throw new ApiWalletException(e.getMessage());
        }

        return walletHistoryResult;
    }

}
