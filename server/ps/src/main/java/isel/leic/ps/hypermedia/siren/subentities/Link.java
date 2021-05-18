package isel.leic.ps.hypermedia.siren.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"rel", "class", "href"})
public class Link {

    public String[] rel;
    @JsonProperty("class")
    public String[] klass;
    public String href;

    public Link(String[] rel, String[] klass, String href) {
        this.rel = rel;
        this.klass = klass;
        this.href = href;
    }
}
