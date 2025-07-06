package com.scm.contactManager.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.userdetails.UserDetails;

public class Helper {

    public static String getEmailOfLoggedUser(Authentication authentication) {
        if (authentication == null) return null;

        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;

            // Google returns "email", GitHub may vary
            Object email = oauthUser.getAttributes().get("email");
            if (email != null) {
                return email.toString();
            }

            // fallback (GitHub often uses "login" or "name")
            Object username = oauthUser.getAttributes().get("login");
            if (username != null) {
                return username.toString();
            }

        } else if (principal instanceof UserDetails) {
            // For regular login
            return ((UserDetails) principal).getUsername();
        }

        // fallback
        return principal.toString();
    }
}
