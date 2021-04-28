package isel.leic.ps.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ProblemDetailsException extends Exception {

    private String title;
    private HttpStatus status;
    private String detail;
    private String instance;

    public ProblemDetailsException(String title, HttpStatus status, String detail, String instance) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public ProblemDetailsException(String title, HttpStatus status, String detail) {
        this(title, status, detail, null);
    }
}
