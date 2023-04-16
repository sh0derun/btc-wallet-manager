package com.shaderun.api;

import java.time.OffsetDateTime;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

public record Transaction(
        String datetime,
        Double amount
) implements Comparable<Transaction>{
    @Override
    public int compareTo(Transaction o) {
        return OffsetDateTime.timeLineOrder().compare(OffsetDateTime.parse(this.datetime, ISO_OFFSET_DATE_TIME), OffsetDateTime.parse(o.datetime, ISO_OFFSET_DATE_TIME));
    }
}
