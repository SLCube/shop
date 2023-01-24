package com.slcube.shop.business.member.domain;

import com.slcube.shop.business.cart.domain.Cart;
import com.slcube.shop.business.address.domain.Address;
import com.slcube.shop.business.order.domain.Order;
import com.slcube.shop.business.review.domain.Review;
import com.slcube.shop.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(insertable = false)
    private LocalDateTime signOutDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.MEMBER;

    @Column(nullable = false)
    private int point;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Cart cart;

    @Builder
    private Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void changePassword(String newPasssword) {
        password = newPasssword;
    }
}
