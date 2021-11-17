package com.sonderben.sdbvideoapi.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

//@ControllerAdvice
public class MyExceptionHandler {

    final int NOT_FOUND=404;
    final int BAD_REQUEST=400;
    final int CONFLICT=409;
    final int FORBIDDEN=403;
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler({NoDataFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request,Exception e){
        return new ErrorMessage(e.getMessage(),request.getRequestURI(),NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({BadRequestException.class,
            org.hibernate.PropertyValueException.class,
    DuplicateKeyException.class,
    HttpRequestMethodNotSupportedException.class,
   MissingRequestHeaderException.class,
    MissingServletRequestParameterException.class,
    MethodArgumentTypeMismatchException.class,
    HttpMessageNotReadableException.class, DataIntegrityViolationException.class,
            ConstraintViolationException.class})
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request,Exception e){
        return new ErrorMessage(e.getMessage()/*.split(":")[0]*/,request.getRequestURI(),BAD_REQUEST);
    }


    @ResponseStatus(code = HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ErrorMessage conflictException(HttpServletRequest request,Exception e){
        return new ErrorMessage(e.getMessage(),request.getRequestURI(),CONFLICT);
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler({ForbiddenException.class})
    @ResponseBody
    public ErrorMessage forbiddenException(HttpServletRequest request,Exception e){
        return new ErrorMessage(e.getMessage(),request.getRequestURI(),FORBIDDEN);
    }

}
