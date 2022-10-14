package com.stussy.stussyclon20220930changeun.api.advice;

import com.stussy.stussyclon20220930changeun.exception.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.exception.CustomInternalServerErrorException;
import com.stussy.stussyclon20220930changeun.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationErrorException(CustomValidationException e) {

        return ResponseEntity.badRequest().body(new CMRespDto<>(e.getMessage(), e.getErrorMap()));
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<?> internalServerErrorException(CustomInternalServerErrorException e) {

        return ResponseEntity.internalServerError().body(new CMRespDto<>(e.getMessage(), null));
    }
}
