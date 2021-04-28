package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
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
    public Users getUser(@PathVariable("username") String username) throws NotFoundException, BadRequestException {
        Users user = null;
        try {
            user = userService.getUserByUsername(username);                              //TODO tratar de outputModel, autenticaçao, etc!
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage(), e.getUserFriendlyMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e.getUserFriendlyMessage());
        }
        return user;
    }

    @PostMapping("")
    public Users addUser(@RequestBody Users user) throws BadRequestException, ConflictException {
        try {
            userService.addUser(user);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage(), e.getUserFriendlyMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage(), e.getUserFriendlyMessage());
        }
        return user;
    }

    @PostMapping("/{username}")
    public Users updateUser(@PathVariable("username") String username, @RequestBody Users user) throws BadRequestException, ConflictException, NotFoundException {
        try {
            user = userService.updateUser(username, user);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage(), e.getUserFriendlyMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e.getUserFriendlyMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage(), e.getUserFriendlyMessage());
        }
        return user;
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) throws BadRequestException, NotFoundException {
        try {
            userService.deleteUserByUsername(username);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage(), e.getUserFriendlyMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e.getUserFriendlyMessage());
        }
    }
}
