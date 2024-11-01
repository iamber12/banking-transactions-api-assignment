package com.brainridge.avbank.exception;

import com.brainridge.avbank.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.NOT_FOUND,
                ExceptionConstants.ERROR_ACCOUNT_NOT_FOUND,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity<Object> handleAccountExistsException(AccountExistsException ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ERROR_ACCOUNT_ALREADY_EXISTS,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler({SelfTransactionNotAllowedException.class, InsufficientPayerBalanceException.class})
    public ResponseEntity<Object> handleSelfTransactionNotAllowedException(Exception ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ERROR_TRANSACTION_FAILED,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return buildErrorResponseBody(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ERROR_VALIDATION,
                errors,
                request
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        String fieldName = ex.getName();
        String errorMessage = "Invalid value '" + ex.getValue() + "' for parameter '" + fieldName + "'";
        errors.put(fieldName, errorMessage);

        return buildErrorResponseBody(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ERROR_TYPE_MISMATCH,
                errors,
                request
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidRequestExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.BAD_REQUEST,
                ExceptionConstants.ERROR_INVALID_REQUEST_JSON,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.NOT_FOUND,
                ExceptionConstants.ERROR_ENDPOINT_NOT_FOUND,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.METHOD_NOT_ALLOWED,
                ExceptionConstants.ERROR_METHOD_NOT_SUPPORTED,
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return buildErrorResponseBody(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ExceptionConstants.UNEXPECTED_ERROR,
                ex.getMessage(),
                request
        );
    }

    public static ResponseEntity<Object> buildErrorResponseBody(
            HttpStatus httpStatus,
            String error,
            Object message,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", httpStatus.value());
        body.put("error", error);
        body.put("message", message);
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(body, httpStatus);
    }
}
