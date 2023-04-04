package com.shaderun.api;

import java.util.List;

public record WalletHistoryResult(
        List<Transaction> transactions
){}
