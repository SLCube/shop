package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Long save(Member member);

    public Optional<Member> findById(Long memberId);
    public List<Member> findAll();
}
