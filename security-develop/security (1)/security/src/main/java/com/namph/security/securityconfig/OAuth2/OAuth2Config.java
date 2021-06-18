package com.namph.security.securityconfig.OAuth2;

import com.namph.security.securityconfig.jwt.JwtAuthenticationFilter;
import com.namph.security.securityconfig.jwt.JwtTokenProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class OAuth2Config extends WebSecurityConfigurerAdapter {
    private JwtTokenProvider tokenProvider;
    public OAuth2Config(JwtTokenProvider tokenProvider) {
        this.setTokenProvider(tokenProvider);
    }

    public JwtTokenProvider getTokenProvider() {
        return tokenProvider;
    }

    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
