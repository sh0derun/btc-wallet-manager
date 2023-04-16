package com.shaderun.api;

import org.springframework.http.HttpStatus;

public record Result(
        HttpStatus status,
        String message
){}
