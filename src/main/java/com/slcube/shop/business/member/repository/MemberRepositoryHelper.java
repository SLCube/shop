package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import com.slcube.shop.common.exception.MemberNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MemberRepositoryHelper {

    public Member findByEmailAndMemberStatus(MemberRepository memberRepository, String email, MemberStatus memberStatus) {
        return memberRepository.findByEmailAndMemberStatus(email, memberStatus)
                .orElseThrow(() -> new MemberNotFoundException());
    }
}
