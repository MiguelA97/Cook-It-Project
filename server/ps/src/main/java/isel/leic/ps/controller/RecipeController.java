package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.model.outputModel.RecipeOutputModel;
import isel.leic.ps.service.RecipeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static isel.leic.ps.utils.HeadersUtils.setSirenContentType;

@RestController
@RequestMapping("/v1/users/{username}/lists/{listId}/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeOutputModel> getRecipeById(@PathVariable("username") String username, @PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException {
        Recipe recipe;
        try {
            recipe = recipeService.getRecipeById(recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new RecipeOutputModel(recipe, username), setSirenContentType(headers), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<RecipeOutputModel>> getRecipesByUserRecipeListId(@PathVariable("username") String username, @PathVariable("listId") int listId) throws BadRequestException, NotFoundException {
        List<Recipe> recipes;
        ArrayList<RecipeOutputModel> recipeOutputModels;
        try {
            recipes = recipeService.getRecipesByUserRecipeListId(listId);
            recipeOutputModels = new ArrayList<>(recipes.size());
            for (Recipe recipe : recipes)
                recipeOutputModels.add(new RecipeOutputModel(recipe, username));
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(recipeOutputModels, setSirenContentType(headers), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RecipeOutputModel> addRecipe(@PathVariable("username") String username, @PathVariable("listId") int listId, @RequestBody Recipe recipe) throws BadRequestException, ConflictException, NotFoundException, ForbiddenException {
        try {
            recipeService.addRecipe(username, listId, recipe);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new RecipeOutputModel(recipe, username), setSirenContentType(headers), HttpStatus.CREATED);
    }

    @PatchMapping("/{recipeId}")
    public ResponseEntity<RecipeOutputModel> updateRecipe(@PathVariable("username") String username, @PathVariable("recipeId") int recipeId, @RequestBody Recipe recipe) throws BadRequestException, NotFoundException, ForbiddenException {
        try {
            recipe = recipeService.updateRecipe(username, recipeId, recipe);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new RecipeOutputModel(recipe, username), setSirenContentType(headers), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipeById(@PathVariable("username") String username, @PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException, ForbiddenException {
        try {
            recipeService.deleteRecipeById(username, recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
    }
}