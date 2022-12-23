package full.projmanager.services;

import full.projmanager.entities.User;
import full.projmanager.repositories.UserRepository;
import full.projmanager.types.NewUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public void saveUser(NewUserRequest newUserRequest) {
        User toSave = new User();
        toSave.setUsername(newUserRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(newUserRequest.getPassword());
        toSave.setPassword(encodedPassword);
        toSave.setAuthorities("ALL");
        userRepository.save(toSave);

    }
}
