package com.slcube.shop.business.member.service;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.*;
import com.slcube.shop.business.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // Spring Security가 아직 적용 안된 service이다. 적용후에 어떤 과정을 거쳐 코드가 바뀔지 궁금하다.
    @Override
    public Long signUp(MemberSignUpRequestDto requestDto) {
        Member member = MemberMapper.toEntity(requestDto);
        return memberRepository.save(member).getId();
    }

    // 테스트를 수월하기 위해 일단 member pk로 member를 찾는 로직을 만들었다. 이부분도 나중에 어떻게 바뀔지 궁금하다.
    @Override
    public MemberResponseDto findMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        return new MemberResponseDto(member);
    }

    // 테스트의 편의를 위해 실제 코드에 메소드를 추가하는게 맞는걸까 다른 방법은 없을까
    @Override
    public MemberResponseDto login(MemberLoginDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        Member member = memberRepository.login(email, password)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        return new MemberResponseDto(member);
    }

    @Override
    public Long changePassword(MemberChangePasswordRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        member.changePassword(requestDto.getChangedPassword());

        return member.getId();
    }
}
