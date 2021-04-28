package isel.leic.ps.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ProblemDetailsException {

    public NotFoundException(String message) {
        super("Resource not found.", HttpStatus.NOT_FOUND, message);
    }
}
