package tech.savvy.bookstore.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tech.savvy.bookstore.dto.ApiError;
import tech.savvy.bookstore.exception.BookStoreException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
        Exception ex, 
        WebRequest request) {
        
        Set<ConstraintViolation<?>> msg = ((ConstraintViolationException)ex).getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for(ConstraintViolation<?> c : msg) {
        	sb.append(c.getMessageTemplate() + "\n");
        } 
        logger.error(String.format("Error : %s | Error Code : %s", sb.toString() , 201));
        //Assume field validation has error code 201
        ApiError err = new ApiError(
                LocalDateTime.now(), 
                sb.toString() ,
                201);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	@ExceptionHandler(BookStoreException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
    		BookStoreException ex) {
    	
    	logger.error(String.format("Error : %s | Error Code : %s", ex.getMessage() , ex.getErrorCode()));
        
    	ApiError err = new ApiError(
            LocalDateTime.now(), 
            ex.getMessage() ,
            ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
