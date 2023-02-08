package com.slcube.shop.common.config.jpa;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        Member member = memberContext.getMember();
        return Optional.ofNullable(member.getUsername());
    }
}
