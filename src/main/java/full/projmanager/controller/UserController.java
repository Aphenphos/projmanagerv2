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
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    public Object isUser() {
        var u = SecurityContextHolder.getContext().getAuthentication();
        Object curUsername = u.getPrincipal();
        System.out.println(curUsername);
        u.getAuthorities().forEach(a -> System.out.println(a));

        return curUsername;
    }

    @PostMapping("/signup")
    public NewUserRequest signup(@RequestBody NewUserRequest newUserRequest) throws Exception {
        System.out.println(newUserRequest);
       userService.saveUser(newUserRequest);
       return newUserRequest;
    }

}
