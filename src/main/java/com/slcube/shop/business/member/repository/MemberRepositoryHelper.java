package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.exception.MemberNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MemberRepositoryHelper {

    public Member findByEmail(MemberRepository memberRepository, String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException());
    }
}
