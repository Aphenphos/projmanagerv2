package full.projmanager.config.managers;

import full.projmanager.config.providers.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomAuthenticationProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //more providers simply add a chain here
        if (provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
        };

        throw new BadCredentialsException("Provider failed");
    }
}
