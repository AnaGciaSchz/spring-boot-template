package es.nextdigital.exception;

public class CardNotActivatedException extends RuntimeException {

    public CardNotActivatedException(Long id) {
        super("The card: " + id + " is not activated");
    }
}
