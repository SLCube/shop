package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.exception.CustomException;
import org.springframework.stereotype.Component;

import static com.slcube.shop.common.exception.CustomErrorCode.*;

@Component
public class MemberRepositoryHelper {

    public Member findByEmail(MemberRepository memberRepository, String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}
