package isel.leic.ps.service;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.Users;

public interface UserService {

    /**
     *
     * Verofoes if the given user exists by it's username
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
     */
    Users getUserByUsername(String username) throws EntityException, EntityNotFoundException;

    /**
     * Adds a User
     *
     * @param user
     * @return created User
     */
    Users addUser(Users user) throws EntityException, EntityAlreadyExistsException;

    /**
     * Updates a User
     *
     * @param user
     * @param username
     * @return updated User
     */
    Users updateUser(String username, Users user) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException;

    /**
     * Deletes a User by its username
     *
     * @param username
     */
    void deleteUserByUsername(String username) throws EntityException, EntityNotFoundException;
}
