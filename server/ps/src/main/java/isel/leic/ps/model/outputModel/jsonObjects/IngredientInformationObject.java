package isel.leic.ps.model.outputModel.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientInformationObject {

    @JsonProperty(value = "apiId")
    private final int id;
    @JsonProperty(value = "aisle")
    private final String aisle;
    @JsonProperty(value = "image")
    private final String image;
    @JsonProperty(value = "ingredientName")
    private final String name;
    @JsonProperty(value = "amount")
    private final double amount;
    @JsonProperty(value = "unit")
    private final String unit;

    public IngredientInformationObject(int id, String aisle, String image, String name, double amount, String unit) {
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
}