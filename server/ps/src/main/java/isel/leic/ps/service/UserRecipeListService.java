package isel.leic.ps.service;

import java.util.List;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.UserRecipeList;

public interface UserRecipeListService {

    /**
     * Verifies if the given user recipe list exists by it's id
     *
     * @param idUrl
     * @return true if user recipe list exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsUserRecipeListById(int idUrl) throws EntityException;

    /**
     * Verifies if the given user recipe list exists by it's list name and user id
     *
     * @param listName
     * @param idUser
     * @return true if user recipe list exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsUserRecipeListByListName(int idUser, String listName) throws EntityException;

    /**
     * Returns a list of the users recipe lists
     *
     * @param username
     * @return Users recipe lists
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user with username is found
     */
    List<UserRecipeList> getUserRecipeListsByUsername(String username) throws EntityException, EntityNotFoundException;

    /**
     * Returns a user recipe list by it's id
     *
     * @param idUrl
     * @return User recipe list
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user recipe list with idUrl is found
     */
    UserRecipeList getUserRecipeListById(int idUrl) throws EntityException, EntityNotFoundException;

    /**
     * Adds a user recipe list
     *
     * @param username
     * @param userRecipeList
     * @return created user recipe list
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if a user recipe list already exists with the same parameters
     * @throws EntityMismatchException if the user id doesn't match the user id with the corresponding username.
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    UserRecipeList addUserRecipeList(String username, UserRecipeList userRecipeList) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException, EntityMismatchException, InsufficientPrivilegesException;

    /**
     * Updates a user recipe list
     *
     * @param username
     * @param userRecipeList
     * @param idUrl
     * @return updated user recipe list
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user recipe list with idUrl is found
     * @throws EntityAlreadyExistsException if a user recipe list already exists with the same parameters
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    UserRecipeList updateUserRecipeList(String username, int idUrl, UserRecipeList userRecipeList) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException, InsufficientPrivilegesException;

    /**
     * Deletes a user recipe list by it's id
     *
     * @param username
     * @param idUrl
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user recipe list with idUrl is found
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    void deleteUserRecipeListById(String username, int idUrl) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException;
}