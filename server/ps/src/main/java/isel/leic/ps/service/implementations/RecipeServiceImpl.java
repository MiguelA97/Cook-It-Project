package isel.leic.ps.service.implementations;

import isel.leic.ps.components.AuthenticationFacade;
import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.exceptions.InsufficientPrivilegesException;
import isel.leic.ps.model.IngredientDetails;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.repository.RecipeRepository;
import isel.leic.ps.service.IngredientDetailsService;
import isel.leic.ps.service.RecipeService;
import isel.leic.ps.service.UserRecipeListService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRecipeListService userRecipeListService;
    private final IngredientDetailsService ingredientDetailsService;

    private final AuthenticationFacade authenticationFacade;
    private final MessageSource messageSource;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRecipeListService userRecipeListService, @Lazy IngredientDetailsService ingredientDetailsService, AuthenticationFacade authenticationFacade, MessageSource messageSource) {
        this.recipeRepository = recipeRepository;
        this.userRecipeListService = userRecipeListService;
        this.ingredientDetailsService = ingredientDetailsService;
        this.authenticationFacade = authenticationFacade;
        this.messageSource = messageSource;
    }

    @Override
    public boolean existsRecipeById(int id) throws EntityException {
        ValidationsUtils.validateRecipeId(id);
        return recipeRepository.existsById(id);
    }

    @Override
    public boolean existsRecipeByIdUrlAndIdApi(int listId, int apiId) throws EntityException {
        ValidationsUtils.validateUserRecipeListId(listId);
        return recipeRepository.existsByIdUrlAndIdApi(listId, apiId);
    }

    @Override
    public Recipe getRecipeById(int id) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateRecipeId(id);
        return recipeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{id}, Locale.ENGLISH)));
    }

    @Override
    public List<Recipe> getRecipesByUserRecipeListId(int listId) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserRecipeListId(listId);
        if(!userRecipeListService.existsUserRecipeListById(listId))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{listId}, Locale.ENGLISH));
        return recipeRepository.getRecipesByUserRecipeListId(listId);
    }

    @Transactional
    @Override
    public Recipe addRecipe(String username, int listId, Recipe recipe) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

        if (!userRecipeListService.existsUserRecipeListById(listId))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{listId}, Locale.ENGLISH));
        if (recipe.getIdApi() != 0)         //if it is a recipe from the API
            if (existsRecipeByIdUrlAndIdApi(listId, recipe.getIdApi()))     //verify if there isn't a recipe with the same api id in this list
                throw new EntityAlreadyExistsException(messageSource.getMessage("recipe_already_exist", new Object[]{listId, recipe.getIdApi()}, Locale.ENGLISH));
        ValidationsUtils.validateRecipeName(recipe.getName());
        //recipe.setIdUrl(listId);
        //recipe.setIdUser(userRecipeListService.getUserRecipeListById(listId).getIdUser()); estas 2 linha nao sao necessarias. esta info já é enviada em recipe!
        Recipe createdRecipe = recipeRepository.save(recipe);

        //recipe has been created, now create ingredients and add them to the created recipe ingredients list!
        for (IngredientDetails ingredientDetails : recipe.getIngredientDetailsList())
            ingredientDetailsService.addIngredientDetails(username, createdRecipe.getId(), ingredientDetails);

        return recipe;
    }

    @Transactional
    @Override
    public Recipe updateRecipe(String username, int recipeId, Recipe updatedRecipe) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

        if (!existsRecipeById(recipeId))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));
        ValidationsUtils.validateRecipeName(updatedRecipe.getName());

        Recipe recipe = getRecipeById(recipeId);
        recipe.setName(updatedRecipe.getName());
        recipe.setReadyInMinutes(updatedRecipe.getReadyInMinutes());
        recipe.setInstructions(updatedRecipe.getInstructions());
        recipe.setImage(updatedRecipe.getImage());
        recipe.setServings(updatedRecipe.getServings());
        recipe.setDairyFree(updatedRecipe.isDairyFree());
        recipe.setGlutenFree(updatedRecipe.isGlutenFree());
        recipe.setVegan(updatedRecipe.isVegan());
        recipe.setVegetarian(updatedRecipe.isVegetarian());

        return recipeRepository.save(recipe);
    }

    @Transactional
    @Override
    public void deleteRecipeById(String username, int recipeId) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

        if (!existsRecipeById(recipeId))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));
        recipeRepository.deleteById(recipeId);
    }
}