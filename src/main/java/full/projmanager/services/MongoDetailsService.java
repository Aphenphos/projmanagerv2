package full.projmanager.services;

import full.projmanager.config.WebSecurityUser;
import full.projmanager.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MongoDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MongoDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findByUsername(username);

        return u.map(WebSecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username ));
    }
}
