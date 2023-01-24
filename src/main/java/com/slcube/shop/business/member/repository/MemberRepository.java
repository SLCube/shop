package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.memberStatus = :memberStatus")
    public Optional<Member> findByEmailAndMemberStatus(@Param("email") String email, @Param("memberStatus") MemberStatus memberStatus);
}
