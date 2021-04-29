package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.service.UserRecipeListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{username}/lists")
public class UserRecipeListController {

    private final UserRecipeListService userRecipeListService;

    public UserRecipeListController(UserRecipeListService userRecipeListService) {
        this.userRecipeListService = userRecipeListService;
    }

    @GetMapping("")
    public List<UserRecipeList> getUserRecipeListsByUsername(@PathVariable("username") String username) throws NotFoundException, BadRequestException {
        List<UserRecipeList> userRecipeLists = null;
        try {
            userRecipeLists = userRecipeListService.getUserRecipeListsByUsername(username);                           //TODO tratar de outputModel, autenticaçao, etc!
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return userRecipeLists;
    }

    @GetMapping("/{idUrl}")
    public UserRecipeList getUserRecipeListsById(@PathVariable("idUrl") int idUrl) throws NotFoundException, BadRequestException {
        UserRecipeList userRecipeList = null;
        try {
            userRecipeList = userRecipeListService.getUserRecipeListById(idUrl);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        return userRecipeList;
    }

    @PostMapping("")
    public UserRecipeList addUserRecipeList(@RequestBody UserRecipeList userRecipeList) throws BadRequestException, ConflictException {
        try {
            userRecipeListService.addUserRecipeList(userRecipeList);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        }
        return userRecipeList;
    }

    @PatchMapping("/{idUrl}")
    public UserRecipeList updateUserRecipeList(@PathVariable("idUrl") int idUrl, @RequestBody UserRecipeList userRecipeList) throws BadRequestException, ConflictException, NotFoundException {
        try {
            userRecipeList = userRecipeListService.updateUserRecipeList(idUrl, userRecipeList);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        }
        return userRecipeList;
    }

    @DeleteMapping("/{idUrl}")
    public void deleteUserRecipeList(@PathVariable("idUrl") int idUrl) throws BadRequestException, NotFoundException {
        try {
            userRecipeListService.deleteUserRecipeListById(idUrl);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
