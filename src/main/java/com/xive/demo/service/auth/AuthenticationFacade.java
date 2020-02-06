package com.xive.demo.service.auth;

import com.xive.demo.entity.User;
import com.xive.demo.model.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Override
    public User getAuthenticatedUser() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        return ((UserDetailsImpl) authentication.getPrincipal()).getUserDetails();
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserDetailsImpl)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }
}


