package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRecipesObject {

    @JsonProperty(value = "results")
    private final List<RecipeObject> results;
    @JsonProperty(value = "offset")
    private final int offset;
    @JsonProperty(value = "number")
    private final int number;
    @JsonProperty(value = "totalResults")
    private final int totalResults;

    public SearchRecipesObject(List<RecipeObject> results, int offset, int number, int totalResults) {
        this.results = results;
        this.offset = offset;
        this.number = number;
        this.totalResults = totalResults;
    }
}
