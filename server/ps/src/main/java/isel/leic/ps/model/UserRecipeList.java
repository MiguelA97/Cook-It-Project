package isel.leic.ps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collection;

@Entity
@IdClass(UserRecipeListId.class)
@Table(name = "USER_RECIPE_LIST")
@Getter @Setter
public class UserRecipeList {

    @Id
    private int idUrl;

    @Id
    private int idUser;

    @Column(name = "list_name", length = RestrictionUtils.URL_LIST_NAME_MAX_LENGTH, nullable = false)
    private String listName;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility", length = RestrictionUtils.URL_VISIBILITY_MAX_LENGTH)
    private String visibility;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    private Users userByUserId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userRecipeListByUserRecipeListId")
    private Collection<Recipe> recipes;

    protected UserRecipeList() {
    }

    public UserRecipeList(int idUser, String listName, String description, String visibility) {
        setIdUser(idUser);
        setListName(listName);
        setDescription(description);
        setVisibility(visibility);
    }

    public UserRecipeList(int idUrl, int idUser, String listName, String description, String visibility) {
        setIdUrl(idUrl);
        setIdUser(idUser);
        setListName(listName);
        setDescription(description);
        setVisibility(visibility);
    }
}