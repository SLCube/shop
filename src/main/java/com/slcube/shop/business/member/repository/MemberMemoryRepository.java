package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberMemoryRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;

    public Long save(Member member) {
        store.put(++sequence, member);
        return sequence;
    }

    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
