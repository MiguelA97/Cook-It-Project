package isel.leic.ps.model.outputModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import isel.leic.ps.hypermedia.hateoas.Link;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.utils.UriBuilderUtils;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "properties", "links"})
public class RecipeOutputModel {

    private final static String ENTITY_CLASS = "recipe";

    @JsonProperty(value = "class")
    private final String[] klass;
    @JsonProperty
    private final Map<String, Object> properties;
    @JsonProperty
    private final Link[] links;

    public RecipeOutputModel(Recipe recipe, String username) {
        this.klass = initKlass();
        this.properties = initProperties(recipe);
        this.links = initLinks(username, recipe.getIdUrl(), recipe.getId());
    }

    private String[] initKlass() {
        return new String[]{ENTITY_CLASS};
    }

    private HashMap<String, Object> initProperties(Recipe recipe) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("recipe-id", recipe.getId());
        properties.put("recipe-api-id", recipe.getIdApi());
        properties.put("user-id", recipe.getIdUser());
        properties.put("recipe-list-id", recipe.getIdUrl());
        properties.put("recipe-name", recipe.getName());
        properties.put("recipe-ready-in-minutes", recipe.getReadyInMinutes());
        properties.put("recipe-instructions", recipe.getInstructions());
        properties.put("recipe-image", recipe.getImage());
        properties.put("recipe-servings", recipe.getServings());
        properties.put("recipe-dairy-free", recipe.isDairyFree());
        properties.put("recipe-gluten-free", recipe.isGlutenFree());
        properties.put("recipe-vegan", recipe.isVegan());
        properties.put("recipe-vegetarian", recipe.isVegetarian());
        properties.put("recipe-ingredient-details-list", recipe.getIngredientDetailsList());
        return properties;
    }

    private Link[] initLinks(String username, int idUrl, int recipeId) {
        // Link-self
        String recipeUri = UriBuilderUtils.buildRecipeUri(username, idUrl, recipeId);
        Link self = new Link(new String[]{"self"}, new String[]{ENTITY_CLASS}, recipeUri);
        // Link-related-list
        String recipeListsUri = UriBuilderUtils.buildRecipesUri(username, idUrl);
        Link related = new Link(new String[]{"related"}, new String[]{"list, collection"}, recipeListsUri);

        return new Link[]{self, related};
    }
}
