package isel.leic.ps.model;

import isel.leic.ps.utils.RestrictionUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENT_DETAILS")
@Getter @Setter
public class IngredientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_api", unique = true)
    private int idApi;

    @Column(name = "id_recipe", nullable = false)
    private int recipeId;

    @Column(name = "aisle", length = RestrictionUtils.INGREDIENT_DETAILS_AISLE_MAX_LENGTH)
    private String aisle;

    @Column(name = "ingredient_name", length = RestrictionUtils.INGREDIENT_DETAILS_INGREDIENT_NAME_MAX_LENGTH, nullable = false)
    private String ingredientName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "unit", length = RestrictionUtils.INGREDIENT_DETAILS_UNIT_MAX_LENGTH, nullable = false)
    private String unit;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe", nullable = false, insertable = false, updatable = false)
    private Recipe recipeByRecipeId;

    protected IngredientDetails() {
    }

    public IngredientDetails(int idApi, int recipeId, String aisle, String ingredientName, double amount, String unit, String image) {
        setIdApi(idApi);
        setRecipeId(recipeId);
        setAisle(aisle);
        setIngredientName(ingredientName);
        setAmount(amount);
        setUnit(unit);
        setImage(image);
    }

    public IngredientDetails(int id, int idApi, int recipeId, String aisle, String ingredientName, double amount, String unit, String image) {
        setId(id);
        setIdApi(idApi);
        setRecipeId(recipeId);
        setAisle(aisle);
        setIngredientName(ingredientName);
        setAmount(amount);
        setUnit(unit);
        setImage(image);
    }
}
