package isel.leic.ps.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class UserRecipeListId implements Serializable {

    private int id;
    private int userId;

    protected UserRecipeListId() {
    }

    public UserRecipeListId(int userId) {
        setUserId(userId);
    }

    public UserRecipeListId(int id, int userId) {
        setId(id);
        setUserId(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRecipeListId urlId = (UserRecipeListId) obj;
        return id == urlId.id && userId == urlId.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}