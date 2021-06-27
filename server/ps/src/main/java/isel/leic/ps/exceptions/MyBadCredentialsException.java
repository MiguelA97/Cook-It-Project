package isel.leic.ps.exceptions;

import org.springframework.http.HttpStatus;

public class MyBadCredentialsException extends ProblemDetailsException {

    public MyBadCredentialsException(String detail) {
        super("Bad credentials.", HttpStatus.UNAUTHORIZED, detail);
    }
}
