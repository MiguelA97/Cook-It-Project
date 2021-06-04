package isel.leic.ps.service.implementations;

import isel.leic.ps.components.AuthenticationFacade;
import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.model.Users;
import isel.leic.ps.repository.UserRecipeListRepository;
import isel.leic.ps.service.UserRecipeListService;
import isel.leic.ps.service.UserService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserRecipesListServiceImpl implements UserRecipeListService {

    private final UserRecipeListRepository userRecipeListRepository;
    private final UserService userService;

    private final AuthenticationFacade authenticationFacade;
    private final MessageSource messageSource;

    public UserRecipesListServiceImpl(UserRecipeListRepository userRecipeListRepository, UserServiceImpl userService, AuthenticationFacade authenticationFacade, MessageSource messageSource) {
        this.userRecipeListRepository = userRecipeListRepository;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
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

        Users user = userService.getUserByUsername(username);
        return (List<UserRecipeList>) user.getUserRecipeLists();
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
    public UserRecipeList addUserRecipeList(String username, UserRecipeList userRecipeList) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException, EntityMismatchException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

        if (!userService.existsUserByUserUsername(username))
            throw new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH));
        Users user = userService.getUserByUsername(username);
        if (user.getId() != userRecipeList.getIdUser())
            throw new EntityMismatchException(messageSource.getMessage("entity_mismatch", new Object[]{username, user.getId(), userRecipeList.getIdUser()}, Locale.ENGLISH));
        if (existsUserRecipeListByListName(userRecipeList.getIdUser(), userRecipeList.getListName()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("user_recipe_list_Already_Exist", new Object[]{userRecipeList.getIdUser(), userRecipeList.getListName()}, Locale.ENGLISH));

        if (userRecipeList.getVisibility() == null) userRecipeList.setVisibility("private");         //set default visibility to private
        else ValidationsUtils.validateUserRecipeListVisibility(userRecipeList.getVisibility());

        AtomicInteger id = new AtomicInteger();
        userRecipeListRepository.getBiggestId().ifPresentOrElse(
                (value) -> { id.set(value + 1); },
                () -> { id.set(1); }
        );
        userRecipeList.setIdUrl(id.get());

        return userRecipeListRepository.save(userRecipeList);
    }

    @Transactional
    @Override
    public UserRecipeList updateUserRecipeList(String username, int idUrl, UserRecipeList updatedUserRecipeList) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

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
    public void deleteUserRecipeListById(String username, int idUrl) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException {
        String authenticatedUser = authenticationFacade.getAuthentication().getName();
        if (!authenticatedUser.equals(username))
            throw new InsufficientPrivilegesException(messageSource.getMessage("no_authorization", null, Locale.ENGLISH));

        if (!existsUserRecipeListById(idUrl))
            throw new EntityNotFoundException(messageSource.getMessage("user_recipe_list_Not_Exist", new Object[]{idUrl}, Locale.ENGLISH));
        userRecipeListRepository.deleteByIdUrl(idUrl);
    }
}