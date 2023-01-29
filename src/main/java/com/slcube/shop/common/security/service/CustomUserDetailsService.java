package com.slcube.shop.common.security.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.member.repository.MemberRepositoryHelper;
import com.slcube.shop.common.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberRepositoryHelper memberRepositoryHelper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepositoryHelper.findByEmailAndMemberStatus(memberRepository, email, MemberStatus.MEMBER);

        if (member == null) {
            throw new UsernameNotFoundException(new MemberNotFoundException().getMessage());
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(member.getMemberStatus().name()));

        MemberContext memberContext = new MemberContext(member, roles);

        return memberContext;
    }
}
