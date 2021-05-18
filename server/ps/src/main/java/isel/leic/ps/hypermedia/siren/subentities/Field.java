package isel.leic.ps.hypermedia.siren.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {

    public enum Type {
        hidden("hidden"), text("text"), search("search"), tel("tel"), url("url"), email("email"), password("password"),
        datetime("datetime"), date("date"), month("month"), week("week"), time("time"), datetimeLocal("datetimeLocal"),
        number("number"), range("range"), color("color"), checkbox("checkbox"), radio("radio"), file("file"),
        bool("boolean");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private String name;
    private Type type;
    private Object value;
    public String title;

    public Field(String name, Type type, Object value, String title) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.title = title;
    }
}
