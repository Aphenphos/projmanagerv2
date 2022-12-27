package full.projmanager.restControllers;

import full.projmanager.entities.User;
import full.projmanager.services.UserService;
import full.projmanager.types.NewUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/curUser")
    public String isUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/signup")
    public NewUserRequest signup(@RequestBody NewUserRequest newUserRequest) throws Exception {
       userService.saveUser(newUserRequest);
       return newUserRequest;
    }

    @PostMapping("/signin")
    public ResponseEntity<HttpStatus> signin(@RequestBody User user) throws Exception {

        Authentication authentication = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid login");
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }


}
