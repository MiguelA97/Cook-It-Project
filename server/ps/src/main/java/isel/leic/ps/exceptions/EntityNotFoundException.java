package isel.leic.ps.exceptions;

public class EntityNotFoundException extends Exception {

    private String message;

    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
