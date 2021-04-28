package isel.leic.ps.service.implementations;

import isel.leic.ps.exceptions.EntityAlreadyExistsException;
import isel.leic.ps.exceptions.EntityException;
import isel.leic.ps.exceptions.EntityNotFoundException;
import isel.leic.ps.model.Users;
import isel.leic.ps.repository.UsersRepository;
import isel.leic.ps.service.UserService;
import isel.leic.ps.utils.ValidationsUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final MessageSource messageSource;

    public UserServiceImpl(UsersRepository usersRepository, MessageSource messageSource) {
        this.usersRepository = usersRepository;
        this.messageSource = messageSource;
    }

    @Override
    public boolean existsUserByUserUsername(String username) throws EntityException {
        ValidationsUtils.validateUserUsername(username);
        return usersRepository.existsByUsername(username);
    }

    @Override
    public Users getUserByUsername(String username) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserUsername(username);
        return usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH)));
    }

    @Transactional
    @Override
    public Users addUser(Users user) throws EntityException, EntityAlreadyExistsException {
        if (existsUserByUserUsername(user.getUsername()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("username_Already_Exist", new Object[]{user.getUsername()}, Locale.ENGLISH));
        ValidationsUtils.validateUserEmail(user.getEmail());
        if (usersRepository.existsByEmail(user.getEmail()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("email_Already_Exist", new Object[]{user.getEmail()}, Locale.ENGLISH));
        user.setPassword(user.getPassword());
        return usersRepository.save(user);
    }

    @Transactional
    @Override
    public Users updateUser(String username, Users updatedUser) throws EntityException, EntityNotFoundException, EntityAlreadyExistsException {
        if (!existsUserByUserUsername(username))
            throw new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH));
        if (!username.equals(updatedUser.getUsername()) && existsUserByUserUsername(updatedUser.getUsername()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("username_Already_Exist", new Object[]{updatedUser.getUsername()}, Locale.ENGLISH));

        Users user = getUserByUsername(username);
        ValidationsUtils.validateUserEmail(updatedUser.getEmail());
        if (!updatedUser.getEmail().equals(user.getEmail()) && usersRepository.existsByEmail(updatedUser.getEmail()))
            throw new EntityAlreadyExistsException(messageSource.getMessage("email_Already_Exist", new Object[]{updatedUser.getEmail()}, Locale.ENGLISH));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setPassword(updatedUser.getPassword());
        return usersRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserByUsername(String username) throws EntityException, EntityNotFoundException {
        ValidationsUtils.validateUserUsername(username);
        if (!usersRepository.existsByUsername(username))
            throw new EntityNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH));
        usersRepository.deleteByUsername(username);
    }
}
