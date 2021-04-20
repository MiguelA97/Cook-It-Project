package isel.leic.ps.model;

import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@IdClass(UserRecipeListId.class)
@Table(name = "USER_RECIPE_LIST")
@Getter @Setter
public class UserRecipeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //TODO acho que isto nao funciona!! verificar!
    @Column(name = "id", nullable = false)
    private int id;

    @Id
    @Column(name = "id_user", nullable = false)
    private int userId;

    @Column(name = "list_name", length = RestrictionUtils.URL_LIST_NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility", length = RestrictionUtils.URL_VISIBILITY_MAX_LENGTH)
    private String visibility;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    private Users userByUserId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRecipeList")
    private ArrayList<UsersRecipes> usersRecipesList;

    protected UserRecipeList() {
    }

    public UserRecipeList(int userId, String name, String description, String visibility) {
        setUserId(userId);
        setName(name);
        setDescription(description);
        setVisibility(visibility);
    }

    public UserRecipeList(int id, int userId, String name, String description, String visibility) {
        setId(id);
        setUserId(userId);
        setName(name);
        setDescription(description);
        setVisibility(visibility);
    }
}