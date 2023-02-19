package com.slcube.shop.business.address.domain;

import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.common.config.jpa.BooleanToYnConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String street;

    private String comment;

    @Column(nullable = false)
    @Convert(converter = BooleanToYnConverter.class)
    private Boolean isDefaultAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Address(String city, String zipcode, String street, String comment, boolean isDefaultAddress, Member member) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.comment = comment;
        this.isDefaultAddress = isDefaultAddress;
        this.member = member;
    }

    public void updateAddress(String city, String zipcode, String street, String comment, boolean isDefaultAddress) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.comment = comment;
        this.isDefaultAddress = isDefaultAddress;
    }
}
