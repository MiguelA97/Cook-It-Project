package isel.leic.ps.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class UserRecipeListId implements Serializable {

    private int idUrl;
    private int idUser;

    protected UserRecipeListId() {
    }

    public UserRecipeListId(int idUser) {
        setIdUser(idUser);
    }

    public UserRecipeListId(int idUrl, int idUser) {
        setIdUrl(idUrl);
        setIdUser(idUser);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRecipeListId urlId = (UserRecipeListId) obj;
        return idUrl == urlId.idUrl && idUser == urlId.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUrl, idUser);
    }
}