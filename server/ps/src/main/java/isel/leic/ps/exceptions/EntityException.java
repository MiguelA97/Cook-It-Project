package isel.leic.ps.exceptions;

public class EntityException extends Exception {

    private String message;

    public EntityException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
