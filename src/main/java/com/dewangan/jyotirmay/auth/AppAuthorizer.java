package com.dewangan.jyotirmay.auth;

import io.dropwizard.auth.Authorizer;

/**
 * Created by jyotirmay.d on 28/09/17.
 */


public class AppAuthorizer implements Authorizer<User>
{
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
