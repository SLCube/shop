package com.slcube.shop.business.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

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

    @CreatedDate
    private LocalDateTime signUpDate;

    private LocalDateTime signOutDate;

    @Column(nullable = false)
    private boolean isSignOut;

    @Column(nullable = false)
    private int point;

    @OneToMany(mappedBy = "member")
    private List<Address> addresses = new ArrayList<>();


}
