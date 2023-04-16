package com.shaderun;

import com.shaderun.api.HistoryRequestRange;
import com.shaderun.api.Result;
import com.shaderun.api.Transaction;
import com.shaderun.api.WalletHistoryResult;
import com.shaderun.entity.TransactionEntity;
import com.shaderun.repository.BtcWalletTransactionsRepository;
import com.shaderun.service.BtcWalletTransactionsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private BtcWalletTransactionsRepository transactionRepository;

    @InjectMocks
    private BtcWalletTransactionsService transactionsService;

    @Test
    void testSaveTransaction() {
        Transaction transaction = new Transaction("2023-03-01T16:00Z", 100.);
        TransactionEntity transactionEntity = new TransactionEntity(1L, OffsetDateTime.parse("2023-03-01T16:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME), 100.);

        ArgumentCaptor<TransactionEntity> entityArgumentCaptor = ArgumentCaptor.forClass(TransactionEntity.class);
        Mockito.when(transactionRepository.save(
                entityArgumentCaptor.capture())
        ).thenReturn(transactionEntity);

        Result actual = transactionsService.saveTransaction(transaction);

        Result expected = new Result(HttpStatus.CREATED, "transaction created with id "+transactionEntity.getId());

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetWalletHistoryResult() {
        OffsetDateTime dateTime1 = OffsetDateTime.of(2023, 3, 1, 16, 5, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction1 = new TransactionEntity(1L, dateTime1, 100.0);

        OffsetDateTime dateTime2 = OffsetDateTime.of(2023, 3, 1, 16, 30, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction2 = new TransactionEntity(2L, dateTime2, 100.0);

        OffsetDateTime dateTime3 = OffsetDateTime.of(2023, 3, 1, 16, 55, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction3 = new TransactionEntity(3L, dateTime3, 100.0);

        OffsetDateTime dateTime4 = OffsetDateTime.of(2023, 3, 1, 17, 5, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction4 = new TransactionEntity(4L, dateTime4, 100.0);

        OffsetDateTime dateTime5 = OffsetDateTime.of(2023, 3, 1, 17, 30, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction5 = new TransactionEntity(5L, dateTime5, 100.0);

        OffsetDateTime dateTime6 = OffsetDateTime.of(2023, 3, 1, 18, 5, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction6 = new TransactionEntity(6L, dateTime6, 100.0);

        ArgumentCaptor<OffsetDateTime> argumentCaptorStart = ArgumentCaptor.forClass(OffsetDateTime.class);
        ArgumentCaptor<OffsetDateTime> argumentCaptorEnd = ArgumentCaptor.forClass(OffsetDateTime.class);
        Mockito.when(transactionRepository.findByDateTimeBetween(
                argumentCaptorStart.capture(), argumentCaptorEnd.capture()
        )).thenReturn(List.of(transaction1,transaction2,transaction3,transaction4,transaction5,transaction6));

        OffsetDateTime start = OffsetDateTime.of(2023, 3, 1, 16, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(2023, 3, 1, 21, 0, 0, 0, ZoneOffset.UTC);
        HistoryRequestRange range = new HistoryRequestRange(start.toString(), end.toString());
        WalletHistoryResult walletHistoryResult =  transactionsService.getWalletHistory(range);

        WalletHistoryResult expected = new WalletHistoryResult(Set.of(
                new Transaction("2023-03-01T16:00Z", 300.0),
                new Transaction("2023-03-01T17:00Z", 200.0),
                new Transaction("2023-03-01T18:00Z", 100.0)
        ));

        Assertions.assertThat(walletHistoryResult).isEqualTo(expected);
        Assertions.assertThat(walletHistoryResult.getTransactions()).containsExactlyInAnyOrderElementsOf(expected.getTransactions());
    }
}