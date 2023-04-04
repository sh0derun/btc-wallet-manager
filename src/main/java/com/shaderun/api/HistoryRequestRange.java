package com.shaderun.api;

import java.util.Date;

public record HistoryRequestRange(
        String startDate,
        String endDate
) {}
