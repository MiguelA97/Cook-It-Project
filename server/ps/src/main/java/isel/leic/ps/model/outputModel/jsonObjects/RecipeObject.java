package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeObject {

    @JsonProperty(value = "id")
    private final int id;
    @JsonProperty(value = "title")
    private final String title;
    @JsonProperty(value = "readyInMinutes")
    private final short readyInMinutes;
    @JsonProperty(value = "servings")
    private final short servings;
    @JsonProperty(value = "image")
    private final String image;

    public RecipeObject(int id, String title, short readyInMinutes, short servings, String image) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
    }
}