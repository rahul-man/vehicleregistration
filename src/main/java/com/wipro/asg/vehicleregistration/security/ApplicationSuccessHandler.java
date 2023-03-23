package com.wipro.asg.vehicleregistration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.wipro.asg.vehicleregistration.model.ApplicationUserRole.ADMIN;
import static com.wipro.asg.vehicleregistration.model.ApplicationUserRole.USER;

@Component
public class ApplicationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            try {
                if (grantedAuthority.getAuthority().equals("ROLE_" + USER.name())) {
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/newRegistration");
                } else if (grantedAuthority.getAuthority().equals("ROLE_" + ADMIN.name())) {
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/adminHome");
                }
            } catch (Exception e) {
                throw new IllegalStateException();
            }
        }
    }
}
