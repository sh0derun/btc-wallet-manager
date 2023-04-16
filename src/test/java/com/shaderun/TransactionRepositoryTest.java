package com.shaderun;

import com.shaderun.entity.TransactionEntity;
import com.shaderun.repository.BtcWalletTransactionsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

    @Autowired
    private BtcWalletTransactionsRepository transactionRepository;

    @Test
    void testFindByDateTimeBetween() {
        // Create two transactions with different date times
        OffsetDateTime dateTime1 = OffsetDateTime.of(2023, 3, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction1 = new TransactionEntity(1L, dateTime1, 100.0);
        transactionRepository.save(transaction1);

        OffsetDateTime dateTime2 = OffsetDateTime.of(2023, 3, 15, 0, 0, 0, 0, ZoneOffset.UTC);
        TransactionEntity transaction2 = new TransactionEntity(2L, dateTime2, 200.0);
        transactionRepository.save(transaction2);

        // Retrieve transactions between two date times
        OffsetDateTime start = OffsetDateTime.of(2023, 3, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(2023, 3, 31, 0, 0, 0, 0, ZoneOffset.UTC);

        List<TransactionEntity> transactions = transactionRepository.findByDateTimeBetween(start, end);

        // Verify that the correct transactions are returned
        List<TransactionEntity> expectedTransactions = Arrays.asList(
                new TransactionEntity(1L, dateTime1.withOffsetSameInstant(ZoneOffset.UTC), 100.0),
                new TransactionEntity(2L, dateTime2.withOffsetSameInstant(ZoneOffset.UTC), 200.0)
        );
        Assertions.assertThat(transactions).containsExactlyElementsOf(expectedTransactions);
    }
}