package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.service.IngredientDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{username}/lists/{listId}/recipes/{recipeId}/ingredients")
public class IngredientDetailsController {

    private final IngredientDetailsService ingredientDetailsService;

    public IngredientDetailsController(IngredientDetailsService ingredientDetailsService) {
        this.ingredientDetailsService = ingredientDetailsService;
    }

    @GetMapping("/{ingredientDetailsId}")
    public IngredientDetails getIngredientDetailsById(@PathVariable("ingredientDetailsId") int ingredientDetailsId) throws BadRequestException, NotFoundException {
        IngredientDetails ingredientDetails;
        try {
            ingredientDetails = ingredientDetailsService.getIngredientDetailsById(ingredientDetailsId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return ingredientDetails;
    }

    @GetMapping("")
    public List<IngredientDetails> getIngredientDetailsByRecipeId(@PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException {
        List<IngredientDetails> ingredientDetails;
        try {
            ingredientDetails = ingredientDetailsService.getIngredientDetailsByRecipeId(recipeId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return ingredientDetails;
    }

    @PostMapping("")
    public IngredientDetails addIngredientDetails(@PathVariable("recipeId") int recipeId, @RequestBody IngredientDetails ingredientDetails) throws BadRequestException, ConflictException, NotFoundException {
        try {
            ingredientDetailsService.addIngredientDetails(recipeId, ingredientDetails);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return ingredientDetails;
    }

    @PatchMapping("/{ingredientDetailsId}")
    public IngredientDetails updateRecipe(@PathVariable("ingredientDetailsId") int ingredientDetailsId, @RequestBody IngredientDetails ingredientDetails) throws BadRequestException, NotFoundException, ConflictException {
        try {
            ingredientDetails = ingredientDetailsService.updateIngredientDetails(ingredientDetailsId, ingredientDetails);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        }
        return ingredientDetails;
    }

    @DeleteMapping("/{ingredientDetailsId}")
    public void deleteIngredientDetailsById(@PathVariable("ingredientDetailsId") int ingredientDetailsId) throws BadRequestException, NotFoundException {
        try {
            ingredientDetailsService.deleteById(ingredientDetailsId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
