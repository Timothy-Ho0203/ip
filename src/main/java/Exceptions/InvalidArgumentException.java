package Exceptions;

/**
 * Runtime Exception when invalid argument is presented
 */
public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
