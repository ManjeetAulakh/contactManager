package com.scm.contactManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.contactManager.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    // for testing purpose Only
    // @Bean
    // public UserDetailsService userDetailsService(){

    // UserDetails user1 = User
    // .withDefaultPasswordEncoder()
    // .username("admin")
    // .password("admin123")
    // .roles("ADMIN", "USER")
    // .build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
    // return inMemoryUserDetailsManager;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationManager authenticationProvider(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/do-register", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/login") // redirect to my custom login
                        .defaultSuccessUrl("/user/profile", true)
                        .permitAll())

                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // use same login page if you want
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .defaultSuccessUrl("/user/profile", true) // after successful Google login
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
