package com.namph.security.securityconfig;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext securityContext= SecurityContextHolder.getContext();
        return  Optional.of(securityContext.getAuthentication().getPrincipal().toString());
    }
}
