package com.shaderun.api;

import java.util.Date;

public record HistoryRequestRange(
        Date startDate,
        Date endDate
) {}
