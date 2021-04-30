package isel.leic.ps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(UsersRecipesId.class)
@Table(name = "USERS_RECIPES")
@Getter @Setter
public class UsersRecipes {

    @Id
    private int recipesId;

    @Id
    private int userId;

    @Id
    private int urlId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "id_url", referencedColumnName = "id_url", nullable = false, insertable = false, updatable = false)
    })
    private UserRecipeList userRecipeList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe", nullable = false, insertable = false, updatable = false)
    private Recipe recipe;

    protected UsersRecipes() {
    }

    public UsersRecipes(int recipesId, int userId, int urlId) {
        setRecipesId(recipesId);
        setUserId(userId);
        setUrlId(urlId);
    }
}
