package isel.leic.ps.hypermedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails {

    @JsonProperty
    private final String title;

    @JsonProperty
    private final int status;

    @JsonProperty
    private final String detail;

    @JsonProperty
    private final String instance;

    public ProblemDetails(String title, int status, String detail, String instance) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public ProblemDetails(String title, int status, String detail) {
        this(title, status, detail, null);
    }
}