package isel.leic.ps.controller;

import isel.leic.ps.model.Users;
import isel.leic.ps.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public Users getUser(@PathVariable("username") String username) throws Exception {
        Users user = null;
        try {
            user = userService.getUserByUsername(username);                              //TODO tratar de exceptions, outputModel, autenticaçao, etc!
        } catch (Exception e) {
            throw new Exception("An error occurred while getting the user!");
        }
        return user;
    }

    @PostMapping("")
    public Users addUser(@RequestBody Users user) throws Exception{
        try {
            userService.addUser(user);
        } catch (Exception e) {
            throw new Exception("An error occurred while adding the user!");
        }
        return user;
    }

    @PostMapping("/{username}")
    public Users updateUser(@PathVariable("username") String username, @RequestBody Users user) throws Exception {
        try {
            user = userService.updateUser(username, user);
        } catch (Exception e) {
            throw new Exception("An error occurred while updating the user!");
        }
        return user;
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) throws Exception {
        try {
            userService.deleteUserByUsername(username);
        } catch (Exception e) {
            throw new Exception("An error occurred while deleting the user!");
        }
    }
}
