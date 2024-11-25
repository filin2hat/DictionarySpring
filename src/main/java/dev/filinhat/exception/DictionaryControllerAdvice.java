package dev.filinhat.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

@ControllerAdvice
public class DictionaryControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryControllerAdvice.class);

    @ExceptionHandler({IllegalArgumentException.class, ResponseStatusException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(Exception e) {
        String errorMessage = "An error occurred: " + e.getMessage();
        logger.error(errorMessage, e);
        return Map.of("ERROR", errorMessage, "TIMESTAMP", Instant.now().toString(), "STACK_TRACE", Arrays.toString(e.getStackTrace()));
    }
}
