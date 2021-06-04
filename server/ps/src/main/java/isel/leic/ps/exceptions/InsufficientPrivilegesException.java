package isel.leic.ps.exceptions;

public class InsufficientPrivilegesException extends Exception {

    private String message;

    public InsufficientPrivilegesException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}