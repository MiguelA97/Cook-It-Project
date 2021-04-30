package isel.leic.ps.exceptions;

import org.springframework.http.HttpStatus;

public class MismatchException extends ProblemDetailsException {

    public MismatchException(String detail) {
        super("Unprocessable Entity.", HttpStatus.UNPROCESSABLE_ENTITY, detail);
    }
}
