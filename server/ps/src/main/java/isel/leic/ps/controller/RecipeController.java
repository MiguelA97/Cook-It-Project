package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{username}/lists/{listId}/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipeById(@PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException {
        Recipe recipe;
        try {
            recipe = recipeService.getRecipeById(recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return recipe;
    }

    @GetMapping("")
    public List<Recipe> getRecipesByUserRecipeListId(@PathVariable("listId") int listId) throws BadRequestException, NotFoundException {
        List<Recipe> recipes;
        try {
            recipes = recipeService.getRecipesByUserRecipeListId(listId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return recipes;
    }

    @PostMapping("")
    public Recipe addRecipe(@PathVariable("listId") int listId, @RequestBody Recipe recipe) throws BadRequestException, ConflictException, NotFoundException {
        try {
            recipeService.addRecipe(listId, recipe);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return recipe;
    }

    @PatchMapping("/{recipeId}")
    public Recipe updateRecipe(@PathVariable("recipeId") int recipeId, @RequestBody Recipe recipe) throws BadRequestException, NotFoundException {
        try {
            recipe = recipeService.updateRecipe(recipeId, recipe);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return recipe;
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipeById(@PathVariable("username") String username, @PathVariable("listId") int listId, @PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException {
        try {
            recipeService.deleteRecipeById(username, listId, recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
