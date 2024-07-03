package es.nextdigital.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(Long id) {
        super("The balance of the card: " + id + " is not enough");
    }
}
