package es.nextdigital.exception;

public class InsufficientCreditScoreException extends RuntimeException{

    public InsufficientCreditScoreException(Long id) {
        super("The credit score of the card: " + id + " is not enough");
    }
}
