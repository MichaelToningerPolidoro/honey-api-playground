package com.honeyautomation.apiplayground.config;

import com.honeyautomation.apiplayground.encoder.DefaultPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final PasswordEncoder passwordEncoder = DefaultPasswordEncoder.getDefaultEncoder();
        auth.inMemoryAuthentication()
                .withUser("HoneyApiPlayground")
                .password(passwordEncoder.encode("HoneyBasicPassword"))
                .roles("USER", "ADMIN")
        ;
    }
}
