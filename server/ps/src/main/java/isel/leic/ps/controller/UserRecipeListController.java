package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.UserRecipeList;
import isel.leic.ps.model.outputModel.UserRecipeListOutputModel;
import isel.leic.ps.service.UserRecipeListService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static isel.leic.ps.utils.HeadersUtils.setSirenContentType;

@RestController
@RequestMapping("/v1/users/{username}/lists")
public class UserRecipeListController {

    private final UserRecipeListService userRecipeListService;

    public UserRecipeListController(UserRecipeListService userRecipeListService) {
        this.userRecipeListService = userRecipeListService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserRecipeListOutputModel>> getUserRecipeListsByUsername(@PathVariable("username") String username) throws NotFoundException, BadRequestException {
        List<UserRecipeList> userRecipeLists;
        ArrayList<UserRecipeListOutputModel> userRecipeListOutputModels;
        try {
            userRecipeLists = userRecipeListService.getUserRecipeListsByUsername(username);                           //TODO tratar autenticaçao, etc!
            userRecipeListOutputModels = new ArrayList<>(userRecipeLists.size());
            for (UserRecipeList userRecipeList : userRecipeLists)
                userRecipeListOutputModels.add(new UserRecipeListOutputModel(userRecipeList, username));
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userRecipeListOutputModels, setSirenContentType(headers), HttpStatus.OK);
    }

    @GetMapping("/{idUrl}")
    public ResponseEntity<UserRecipeListOutputModel> getUserRecipeListsById(@PathVariable("username") String username, @PathVariable("idUrl") int idUrl) throws NotFoundException, BadRequestException {
        UserRecipeList userRecipeList;
        try {
            userRecipeList = userRecipeListService.getUserRecipeListById(idUrl);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new UserRecipeListOutputModel(userRecipeList, username), setSirenContentType(headers), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserRecipeListOutputModel> addUserRecipeList(@PathVariable("username") String username, @RequestBody UserRecipeList userRecipeList) throws BadRequestException, ConflictException, NotFoundException, MismatchException, ForbiddenException {
        try {
            userRecipeListService.addUserRecipeList(username, userRecipeList);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (EntityMismatchException e) {
            throw new MismatchException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new UserRecipeListOutputModel(userRecipeList, username), setSirenContentType(headers), HttpStatus.CREATED);
    }

    @PatchMapping("/{idUrl}")
    public ResponseEntity<UserRecipeListOutputModel> updateUserRecipeList(@PathVariable("username") String username, @PathVariable("idUrl") int idUrl, @RequestBody UserRecipeList userRecipeList) throws BadRequestException, ConflictException, NotFoundException, ForbiddenException {
        try {
            userRecipeList = userRecipeListService.updateUserRecipeList(username, idUrl, userRecipeList);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new UserRecipeListOutputModel(userRecipeList, username), setSirenContentType(headers), HttpStatus.OK);    }

    @DeleteMapping("/{idUrl}")
    public void deleteUserRecipeList(@PathVariable("username") String username, @PathVariable("idUrl") int idUrl) throws BadRequestException, NotFoundException, ForbiddenException {
        try {
            userRecipeListService.deleteUserRecipeListById(username, idUrl);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
    }
}