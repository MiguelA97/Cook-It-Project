package isel.leic.ps.controller;

import isel.leic.ps.exceptions.BadRequestException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.exceptions.NotFoundException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeInformationObject;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;
import isel.leic.ps.service.APIService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static isel.leic.ps.utils.HeadersUtils.setSirenContentType;

@RestController
@RequestMapping("/v1/recipes")
public class APIController {

    private final APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeObject>> searchRecipes(@RequestParam(value = "query", defaultValue = "", required = false) String query,
                                                            @RequestParam(value = "number", defaultValue = "10") String number,
                                                            @RequestParam(value = "offset", defaultValue = "0") String offset,
                                                            @RequestParam(value = "diet", defaultValue = "", required = false) String diet,
                                                            @RequestParam(value = "intolerances", defaultValue = "", required = false) String intolerances,
                                                            @RequestParam(value = "type", defaultValue = "", required = false) String type,
                                                            @RequestParam(value = "cuisine", defaultValue = "", required = false) String cuisine,
                                                            @RequestParam(value = "includeIngredients", defaultValue = "", required = false) String ingredients
                                                            ) throws BadRequestException {
        List<RecipeObject> searchRecipesObjects;
        try {
            searchRecipesObjects = apiService.searchRecipes(query, number, offset, diet, intolerances, type, cuisine, ingredients);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(searchRecipesObjects, setSirenContentType(headers), HttpStatus.OK);
    }

    @GetMapping("/{recipeId}/information")
    public ResponseEntity<RecipeInformationObject> getRecipeInformation(@PathVariable("recipeId") int recipeId) throws NotFoundException, BadRequestException {
        RecipeInformationObject recipeInformationObject;
        try {
            recipeInformationObject = apiService.getRecipeInformation(recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(recipeInformationObject, setSirenContentType(headers), HttpStatus.OK);
    }
}