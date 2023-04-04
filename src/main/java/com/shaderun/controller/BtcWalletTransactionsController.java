package com.shaderun.controller;

import com.shaderun.api.HistoryRequestRange;
import com.shaderun.api.Result;
import com.shaderun.api.Transaction;
import com.shaderun.api.WalletHistoryResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BtcWalletTransactionsController {

    @PostMapping(value = "/save")
    public Result saveTransaction(Transaction transaction){
        return new Result("Created", "okay");
    }

    @GetMapping(value = "/")
    public WalletHistoryResult getHistoryTransactions(HistoryRequestRange range){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("2019-10-05T14:45:05+07:00", 10.0));
        transactions.add(new Transaction("2019-10-05T14:45:05+07:00", 20.0));
        transactions.add(new Transaction("2019-10-05T14:45:05+07:00", 30.0));
        return new WalletHistoryResult(transactions);
    }

}