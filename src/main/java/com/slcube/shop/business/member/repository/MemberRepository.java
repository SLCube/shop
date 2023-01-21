package com.slcube.shop.business.member.repository;

import com.slcube.shop.business.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.isSignOut = false")
    public Optional<Member> findByEmail(@Param("email") String email);
}
