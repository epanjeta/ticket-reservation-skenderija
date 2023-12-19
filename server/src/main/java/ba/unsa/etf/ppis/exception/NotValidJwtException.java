package ba.unsa.etf.ppis.exception;

public class NotValidJwtException extends RuntimeException{
    public NotValidJwtException(String message) {
        super(message);
    }
}
