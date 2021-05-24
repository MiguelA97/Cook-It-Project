package isel.leic.ps.controller;

import isel.leic.ps.exceptions.BadRequestException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;
import isel.leic.ps.service.APIService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<RecipeObject>> searchRecipes(@RequestParam("query") String query) throws BadRequestException {
        List<RecipeObject> searchRecipesObjects;
        try {
            searchRecipesObjects = apiService.searchRecipes(query);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(searchRecipesObjects, setSirenContentType(headers), HttpStatus.OK);
    }

    /*@GetMapping("/{recipeId}/summary")
    public ResponseEntity<> summarizeRecipe() {
        return null;
    }

    @GetMapping("/{recipeId}/information")
    public ResponseEntity<> getRecipeInformation() {
        return null;
    }

    @GetMapping("/findByIngredients")
    public ResponseEntity<> searchRecipesByIngredients() {
        return null;
    }*/
}