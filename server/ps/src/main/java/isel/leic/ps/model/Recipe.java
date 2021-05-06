package isel.leic.ps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "RECIPE")
@Getter @Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe", nullable = false)
    private int id;

    @Column(name = "id_api")
    private int idApi;

    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "id_url", nullable = false)
    private int idUrl;

    @Column(name = "recipe_name", length = RestrictionUtils.RECIPE_NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = "ready_in_minutes", nullable = false)
    private short readyInMinutes;

    @Column(name = "instructions", nullable = false)
    private String instructions;

    @Column(name = "image")
    private String image;

    @Column(name = "servings", nullable = false)
    private short servings;

    @Column(name = "dairy_free", nullable = false)
    private boolean dairyFree;

    @Column(name = "gluten_free", nullable = false)
    private boolean glutenFree;

    @Column(name = "vegan", nullable = false)
    private boolean vegan;

    @Column(name = "vegetarian", nullable = false)
    private boolean vegetarian;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "id_url", referencedColumnName = "id_url", nullable = false, insertable = false, updatable = false)
    })
    private UserRecipeList userRecipeListByUserRecipeListId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeByRecipeId")
    private Collection<IngredientDetails> ingredientDetailsList;

    protected Recipe() {
    }

    public Recipe(int idApi, String name, short readyInMinutes, String instructions, String image, short servings, boolean dairyFree, boolean glutenFree, boolean vegan, boolean vegetarian) {
        setIdApi(idApi);
        setName(name);
        setReadyInMinutes(readyInMinutes);
        setInstructions(instructions);
        setImage(image);
        setServings(servings);
        setDairyFree(dairyFree);
        setGlutenFree(glutenFree);
        setVegan(vegan);
        setVegetarian(vegetarian);
    }

    public Recipe(int id, int idApi, String name, short readyInMinutes, String instructions, String image, short servings, boolean dairyFree, boolean glutenFree, boolean vegan, boolean vegetarian) {
        setId(id);
        setIdApi(idApi);
        setName(name);
        setReadyInMinutes(readyInMinutes);
        setInstructions(instructions);
        setImage(image);
        setServings(servings);
        setDairyFree(dairyFree);
        setGlutenFree(glutenFree);
        setVegan(vegan);
        setVegetarian(vegetarian);
    }
}