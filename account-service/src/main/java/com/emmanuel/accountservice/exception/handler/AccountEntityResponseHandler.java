package com.emmanuel.accountservice.exception.handler;

import com.emmanuel.accountservice.exception.BusinessException;
import com.emmanuel.accountservice.exception.ExceptionResponse;
import com.emmanuel.accountservice.exception.FieldErrorResponse;
import com.emmanuel.accountservice.exception.enums.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
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
public class AccountEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handlerBusinessException(
            BusinessException ex,
            HttpServletRequest http
    ) {

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                ex.getResponse().getHttpStatus().value(),
                ex.getResponse().getCode(),
                ex.getResponse().getMessage(),
                http.getRequestURI(),
                List.of()
        );

        return ResponseEntity.status(ex.getResponse().getHttpStatus()).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<FieldErrorResponse> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        ErrorResponse error = ErrorResponse.ACCOUNT_VALIDATION_ERROR;

        String path = ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                error.getHttpStatus().value(),
                error.getCode(),
                error.getMessage(),
                path,
                erros
        );


        return handleExceptionInternal(
                ex,
                response,
                headers,
                error.getHttpStatus(),
                request
        );
    }
}
