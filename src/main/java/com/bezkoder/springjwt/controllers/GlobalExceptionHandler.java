package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.exception.*;
import com.bezkoder.springjwt.response.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("resource_not_found");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardErrorResponse);
    }

    @ExceptionHandler(TrainingOngoingException.class)
    public ResponseEntity<?> handleTrainingOngoingException(TrainingOngoingException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("training_already_started");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardErrorResponse);
    }

    @ExceptionHandler(MaximumCapacityException.class)
    public ResponseEntity<?> handleMaximumCapacityException(MaximumCapacityException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("maximum_capacity_reached");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardErrorResponse);
    }

    @ExceptionHandler(UserAlreadyEnrolledException.class)
    public ResponseEntity<?> handleUserAlreadyEnrolledException(UserAlreadyEnrolledException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("user_already_enrolled");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardErrorResponse);
    }

    @ExceptionHandler(ReviewNotPermittedException.class)
    public ResponseEntity<?> handleReviewNotPermittedException(ReviewNotPermittedException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("review_not_permitted");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardErrorResponse);
    }

    @ExceptionHandler(ZeroResultsException.class)
    public ResponseEntity<?> handleZeroResultsException(ZeroResultsException ex){
        StandardErrorResponse standardErrorResponse = new StandardErrorResponse();
        standardErrorResponse.setCode("zero_results_found");
        standardErrorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(standardErrorResponse);
    }

}
