package com.bms.BookMyMovie.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResouceNotFoundException ex , WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Not Found",
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatUnavailableException.class)
    public ResponseEntity<?> seatUnavailableException (SeatUnavailableException ex , WebRequest request) {
          ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Bad Request",
                  ex.getMessage(), request.getDescription(false));

          return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> GlobalHandler (Exception ex , WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server error",
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResponse , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
