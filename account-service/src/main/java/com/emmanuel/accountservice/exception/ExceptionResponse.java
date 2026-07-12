package com.emmanuel.accountservice.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionResponse(
        LocalDateTime timestamp,
        int status,
        String errorCode,
        String message,
        String path,
        List<FieldErrorResponse> errors

){}
