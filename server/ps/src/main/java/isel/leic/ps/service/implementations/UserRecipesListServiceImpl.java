package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.repository.UserRecipeListRepository;
import isel.leic.ps.service.UserRecipeListService;
import isel.leic.ps.service.UserService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class UserRecipesListServiceImpl implements UserRecipeListService {

    private final UserRecipeListRepository userRecipeListRepository;
    private final UserService userService;

    private final MessageSource messageSource;

    public UserRecipesListServiceImpl(UserRecipeListRepository userRecipeListRepository, UserServiceImpl userService, MessageSource messageSource) {
        this.userRecipeListRepository = userRecipeListRepository;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean existsUserRecipeListById(int idUrl) throws EntityException {
        ValidationsUtils.validateUserRecipeListId(idUrl);
        return userRecipeListRepository.existsByIdUrl(idUrl);
    }

    @Override
    public boolean existsUserRecipeListByListName(int idUser, String listName) throws EntityException {
        ValidationsUtils.validateUserId(idUser);
        ValidationsUtils.validateUserRecipeListName(listName);
        return userRecipeListRepository.existsByIdUserAndListName(idUser, listName);
    }

    @Override
    public List<UserRecipeList> getUserRecipeListsByUsername(String username) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserUsername(username);
        if(!userService.existsUserByUserUsername(username))
            throw new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH));
        return userRecipeListRepository.findByUsername(username);
    }

    @Override
    public UserRecipeList getUserRecipeListById(int idUrl) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserRecipeListId(idUrl);
        return userRecipeListRepository
                .findByIdUrl(idUrl)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{idUrl}, Locale.ENGLISH)));
    }

    @Transactional
    @Override
    public UserRecipeList addUserRecipeList(UserRecipeList userRecipeList) throws EntityException, EntityAlreadyExistsException {
        if (existsUserRecipeListByListName(userRecipeList.getIdUser(), userRecipeList.getListName()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("user_recipe_list_Already_Exist", new Object[]{userRecipeList.getIdUser(), userRecipeList.getListName()}, Locale.ENGLISH));
        ValidationsUtils.validateUserRecipeListVisibility(userRecipeList.getVisibility());
        return userRecipeListRepository.save(userRecipeList);
    }

    @Transactional
    @Override
    public UserRecipeList updateUserRecipeList(int idUrl, UserRecipeList updatedUserRecipeList) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException {
        if (!existsUserRecipeListById(idUrl))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{idUrl}, Locale.ENGLISH));
        ValidationsUtils.validateUserRecipeListVisibility(updatedUserRecipeList.getVisibility());

        UserRecipeList userRecipeList = getUserRecipeListById(idUrl);

        if (!userRecipeList.getListName().equals(updatedUserRecipeList.getListName()) && existsUserRecipeListByListName(userRecipeList.getIdUser(), updatedUserRecipeList.getListName()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("user_recipe_list_Already_Exist", new Object[]{userRecipeList.getIdUser(), updatedUserRecipeList.getListName()}, Locale.ENGLISH));

        userRecipeList.setDescription(updatedUserRecipeList.getDescription());
        userRecipeList.setVisibility(updatedUserRecipeList.getVisibility());
        userRecipeList.setListName(updatedUserRecipeList.getListName());
        return userRecipeListRepository.save(userRecipeList);
    }

    @Transactional
    @Override
    public void deleteUserRecipeListById(int idUrl) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserRecipeListId(idUrl);
        if (!existsUserRecipeListById(idUrl))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{idUrl}, Locale.ENGLISH));
        userRecipeListRepository.deleteByIdUrl(idUrl);
    }
}
