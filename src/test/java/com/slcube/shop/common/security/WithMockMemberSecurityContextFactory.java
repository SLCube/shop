package com.slcube.shop.common.security;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import com.slcube.shop.common.security.token.AuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

public class WithMockMemberSecurityContextFactory implements WithSecurityContextFactory<WithMockMember> {

    @Override
    public SecurityContext createSecurityContext(WithMockMember mockMember) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Member member = Member.builder()
                .username(mockMember.username())
                .email(mockMember.email())
                .password(mockMember.password())
                .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(MemberStatus.MEMBER.name());
        authorities.add(authority);
        MemberContext memberContext = new MemberContext(member, authorities);
        AuthenticationToken token = new AuthenticationToken(memberContext, member.getPassword(), authorities);
        context.setAuthentication(token);
        return context;
    }
}
