package full.projmanager.config;

import full.projmanager.entities.User;
import org.springframework.security.core.GrantedAuthority;

public class SecurityAuthority implements GrantedAuthority {
    private final User user;
    public SecurityAuthority(User user) {
    this.user = user;
    }

    @Override
    public String getAuthority() {
        return user.getAuthorities();
    }
}
