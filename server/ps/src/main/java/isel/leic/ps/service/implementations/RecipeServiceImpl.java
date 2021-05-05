package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.Recipe;
import isel.leic.ps.model.Users;
import isel.leic.ps.repository.RecipeRepository;
import isel.leic.ps.repository.UsersRecipesRepository;
import isel.leic.ps.service.RecipeService;
import isel.leic.ps.service.UserRecipeListService;
import isel.leic.ps.service.UserService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UsersRecipesRepository usersRecipesRepository;
    private final UserRecipeListService userRecipeListService;
    private final UserService userService;

    private final MessageSource messageSource;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UsersRecipesRepository usersRecipesRepository, UserRecipeListService userRecipeListService, UserService userService, MessageSource messageSource) {
        this.recipeRepository = recipeRepository;
        this.usersRecipesRepository = usersRecipesRepository;
        this.userRecipeListService = userRecipeListService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean existsRecipeById(int id) throws EntityException {
        ValidationsUtils.validateRecipeId(id);
        return recipeRepository.existsById(id);
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
        ArrayList<Integer> recipesIds = (ArrayList<Integer>) usersRecipesRepository.findByIdUrl(listId);
        List<Recipe> recipes = null;
        for (Integer id: recipesIds)
            recipes.add(getRecipeById(id));
        return recipes;
    }

    @Transactional
    @Override
    public Recipe addRecipe(int listId, Recipe recipe) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException {
        if (!userRecipeListService.existsUserRecipeListById(listId))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{listId}, Locale.ENGLISH));
        ValidationsUtils.validateRecipeName(recipe.getName());
        return recipeRepository.save(recipe);
    }

    @Transactional
    @Override
    public Recipe updateRecipe(int id, Recipe updatedRecipe) throws EntityException, EntityNotFoundException {
        if (!existsRecipeById(id))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{id}, Locale.ENGLISH));
        ValidationsUtils.validateRecipeName(updatedRecipe.getName());

        Recipe recipe = getRecipeById(id);
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
    public void deleteRecipeById(String username, int listId, int recipeId) throws EntityException, EntityNotFoundException {
        if (!userService.existsUserByUserUsername(username))
            throw new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH));
        if (!userRecipeListService.existsUserRecipeListById(listId))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{listId}, Locale.ENGLISH));
        if (!existsRecipeById(recipeId))
            throw new EntityNotFoundException(messageSource.getMessage("recipe_Not_Exist", new Object[]{recipeId}, Locale.ENGLISH));

        //first delete recipe from the list
        usersRecipesRepository.deleteRecipeFromListByIdUrlAndRecipeId(listId, recipeId);
        //check if there are any lists from the current user with the same recipe
        if (!usersRecipesRepository.existsByIdUserAndIdRecipes(userService.getUserByUsername(username).getId(), recipeId))  //if there are none, delete the recipe
            recipeRepository.deleteById(recipeId);
    }
}
