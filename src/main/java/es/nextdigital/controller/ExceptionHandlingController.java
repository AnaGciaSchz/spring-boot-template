package es.nextdigital.controller;

import es.nextdigital.CustomErrorResponse;
import es.nextdigital.exception.CardNotActivatedException;
import es.nextdigital.exception.InsufficientBalanceException;
import es.nextdigital.exception.InsufficientCreditScoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomErrorResponse> handleException(InsufficientBalanceException e) {
        return handleException(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomErrorResponse> handleException(InsufficientCreditScoreException e) {
        return handleException(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(CardNotActivatedException.class)
    public ResponseEntity<CustomErrorResponse> handleException(CardNotActivatedException e) {
        return handleException(HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<CustomErrorResponse> handleException(HttpStatus status, Exception e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(status.value(), e.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

}
