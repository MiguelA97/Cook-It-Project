package isel.leic.ps.exceptions;

public class EntityNotFoundException extends Exception {

    private String userFriendlyMessage;

    public EntityNotFoundException(String message, String userFriendlyMessage) {
        super(message);
        this.userFriendlyMessage = userFriendlyMessage;
    }

    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }
}
