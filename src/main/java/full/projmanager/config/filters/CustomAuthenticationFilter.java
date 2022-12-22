package full.projmanager.config.filters;

import full.projmanager.config.authentication.CustomAuthentication;
import full.projmanager.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Create unauthed instance but with all neccesary info
        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication ca = new CustomAuthentication(false, key);
        //call manager and get authentication
        var a = customAuthenticationManager.authenticate(ca);
        //continue in chain if authenticated
        if (a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);
        filterChain.doFilter(request, response);
        }
    }
}
