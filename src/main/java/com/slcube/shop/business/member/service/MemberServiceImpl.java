package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import com.slcube.shop.business.member.dto.*;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.member.repository.MemberRepositoryHelper;
import com.slcube.shop.common.exception.MemberNotFoundException;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberRepositoryHelper memberRepositoryHelper;

    @Override
    @Transactional
    public Long signUp(MemberSignUpRequestDto requestDto) {
        String password = requestDto.getPassword();
        requestDto.setPassword(passwordEncoder.encode(password));

        Member member = MemberMapper.toEntity(requestDto);
        return memberRepository.save(member).getId();
    }

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

    @Override
    @Transactional
    public Long changePassword(MemberChangePasswordRequestDto requestDto) {
        String email = requestDto.getEmail();
        Member member = memberRepositoryHelper.findByEmailAndMemberStatus(memberRepository, email, MemberStatus.MEMBER);

        String changedPassword = requestDto.getChangedPassword();
        member.changePassword(passwordEncoder.encode(changedPassword));

        return member.getId();
    }
}
