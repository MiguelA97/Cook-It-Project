package isel.leic.ps.model.outputModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import isel.leic.ps.hypermedia.siren.subentities.Action;
import isel.leic.ps.hypermedia.siren.subentities.Entity;
import isel.leic.ps.hypermedia.siren.subentities.Link;
import isel.leic.ps.model.Users;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"class", "properties", "entities", "actions", "links"})
public class UserOutputModel {

    private final static String ENTITY_CLASS = "users";

    @JsonProperty(value = "class")
    private final String[] klass;
    @JsonProperty
    private final Map<String, Object> properties;
    @JsonProperty
    private final Entity[] entities;
    @JsonProperty
    private final Action[] actions;
    @JsonProperty
    private final Link[] links;

    public UserOutputModel(Users user) {
        this.klass = initKlass();
        this.properties = initProperties(user);
        this.entities = initEntities(user);
        this.actions = initActions(user);
        this.links = initLinks(user);
    }

    private String[] initKlass() {
        return new String[]{ENTITY_CLASS};
    }

    private HashMap<String, Object> initProperties(Users user) {
        return null;
    }

    private Entity[] initEntities(Users user) {
        return null;
    }

    private Action[] initActions(Users user) {
       return null;
    }

    private Link[] initLinks(Users user) {
        return null;
    }
}
