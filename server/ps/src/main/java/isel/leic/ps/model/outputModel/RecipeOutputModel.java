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
        properties.put("recipeId", recipe.getId());
        properties.put("recipeApiId", recipe.getIdApi());
        properties.put("userId", recipe.getIdUser());
        properties.put("recipeListId", recipe.getIdUrl());
        properties.put("recipeName", recipe.getName());
        properties.put("recipeReadyInMinutes", recipe.getReadyInMinutes());
        properties.put("recipeInstructions", recipe.getInstructions());
        properties.put("recipeImage", recipe.getImage());
        properties.put("recipeServings", recipe.getServings());
        properties.put("recipeDairyFree", recipe.isDairyFree());
        properties.put("recipeGlutenFree", recipe.isGlutenFree());
        properties.put("recipeVegan", recipe.isVegan());
        properties.put("recipeVegetarian", recipe.isVegetarian());
        properties.put("recipeIngredientDetailsList", recipe.getIngredientDetailsList());
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
