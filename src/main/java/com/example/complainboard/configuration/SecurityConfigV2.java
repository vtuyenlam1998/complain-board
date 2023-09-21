//package com.example.complainboard.configuration;
//
//import com.example.complainboard.service.impl.UserDetailsServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfigV2 extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/auth/**", "/login", "/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .usernameParameter("username").passwordParameter("password")
//                .defaultSuccessUrl("/posts")
//                .loginProcessingUrl("/j_spring_security_check").permitAll()
//                .failureUrl("/login?incorrectAccount").and().logout().logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout").permitAll()
//                .and().csrf().disable();
//    }
//
////    private final UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//////        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123")).authorities("ROLE_ADMIN");
////    }
//}
