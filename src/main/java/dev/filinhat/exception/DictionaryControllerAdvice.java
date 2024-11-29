package dev.filinhat.exception;

import dev.filinhat.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DictionaryControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryControllerAdvice.class);

    @ExceptionHandler({DictionaryValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationException(DictionaryValidationException e) {
        logger.error("Validation error: {}", e.getMessage(), e);
        return new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleGenericException(Exception e) {
        logger.error("Unexpected error: {}", e.getMessage(), e);
        return new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred."
        );
    }
}
