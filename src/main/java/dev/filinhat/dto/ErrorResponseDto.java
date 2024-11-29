package dev.filinhat.dto;

import java.time.Instant;

public record ErrorResponseDto(
        String error,
        String message,
        Instant timestamp,
        String details
){}
