package com.example.complainboard.configuration;

import com.example.complainboard.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// This class extends WebSecurityConfigurerAdapter, which is a convenient base class for configuring Spring Security. It allows you to customize security settings.
public class SecurityConfigV2 extends WebSecurityConfigurerAdapter {
    @Override
//    This method is used to configure security settings related to HTTP requests and access control.
    protected void configure(HttpSecurity http) throws Exception {
        http
//        This is the starting point of the configuration for request authorization. It specifies which requests should be allowed or denied based on certain conditions.
                .authorizeRequests()
//        This line allows unrestricted access (permitAll) to the "/login" and "/sign-up" URLs. Anyone can access these pages without authentication.
                .antMatchers("/login", "/sign-up").permitAll()
//        Here, it is configured that any URL starting with "/complain/" requires the user to have either the "USER" or "ADMIN" role to access. Users with these roles will be authorized to access these URLs.
                .antMatchers("/complain/**").hasAnyRole("USER", "ADMIN")
//        This line specifies that any other request not matched by the previous antMatchers should be authenticated, meaning users must be logged in to access them.
                .anyRequest().authenticated()
//        This is used to chain multiple configurations together.
                .and()
//        This configures a custom login page at the "/login" URL. It also sets up the form-based authentication.
                .formLogin().loginPage("/login")
//                It specifies the request parameters used for the username and password during login.
                .usernameParameter("username").passwordParameter("password")
//        After successful login, the user will be redirected to the "/complain" URL.
                .defaultSuccessUrl("/complain")
//        It configures the URL where the login form data will be posted for processing, and it allows unrestricted access to this URL.
                .loginProcessingUrl("/j_spring_security_check").permitAll()
//        In case of login failure, the user will be redirected to the "/login?incorrectAccount" URL.
                .failureUrl("/login?status=incorrectAccount")
                //        This configures logout settings, specifying the "/logout" URL for logging out.
                .and().logout().logoutUrl("/logout")
//        After successful logout, the user will be redirected to the "/login?logout=true" URL, and this URL is accessible to all.
                .logoutSuccessUrl("/login?logout=true").permitAll()
//                This disables CSRF (Cross-Site Request Forgery) protection for simplicity. In a production application, CSRF protection should typically be enabled for security.
                .and().csrf().disable();
    }

    @Autowired
//    This injects an instance of the UserDetailsServiceImpl class, which is responsible for loading user details during authentication.
    private UserDetailsServiceImpl userDetailsService;

    @Bean
//    This defines a bean named "passwordEncoder" of type PasswordEncoder. It configures the password encoder, specifically using BCryptPasswordEncoder for hashing passwords securely.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
//    This method is used to configure the authentication manager. It specifies that the userDetailsService should be used for loading user details and sets the password encoder for handling password validation.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        this code is setting up the authentication manager to use a custom UserDetailsService (userDetailsService) for loading user details and a specified password encoder (passwordEncoder()) for password validation. This allows Spring Security to authenticate users based on the user details obtained from the UserDetailsService and validate passwords using the configured password encoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
