package com.xive.demo.service.auth;

import com.xive.demo.entity.User;


public interface IAuthenticationFacade {
    User getAuthenticatedUser();
}