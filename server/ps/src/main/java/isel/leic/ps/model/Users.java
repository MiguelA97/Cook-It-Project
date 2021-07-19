package isel.leic.ps.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@TypeDef(
    name = "list-array",
    typeClass = ListArrayType.class
)
@Getter @Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private int id;

    @Column(name = "username", length = RestrictionUtils.USER_USERNAME_MAX_LENGTH, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = RestrictionUtils.USER_PASSWORD_MAX_LENGTH, nullable = false)
    private String password;

    @Column(name = "email", length = RestrictionUtils.USER_EMAIL_MAX_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(name = "name", length = RestrictionUtils.USER_NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Type(type = "list-array")
    @Column(name = "ingredients", columnDefinition = "text[]")
    private List<String> ingredients;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
    private Collection<UserRecipeList> userRecipeLists;

    protected Users() {
    }

    public Users(int id, String username, String password, String email, String name, List<String> ingredients) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setName(name);
        setIngredients(ingredients);
    }

    public Users(String username, String password, String email, String name, List<String> ingredients) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setName(name);
        setIngredients(ingredients);
    }
}