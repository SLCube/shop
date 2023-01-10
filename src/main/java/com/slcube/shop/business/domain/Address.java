package com.slcube.shop.business.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private boolean isDefaultAddress;
}
