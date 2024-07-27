package com.example.library.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService {

//        extends DefaultOAuth2UserService  implements OAuth2UserService<OAuth2UserRequest, OidcUser> {

//    @Override
    public OidcUser loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Call the super class's loadUser method to fetch the OidcUser
//        OidcUser oidcUser = super.loadUser(userRequest);
//
//        // Process the user data as needed
//        String email = oidcUser.getAttribute("email");
//        String name = oidcUser.getAttribute("name");
//
//        // Add logic to find or create a user in your local database
//        // Example: userService.findOrCreateUser(email, name);

        return null;
//        return oidcUser;
    }
}
