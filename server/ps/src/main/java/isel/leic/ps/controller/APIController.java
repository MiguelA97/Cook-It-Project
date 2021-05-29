package isel.leic.ps.controller;

import isel.leic.ps.exceptions.BadRequestException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.exceptions.NotFoundException;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeInformationObject;
import isel.leic.ps.model.outputModel.jsonObjects.RecipeObject;
import isel.leic.ps.model.outputModel.jsonObjects.SearchRecipesByIngredientsObject;
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


    @GetMapping("/findByIngredients")
    public ResponseEntity<List<SearchRecipesByIngredientsObject>> searchRecipesByIngredients(@RequestParam("ingredients") String ingredients) throws BadRequestException {
        List<SearchRecipesByIngredientsObject> searchSearchRecipesByIngredientsObjects;
        try {
            searchSearchRecipesByIngredientsObjects = apiService.searchRecipesByIngredients(ingredients);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(searchSearchRecipesByIngredientsObjects, setSirenContentType(headers), HttpStatus.OK);
    }
}