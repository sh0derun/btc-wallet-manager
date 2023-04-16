package com.shaderun.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class TransactionEntity {

    @Id
    @SequenceGenerator(name = "transaction_sequence_id", sequenceName = "transaction_sequence_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence_id")
    private Long id;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateTime;

    private Double amount;

    public TransactionEntity(){}

    public TransactionEntity(OffsetDateTime date, Double amount) {
        this.dateTime = date;
        this.amount = amount;
    }

    public TransactionEntity(Long id, OffsetDateTime date, Double amount) {
        this.id = id;
        this.dateTime = date;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime date) {
        this.dateTime = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id.equals(that.id) && dateTime.equals(that.dateTime) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, amount);
    }
}
