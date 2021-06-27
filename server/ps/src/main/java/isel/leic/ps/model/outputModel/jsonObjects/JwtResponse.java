package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {

    @JsonProperty(value = "token")
    private String token;
    private String type = "Bearer";
    @JsonProperty(value = "id")
    private int id;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "name")
    private String name;

    public JwtResponse(String accessToken, int id, String username, String email, String name) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
    }
}