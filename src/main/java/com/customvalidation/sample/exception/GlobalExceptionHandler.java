package com.customvalidation.sample.exception;

import com.customvalidation.sample.dto.StandardDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StandardDto error =StandardDto.builder()
                .status("Failed")
                .build();
        String errorMessage = ex.getBindingResult().getFieldError().getField() + ex.getBindingResult().getFieldError().getDefaultMessage();
        error.reasonCode = StringUtils.isNotBlank(errorMessage) ? errorMessage : error.reasonCode;

        return error;
    }
}
