package com.slcube.shop.common.config.jpa;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.slcube.shop.business.member.domain.MemberStatus.ANONYMOUS_USER;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }


        if (authentication.getPrincipal().equals(ANONYMOUS_USER.getMemberStatus())) {
            return Optional.of(ANONYMOUS_USER.getMemberStatus());
        }

        MemberSessionDto sessionDto = (MemberSessionDto) authentication.getPrincipal();
        return Optional.ofNullable(sessionDto.getUsername());
    }
}
