package isel.leic.ps.service;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.Users;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {

    /**
     * Verifies if the given user exists by it's username
     *
     * @param username
     * @return true if user exists, false otherwise
     * @throws EntityException if the given parameters are invalid
     */
    boolean existsUserByUserUsername(String username) throws EntityException;

    /**
     * Returns a User by its username
     *
     * @param username
     * @return User
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user with username is found
     */
    Users getUserByUsername(String username) throws EntityException, EntityNotFoundException;

    /**
     * Adds a User
     *
     * @param user
     * @return created User
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if a user already exists with the same parameters
     */
    Users addUser(Users user) throws EntityException, EntityAlreadyExistsException;

    /**
     * Updates a User
     *
     * @param user
     * @param username
     * @return updated User
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user with username is found
     * @throws EntityAlreadyExistsException if a user already exists with the same parameters
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    Users updateUser(String username, Users user) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException, InsufficientPrivilegesException;

    /**
     * Deletes a User by its username
     *
     * @param username
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user with username is found
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    void deleteUserByUsername(String username) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException;

    /**
     * Adds an ingredient to the user's ingredient list
     *
     * @param username
     * @param ingredient
     * @throws EntityException if the given parameters are invalid
     * @throws EntityAlreadyExistsException if the user already has the given ingredient in the list
     * @throws EntityNotFoundException if no user with username is found
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    void addUserIngredient(String username, String ingredient) throws EntityException, EntityAlreadyExistsException, EntityNotFoundException, InsufficientPrivilegesException;

    /**
     * Deletes an ingredient from the user's ingredient list
     *
     * @param username
     * @param ingredient
     * @throws EntityException if the given parameters are invalid
     * @throws EntityNotFoundException if no user with username is found or the ingredient doesn't exist
     * @throws InsufficientPrivilegesException if username is different from the authenticated user
     */
    void deleteUserIngredient(String username, String ingredient) throws EntityException, EntityNotFoundException, InsufficientPrivilegesException;
}