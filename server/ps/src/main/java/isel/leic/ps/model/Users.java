package isel.leic.ps.model;

import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "USERS")
@Getter @Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", length = RestrictionUtils.USER_USERNAME_MAX_LENGTH, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = RestrictionUtils.USER_PASSWORD_MAX_LENGTH, nullable = false)
    private String password;

    @Column(name = "email", length = RestrictionUtils.USER_EMAIL_MAX_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(name = "name", length = RestrictionUtils.USER_NAME_MAX_LENGTH, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
    private ArrayList<UserRecipeList> userRecipeLists;

    protected Users() {
    }

    public Users(int id, String username, String password, String email, String name) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setName(name);
    }

    public Users(String username, String password, String email, String name) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setName(name);
    }
}
