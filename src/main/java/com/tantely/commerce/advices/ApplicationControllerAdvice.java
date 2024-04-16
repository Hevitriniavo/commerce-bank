package com.tantely.commerce.advices;

import com.tantely.commerce.exceptions.EntityBadRequestException;
import com.tantely.commerce.exceptions.EntityInternalServerException;
import com.tantely.commerce.exceptions.EntityNotFoundException;
import com.tantely.commerce.exceptions.FileOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ApplicationError handlerEntity(EntityNotFoundException ex) {
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(EntityBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ApplicationError handlerEntity(EntityBadRequestException ex) {
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );
    }
    @ExceptionHandler(EntityInternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ApplicationError handlerEntity(EntityInternalServerException ex) {
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }

    @ExceptionHandler(FileOperationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ApplicationError handlerEntity(FileOperationException ex) {
        return new ApplicationError(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }
}
