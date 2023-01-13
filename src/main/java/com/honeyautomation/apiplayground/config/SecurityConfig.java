package com.honeyautomation.apiplayground.config;

import com.honeyautomation.apiplayground.encoder.DefaultPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (activeProfile.equals("local")) {
            http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final PasswordEncoder passwordEncoder = getDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("HoneyApiPlayground")
                .password(passwordEncoder.encode("HoneyBasicPassword"))
                .roles("USER", "ADMIN")
        ;
    }

    @Bean
    public PasswordEncoder getDefaultPasswordEncoder() {
        return DefaultPasswordEncoder.getDefaultEncoder();
    }
}
