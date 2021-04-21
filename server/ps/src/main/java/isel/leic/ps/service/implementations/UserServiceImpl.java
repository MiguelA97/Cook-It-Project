package isel.leic.ps.service.implementations;

import isel.leic.ps.model.Users;
import isel.leic.ps.repository.UsersRepository;
import isel.leic.ps.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users getUserByUsername(String username) {
        return null;
    }

    @Override
    public Users addUser(Users user) {
        return null;
    }

    @Override
    public Users updateUser(String username, Users user) {
        return null;
    }

    @Override
    public void deleteUserByUsername(String username) {

    }
}
