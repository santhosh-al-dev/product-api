package com.org.products.expectionhandling;

import com.org.products.data.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseStatus> handleProductException(ProductNotFoundException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String statusDescription = status.getReasonPhrase();
        ResponseStatus errorResponse = new ResponseStatus(
                String.valueOf(status.value()),  // Status code
                statusDescription,// Error Status
                ex.getMessage()// Message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}