package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummarizeRecipeObject {

    @JsonProperty(value = "title")
    private final String title;
    @JsonProperty(value = "summary")
    private final String summary;

    public SummarizeRecipeObject(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }
}
