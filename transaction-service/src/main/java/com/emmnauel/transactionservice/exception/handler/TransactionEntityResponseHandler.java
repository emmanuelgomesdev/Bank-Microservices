package com.emmnauel.transactionservice.exception.handler;

import com.emmnauel.transactionservice.exception.BusinessException;
import com.emmnauel.transactionservice.exception.dto.ExceptionResponse;
import com.emmnauel.transactionservice.exception.dto.FieldErrorResponse;
import com.emmnauel.transactionservice.exception.enums.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class TransactionEntityResponseHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handlerBusinessException(
            BusinessException ex,
            HttpServletRequest http) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getErrorResponse().getHttpStatus().value(),
                ex.getErrorResponse().getErrorCode(),
                ex.getErrorResponse().getErrorMessage(),
                http.getRequestURI(),
                List.of()
        );

        return ResponseEntity.status(ex.getErrorResponse().getHttpStatus()).body(response);

    }


    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {


        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                )).toList();

        ErrorResponse error = ErrorResponse.TRANSACTION_VALIDATION_ERROR;

        String path = ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                error.getHttpStatus().value(),
                error.getErrorCode(),
                error.getErrorMessage(),
                path,
                errors
        );

        return handleExceptionInternal(
                ex,
                response,
                headers,
                error.getHttpStatus(),
                request);


    }
}
