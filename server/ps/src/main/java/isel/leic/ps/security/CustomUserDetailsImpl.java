package isel.leic.ps.security;

import isel.leic.ps.model.Users;
import isel.leic.ps.repository.UsersRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final MessageSource messageSource;

    public CustomUserDetailsImpl(UsersRepository usersRepository, MessageSource messageSource) {
        this.usersRepository = usersRepository;
        this.messageSource = messageSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(messageSource.getMessage("username_Not_Exist", new Object[]{username}, Locale.ENGLISH)));

        return  CustomUserDetails.build(user);//User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build();
    }
}