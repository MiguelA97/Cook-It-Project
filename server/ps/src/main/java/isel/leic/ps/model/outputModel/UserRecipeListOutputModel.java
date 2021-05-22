package isel.leic.ps.model.outputModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import isel.leic.ps.hypermedia.hateoas.Link;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.utils.UriBuilderUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "properties", "links"})
public class UserRecipeListOutputModel {

    private final static String ENTITY_CLASS = "user-recipe-list";

    @JsonProperty(value = "class")
    private final String[] klass;
    @JsonProperty
    private final Map<String, Object> properties;
    @JsonProperty
    private final Link[] links;

    public UserRecipeListOutputModel(UserRecipeList userRecipeList, String username) {
        this.klass = initKlass();
        this.properties = initProperties(userRecipeList);
        this.links = initLinks(username, userRecipeList.getIdUrl());
    }

    private String[] initKlass() {
        return new String[]{ENTITY_CLASS};
    }

    private HashMap<String, Object> initProperties(UserRecipeList userRecipeList) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("user-recipe-list-id", userRecipeList.getIdUrl());
        properties.put("user-id", userRecipeList.getIdUser());
        properties.put("user-recipe-list-name", userRecipeList.getListName());
        properties.put("user-recipe-list-description", userRecipeList.getDescription());
        properties.put("user-recipe-list-visibility", userRecipeList.getVisibility());
        properties.put("user-recipe-list-recipes", userRecipeList.getRecipes());
        return properties;
    }

    private Link[] initLinks(String username, int idUrl) {
        // Link-self
        String userRecipeListUri = UriBuilderUtils.buildUserRecipeListUri(username, idUrl);
        Link self = new Link(new String[]{"self"}, new String[]{ENTITY_CLASS}, userRecipeListUri);
        // Link-related-list
        String userRecipeListsUri = UriBuilderUtils.buildUserRecipeListsUri(username);
        Link related = new Link(new String[]{"related"}, new String[]{"list, collection"}, userRecipeListsUri);

        return new Link[]{self, related};
    }
}
