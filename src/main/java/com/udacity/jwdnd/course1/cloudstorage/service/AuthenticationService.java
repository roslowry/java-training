package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private UserMapper userMapper;
    private HashService hashService;

    public AuthenticationService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("Authentication service");
        System.out.println("Authentication service");
        System.out.println("Authentication service");
        System.out.println("Authentication service");
        System.out.println("Authentication service");
        String attemptingUserUsername = authentication.getName();
        String attemptingUserPassword = authentication.getCredentials().toString();

        System.out.println("Authentication service. The attemptingUsername is: " + attemptingUserUsername);
        System.out.println("Authentication service. The attemptingUsername is: " + attemptingUserUsername);
        System.out.println("Authentication service. The attemptingUserPassword is: " + attemptingUserPassword);
        System.out.println("Authentication service. The attemptingUserPassword is: " + attemptingUserPassword);

        User userInDb = userMapper.findUserByUserName(attemptingUserUsername);
        System.out.println("Authentication service. The userInDb is: " + userInDb);
        System.out.println("Authentication service. The userInDb is: " + userInDb);

        if (userInDb != null) {
            String userInDbSalt = userInDb.getSalt();
            String userInDbPassword = userInDb.getPassword();
            if (hashService.getHashedValue(attemptingUserPassword, userInDbSalt).equals(userInDbPassword)) {

                return new UsernamePasswordAuthenticationToken(attemptingUserUsername, attemptingUserPassword, new ArrayList<>());
            }
        }
        return null;

    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
