package isel.leic.ps.model.outputModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import isel.leic.ps.hypermedia.hateoas.Link;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.utils.UriBuilderUtils;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "properties", "links"})
public class IngredientDetailsOutputModel {
    private final static String ENTITY_CLASS = "ingredient-details";

    @JsonProperty(value = "class")
    private final String[] klass;
    @JsonProperty
    private final Map<String, Object> properties;
    @JsonProperty
    private final Link[] links;

    public IngredientDetailsOutputModel(IngredientDetails ingredientDetails, String username, int idUrl) {
        this.klass = initKlass();
        this.properties = initProperties(ingredientDetails);
        this.links = initLinks(username, idUrl, ingredientDetails.getRecipeId(), ingredientDetails.getId());
    }

    private String[] initKlass() {
        return new String[]{ENTITY_CLASS};
    }

    private HashMap<String, Object> initProperties(IngredientDetails ingredientDetails) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("ingredient-details-id", ingredientDetails.getId());
        properties.put("ingredient-details-api-id", ingredientDetails.getIdApi());
        properties.put("recipe-id", ingredientDetails.getRecipeId());
        properties.put("ingredient-details-aisle", ingredientDetails.getAisle());
        properties.put("ingredient-details-name", ingredientDetails.getIngredientName());
        properties.put("ingredient-details-amount", ingredientDetails.getAmount());
        properties.put("ingredient-details-unit", ingredientDetails.getUnit());
        properties.put("ingredient-details-image", ingredientDetails.getImage());
        return properties;
    }

    private Link[] initLinks(String username, int idUrl, int recipeId, int ingredientDetailsId) {
        // Link-self
        String ingredientDetailsUri = UriBuilderUtils.buildIngredientDetailsUri(username, idUrl, recipeId, ingredientDetailsId);
        Link self = new Link(new String[]{"self"}, new String[]{ENTITY_CLASS}, ingredientDetailsUri);
        // Link-related-list
        String ingredientDetailsListUri = UriBuilderUtils.buildIngredientsDetailsUri(username, idUrl, recipeId);
        Link related = new Link(new String[]{"related"}, new String[]{"list, collection"}, ingredientDetailsListUri);

        return new Link[]{self, related};
    }
}
