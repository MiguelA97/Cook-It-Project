package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeInformationObject {

    @JsonProperty(value = "vegetarian")
    private final boolean vegetarian;
    @JsonProperty(value = "vegan")
    private final boolean vegan;
    @JsonProperty(value = "glutenFree")
    private final boolean glutenFree;
    @JsonProperty(value = "dairyFree")
    private final boolean dairyFree;
    @JsonProperty(value = "servings")
    private final short servings;
    @JsonProperty(value = "apiId")
    private final int id;
    @JsonProperty(value = "title")
    private final String title;
    @JsonProperty(value = "readyInMinutes")
    private final short readyInMinutes;
    @JsonProperty(value = "image")
    private final String image;
    @JsonProperty(value = "instructions")
    private final String instructions;
    @JsonProperty(value = "ingredients")
    private final List<IngredientInformationObject> extendedIngredients;
    @JsonProperty(value = "summary")
    private String summary;


    public RecipeInformationObject(boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, short servings, int id, String title, short readyInMinutes, String image, String instructions, List<IngredientInformationObject> extendedIngredients, String summary) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.servings = servings;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.instructions = instructions;
        this.extendedIngredients = extendedIngredients;
        this.summary = summary;
    }
}