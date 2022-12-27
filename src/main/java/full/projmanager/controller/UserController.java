package full.projmanager.controller;

import full.projmanager.services.UserService;
import full.projmanager.types.NewUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/curUser")
    public String isUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/signup")
    public NewUserRequest signup(@RequestBody NewUserRequest newUserRequest) throws Exception {
       userService.saveUser(newUserRequest);
       return newUserRequest;
    }

}
