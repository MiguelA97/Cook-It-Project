package isel.leic.ps.service;

import isel.leic.ps.model.Users;

public interface UserService {

    /**
     * Returns a User by its username
     *
     * @param username
     * @return User
     */
    Users getUserByUsername(String username);

    /**
     * Adds a User
     *
     * @param user
     * @return created User
     */
    Users addUser(Users user);

    /**
     * Updates a User
     *
     * @param user
     * @param username
     * @return updated User
     */
    Users updateUser(String username, Users user);

    /**
     * Deletes a User by its username
     *
     * @param username
     */
    void deleteUserByUsername(String username);
}
