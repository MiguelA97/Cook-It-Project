package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;
}
