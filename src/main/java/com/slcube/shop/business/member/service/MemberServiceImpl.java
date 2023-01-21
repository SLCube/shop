package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.*;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.business.member.repository.MemberRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRepositoryHelper memberRepositoryHelper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signUp(MemberSignUpRequestDto requestDto) {
        String password = requestDto.getPassword();
        requestDto.setPassword(passwordEncoder.encode(password));

        Member member = MemberMapper.toEntity(requestDto);
        return memberRepository.save(member).getId();
    }

    @Override
    public MemberResponseDto login(MemberLoginDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        Member member = memberRepositoryHelper.findByEmail(memberRepository, email);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호를 확인해주세요.");
        }

        return new MemberResponseDto(member);
    }

    @Override
    public Long changePassword(MemberChangePasswordRequestDto requestDto) {
        String email = requestDto.getEmail();
        Member member = memberRepositoryHelper.findByEmail(memberRepository, email);

        String changedPassword = requestDto.getChangedPassword();
        member.changePassword(passwordEncoder.encode(changedPassword));

        return member.getId();
    }
}
