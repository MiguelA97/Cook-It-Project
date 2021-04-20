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
    @Column(name = "id_recipes", nullable = false)
    private int recipesId;

    @Id
    @Column(name = "id_user", nullable = false)
    private int userId;

    @Id
    @Column(name = "id_user_recipe_list", nullable = false)
    private int urlId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "id_user_recipe_list", referencedColumnName = "id_user_recipe_list", nullable = false, insertable = false, updatable = false)
    })
    private UserRecipeList userRecipeList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipes", referencedColumnName = "id_recipes", nullable = false, insertable = false, updatable = false)
    private Recipe recipe;

    protected UsersRecipes() {
    }

    public UsersRecipes(int recipesId, int userId, int urlId) {
        setRecipesId(recipesId);
        setUserId(userId);
        setUrlId(urlId);
    }
}
