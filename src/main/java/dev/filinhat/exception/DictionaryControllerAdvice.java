package dev.filinhat.exception;

import dev.filinhat.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class DictionaryControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryControllerAdvice.class);

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(Exception e) {
        String errorMessage = "An error occurred";
        logger.error("{}: {}", errorMessage, e.getMessage(), e);

        return new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                Instant.now(),
                e.getClass().getSimpleName()
        );
    }
}
