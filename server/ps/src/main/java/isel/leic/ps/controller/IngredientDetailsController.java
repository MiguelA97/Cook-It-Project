package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.model.outputModel.IngredientDetailsOutputModel;
import isel.leic.ps.service.IngredientDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static isel.leic.ps.utils.HeadersUtils.setSirenContentType;

@RestController
@RequestMapping("/v1/users/{username}/lists/{listId}/recipes/{recipeId}/ingredients")
public class IngredientDetailsController {

    private final IngredientDetailsService ingredientDetailsService;

    public IngredientDetailsController(IngredientDetailsService ingredientDetailsService) {
        this.ingredientDetailsService = ingredientDetailsService;
    }

    @GetMapping("/{ingredientDetailsId}")
    public ResponseEntity<IngredientDetailsOutputModel> getIngredientDetailsById(@PathVariable("username") String username, @PathVariable("listId") int idUrl, @PathVariable("ingredientDetailsId") int ingredientDetailsId) throws BadRequestException, NotFoundException {
        IngredientDetails ingredientDetails;
        try {
            ingredientDetails = ingredientDetailsService.getIngredientDetailsById(ingredientDetailsId);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new IngredientDetailsOutputModel(ingredientDetails, username, idUrl), setSirenContentType(headers), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<IngredientDetailsOutputModel>> getIngredientDetailsByRecipeId(@PathVariable("username") String username, @PathVariable("listId") int idUrl, @PathVariable("recipeId") int recipeId) throws BadRequestException, NotFoundException {
        List<IngredientDetails> ingredientDetails;
        ArrayList<IngredientDetailsOutputModel> ingredientDetailsOutputModels;
        try {
            ingredientDetails = ingredientDetailsService.getIngredientDetailsByRecipeId(recipeId);
            ingredientDetailsOutputModels = new ArrayList<>(ingredientDetails.size());
            for (IngredientDetails ingredientDetail : ingredientDetails)
                ingredientDetailsOutputModels.add(new IngredientDetailsOutputModel(ingredientDetail, username, idUrl));
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ingredientDetailsOutputModels, setSirenContentType(headers), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<IngredientDetailsOutputModel> addIngredientDetails(@PathVariable("username") String username, @PathVariable("listId") int idUrl, @PathVariable("recipeId") int recipeId, @RequestBody IngredientDetails ingredientDetails) throws BadRequestException, ConflictException, NotFoundException {
        try {
            ingredientDetailsService.addIngredientDetails(recipeId, ingredientDetails);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new IngredientDetailsOutputModel(ingredientDetails, username, idUrl), setSirenContentType(headers), HttpStatus.CREATED);
    }

    @PatchMapping("/{ingredientDetailsId}")
    public ResponseEntity<IngredientDetailsOutputModel> updateRecipe(@PathVariable("username") String username, @PathVariable("listId") int idUrl, @PathVariable("ingredientDetailsId") int ingredientDetailsId, @RequestBody IngredientDetails ingredientDetails) throws BadRequestException, NotFoundException, ConflictException {
        try {
            ingredientDetails = ingredientDetailsService.updateIngredientDetails(ingredientDetailsId, ingredientDetails);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new IngredientDetailsOutputModel(ingredientDetails, username, idUrl), setSirenContentType(headers), HttpStatus.OK);
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
