package isel.leic.ps.model.outputModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import isel.leic.ps.hypermedia.hateoas.Link;
import isel.leic.ps.model.Users;
import isel.leic.ps.utils.UriBuilderUtils;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "properties", "links"})
public class UserOutputModel {

    private final static String ENTITY_CLASS = "users";

    @JsonProperty(value = "class")
    private final String[] klass;
    @JsonProperty
    private final Map<String, Object> properties;
    @JsonProperty
    private final Link[] links;

    public UserOutputModel(Users user) {
        this.klass = initKlass();
        this.properties = initProperties(user);
        this.links = initLinks(user.getUsername());
    }

    private String[] initKlass() {
        return new String[]{ENTITY_CLASS};
    }

    private HashMap<String, Object> initProperties(Users user) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("userId", user.getId());
        properties.put("userUsername", user.getUsername());
        properties.put("userPassword", user.getPassword());
        properties.put("userName", user.getName());
        properties.put("userEmail", user.getEmail());
        properties.put("userIngredients", user.getIngredients());
        properties.put("userRecipeLists", user.getUserRecipeLists());
        return properties;
    }

    private Link[] initLinks(String username) {
        // Link-self
        String userUri = UriBuilderUtils.buildUserUri(username);
        Link self = new Link(new String[]{"self"}, new String[]{ENTITY_CLASS}, userUri);
        // Link-index
        String indexUri = UriBuilderUtils.buildIndexUri();
        Link indexLink = new Link(new String[]{"index"}, new String[]{"index"}, indexUri);

        return new Link[]{self, indexLink};
    }
}
