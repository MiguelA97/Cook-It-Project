package isel.leic.ps.controller;

import isel.leic.ps.exceptions.*;
import isel.leic.ps.model.Users;
import isel.leic.ps.model.outputModel.UserOutputModel;
import isel.leic.ps.model.outputModel.jsonObjects.JwtResponse;
import isel.leic.ps.model.outputModel.jsonObjects.LoginRequest;
import isel.leic.ps.security.CustomUserDetails;
import isel.leic.ps.security.jwt.JwtUtils;
import isel.leic.ps.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static isel.leic.ps.utils.HeadersUtils.setSirenContentType;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UsersController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws MyBadCredentialsException {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new MyBadCredentialsException(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getName()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserOutputModel> getUser(@PathVariable("username") String username) throws NotFoundException, BadRequestException {
        Users user;
        try {
            user = userService.getUserByUsername(username);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new UserOutputModel(user), setSirenContentType(headers), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserOutputModel> addUser(@RequestBody Users user) throws BadRequestException, ConflictException {
        try {
            userService.addUser(user);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityAlreadyExistsException e) {
            throw new ConflictException(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new UserOutputModel(user), setSirenContentType(headers), HttpStatus.CREATED);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserOutputModel> updateUser(@PathVariable("username") String username, @RequestBody Users user) throws BadRequestException, ConflictException, NotFoundException, ForbiddenException {
        try {
            user = userService.updateUser(username, user);
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
        return new ResponseEntity<>(new UserOutputModel(user), setSirenContentType(headers), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) throws BadRequestException, NotFoundException, ForbiddenException {
        try {
            userService.deleteUserByUsername(username);
        } catch (EntityException e) {
            throw new BadRequestException(e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InsufficientPrivilegesException e) {
            throw new ForbiddenException(e.getMessage());
        }
    }
}