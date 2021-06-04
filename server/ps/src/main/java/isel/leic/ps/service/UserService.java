package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.exceptions.InsufficientPrivilegesException;
import isel.leic.ps.model.Users;

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
}