package com.slcube.shop.business.member.domain;

import com.slcube.shop.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity implements Serializable {

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
