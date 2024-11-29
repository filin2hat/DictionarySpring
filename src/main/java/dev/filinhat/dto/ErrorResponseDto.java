package dev.filinhat.dto;

public record ErrorResponseDto(
        String error,
        String message
) {
}
