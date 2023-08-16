package com.honeyautomation.apiplayground.aspect;

import com.honeyautomation.apiplayground.service.TokenService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
@Configuration
@EnableAspectJAutoProxy
public class LoginTokenValidationAspect {

    @Autowired
    private TokenService tokenService;

    @Before("@annotation(com.honeyautomation.apiplayground.annotation.RequiresLoginTokenValidation)")
    public void beforeLoginTokenValidation() {
        final String loginTokenHeaderValue =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest()
                .getHeader("loginToken");

        tokenService.validateLoginToken(loginTokenHeaderValue);
    }
}
