package exceptions;

/**
 * Runtime Exception when arguments are presented in the wrong format
 */
public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
