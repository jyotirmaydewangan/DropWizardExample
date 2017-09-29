package com.dewangan.jyotirmay.auth;


import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Created by jyotirmay.d on 28/09/17.
 */
public class AppBasicAuthenticator implements Authenticator<BasicCredentials, User>
{
    private static final Map<String, ImmutableSet<String>> VALID_USERS = ImmutableMap.of(
            "user", (ImmutableSet<String>)ImmutableSet.of("USER"),
            "admin", (ImmutableSet<String>)ImmutableSet.of("ADMIN", "USER")
    );


    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        if (VALID_USERS.containsKey(credentials.getUsername()) && "password".equals(credentials.getPassword()))
        {
            return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
        }
        return Optional.empty();
    }
}