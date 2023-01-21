package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberRepositoryHelper {

    public Member findByEmail(MemberRepository memberRepository, String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호를 확인해주세요."));
    }
}
