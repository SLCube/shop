package com.slcube.shop.business.address.repository;

import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.id = :addressId and a.member = :member")
    public Optional<Address> findByMemberAndAddressId(@Param("addressId") Long AddressId, @Param("member") Member member);

    @Query("select a from Address a where a.member = :member")
    public List<Address> findByMember(@Param("member") Member member);

    @Query("select count(a) from Address a where a.member = :member")
    public int countByMember(@Param("member") Member member);
}
