package isel.leic.ps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class UsersRecipesId implements Serializable {

    @Column(name = "id_recipes", nullable = false)
    private int recipesId;

    @Column(name = "id_user", nullable = false)
    private int userId;

    @Column(name = "id_url", nullable = false)
    private int urlId;

    protected UsersRecipesId() {
    }

    public UsersRecipesId(int recipesId, int userId, int urlId) {
        setRecipesId(recipesId);
        setUserId(userId);
        setUrlId(urlId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRecipesId that = (UsersRecipesId) o;
        return recipesId == that.recipesId && userId == that.userId && urlId == that.urlId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipesId, userId, urlId);
    }
}
