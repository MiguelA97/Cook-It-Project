package isel.leic.ps.model;

import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "RECIPE")
@Getter @Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_api", unique = true)
    private int idApi;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeByRecipeId")
    private ArrayList<IngredientDetails> ingredientDetailsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private ArrayList<UsersRecipes> usersRecipesList;

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