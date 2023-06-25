package com.dataintimate.api.advisor;

import com.dataintimate.api.exception.NotFoundException;
import com.dataintimate.api.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiderExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(new StandardResponse(404,e.getMessage(),e), HttpStatus.NOT_FOUND);
    }
}
