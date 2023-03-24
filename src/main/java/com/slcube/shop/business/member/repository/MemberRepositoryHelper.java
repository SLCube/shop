package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberRepositoryHelper {

    private final MemberRepository memberRepository;

    public Member findByEmailAndMemberStatus(String email, MemberStatus memberStatus) {
        return memberRepository.findByEmailAndMemberStatus(email, memberStatus);
    }
}
