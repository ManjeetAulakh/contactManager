package com.scm.contactManager.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.scm.contactManager.models.Providers;
import com.scm.contactManager.models.User;
import com.scm.contactManager.repository.UserRepo;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepo userRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String name = null, email = null, picture = null, providerId = null;
        Providers provider;

        try {
            switch (registrationId) {
                case "google":
                    name = oauth2User.getAttribute("name");
                    email = oauth2User.getAttribute("email");
                    picture = oauth2User.getAttribute("picture");
                    providerId = oauth2User.getAttribute("sub") != null ? oauth2User.getAttribute("sub").toString()
                            : null;
                    provider = Providers.GOOGLE;
                    break;

                case "github":
                    name = oauth2User.getAttribute("name");
                    if (name == null) {
                        name = oauth2User.getAttribute("login");
                    }

                    email = oauth2User.getAttribute("email");
                    if (email == null && name != null) {
                        email = name + "@github.com";
                    }

                    picture = oauth2User.getAttribute("avatar_url");
                    providerId = oauth2User.getAttribute("id") != null ? oauth2User.getAttribute("id").toString()
                            : null;
                    provider = Providers.GITHUB;
                    break;

                default:
                    throw new OAuth2AuthenticationException("Unsupported provider: " + registrationId);
            }

            // Validate required fields
            if (email == null) {
                throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
            }

            // Final variables for lambda
            final String finalName = name != null ? name : "";
            final String finalEmail = email;
            final String finalPicture = picture != null ? picture : "";
            final String finalProviderId = providerId != null ? providerId : "";
            final Providers finalProvider = provider;

            // check if user exists
            User user = userRepo.findByEmail(email).orElseGet(() -> {
                User newUser = new User();
                newUser.setUserId(UUID.randomUUID().toString());
                newUser.setEmail(finalEmail);
                newUser.setName(finalName);
                newUser.setProfilePic(finalPicture);
                newUser.setEnabled(true);
                newUser.setEmailVerified(true);  // verfied by provider
                newUser.setProvider(finalProvider);
                newUser.setProviderId(finalProviderId);
                newUser.setRoles(List.of("USER"));
                newUser.setAbout("I am from" + finalProvider.getClass() + "...");
                return userRepo.save(newUser);
            });

            return oauth2User; // we can also wrap in our own `CustomOAuth2User` if needed

        } catch (Exception e) {
            throw new OAuth2AuthenticationException("Error processing OAuth2 user");
        }
    }

}