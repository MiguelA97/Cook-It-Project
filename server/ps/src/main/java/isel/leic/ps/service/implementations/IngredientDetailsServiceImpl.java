package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.repository.IngredientDetailsRepository;
import isel.leic.ps.service.IngredientDetailsService;
import isel.leic.ps.service.RecipeService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class IngredientDetailsServiceImpl implements IngredientDetailsService {

    private final IngredientDetailsRepository ingredientDetailsRepository;
    private final RecipeService recipeService;

    private final MessageSource messageSource;

    public IngredientDetailsServiceImpl(IngredientDetailsRepository ingredientDetailsRepository, RecipeService recipeService, MessageSource messageSource) {
        this.ingredientDetailsRepository = ingredientDetailsRepository;
        this.recipeService = recipeService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean existsIngredientDetailsById(int id) throws EntityException {
        ValidationsUtils.validateIngredientDetailsId(id);
        return ingredientDetailsRepository.existsById(id);
    }

    @Override
    public boolean existsIngredientDetailsByRecipeIdAndIngredientName(int recipeId, String ingredientName) throws EntityException {
        ValidationsUtils.validateRecipeId(recipeId);
        ValidationsUtils.validateIngredientDetailsName(ingredientName);
        return ingredientDetailsRepository.existsByRecipeIdAndIngredientName(recipeId, ingredientName);
    }

    @Override
    public IngredientDetails getIngredientDetailsById(int id) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateIngredientDetailsId(id);
        return ingredientDetailsRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("ingredient_details_Not_Exist", new Object[]{id}, Locale.ENGLISH)));
    }

    @Override
    public List<IngredientDetails> getIngredientDetailsByRecipeId(int recipeId) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateRecipeId(recipeId);
        if (!recipeService.existsRecipeById(recipeId))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));
        return ingredientDetailsRepository.findByRecipeId(recipeId);
    }

    @Transactional
    @Override
    public IngredientDetails addIngredientDetails(int recipeId, IngredientDetails ingredientDetails) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException {
        if (!recipeService.existsRecipeById(recipeId))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));
        if (existsIngredientDetailsByRecipeIdAndIngredientName(recipeId, ingredientDetails.getIngredientName()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("ingredient_details_already_exist", new Object[]{ingredientDetails.getIngredientName()}, Locale.ENGLISH));
        ValidationsUtils.validateIngredientDetailsAisle(ingredientDetails.getAisle());
        ValidationsUtils.validateIngredientDetailsUnit(ingredientDetails.getUnit());

        ingredientDetails.setRecipeId(recipeId);
        return ingredientDetailsRepository.save(ingredientDetails);
    }

    @Transactional
    @Override
    public IngredientDetails updateIngredientDetails(int ingredientDetailsId, IngredientDetails updatedIngredientDetails) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException {
        if (!existsIngredientDetailsById(ingredientDetailsId))
            throw new EntityNotFoundException(messageSource.getMessage("ingredient_details_Not_Exist", new Object[]{ingredientDetailsId}, Locale.ENGLISH));
        IngredientDetails ingredientDetails = getIngredientDetailsById(ingredientDetailsId);
        //verify if there isn't an ingredient in that recipe with the same name
        if (!ingredientDetails.getIngredientName().equalsIgnoreCase(updatedIngredientDetails.getIngredientName()) && existsIngredientDetailsByRecipeIdAndIngredientName(ingredientDetails.getRecipeId(), updatedIngredientDetails.getIngredientName()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("ingredient_details_already_exist", new Object[]{updatedIngredientDetails.getIngredientName()}, Locale.ENGLISH));

        ValidationsUtils.validateIngredientDetailsUnit(updatedIngredientDetails.getUnit());
        ValidationsUtils.validateIngredientDetailsAisle(updatedIngredientDetails.getAisle());
        ValidationsUtils.validateIngredientDetailsName(updatedIngredientDetails.getIngredientName());

        ingredientDetails.setIngredientName(updatedIngredientDetails.getIngredientName());
        ingredientDetails.setUnit(updatedIngredientDetails.getUnit());
        ingredientDetails.setAmount(updatedIngredientDetails.getAmount());
        ingredientDetails.setImage(updatedIngredientDetails.getImage());
        ingredientDetails.setAisle(updatedIngredientDetails.getAisle());
        return ingredientDetailsRepository.save(ingredientDetails);
    }

    @Transactional
    @Override
    public void deleteById(int id) throws EntityException, EntityNotFoundException {
        if (!existsIngredientDetailsById(id))
            throw new EntityNotFoundException(messageSource.getMessage("ingredient_details_Not_Exist", new Object[]{id}, Locale.ENGLISH));
        ingredientDetailsRepository.deleteById(id);
    }
}