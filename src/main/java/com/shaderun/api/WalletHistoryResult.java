package com.shaderun.api;

import java.util.Objects;
import java.util.Set;

public class WalletHistoryResult{
    private Set<Transaction> transactions;

    public WalletHistoryResult(){}
    public WalletHistoryResult(Set<Transaction> transactions){
        this.transactions = transactions;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "WalletHistoryResult{" +
                "transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletHistoryResult that = (WalletHistoryResult) o;
        return Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions);
    }
}
