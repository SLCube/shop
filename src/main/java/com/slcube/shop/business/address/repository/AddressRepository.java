package com.slcube.shop.business.address.repository;

import com.slcube.shop.business.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.id = :addressId and a.member.id = :memberId")
    public Optional<Address> findByMemberIdAndAddressId(@Param("addressId") Long AddressId, @Param("memberId") Long memberId);

    @Query("select a from Address a where a.member.id = :memberId")
    public List<Address> findByMemberId(@Param("memberId") Long memberId);

    @Query("select count(a) from Address a where a.member.id = :memberId")
    public int countByMemberId(@Param("memberId") Long memberId);
}
