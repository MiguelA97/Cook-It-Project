package isel.leic.ps.hypermedia.siren.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action {

    private String name;
    private String title;
    private Method method;
    private String href;
    private String type;
    private Field[] fields;

    public Action(String name, String title, Method method, String href, String type, Field[] fields) {
        this.name = name;
        this.title = title;
        this.method = method;
        this.href = href;
        this.type = type;
        this.fields = fields;
    }
}
