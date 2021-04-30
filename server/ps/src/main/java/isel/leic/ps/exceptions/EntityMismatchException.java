package isel.leic.ps.exceptions;

public class EntityMismatchException extends Exception{

    private String message;

    public EntityMismatchException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
