package com.slcube.shop.business.domain;

import com.slcube.shop.common.code.RowStatus;
import com.slcube.shop.common.code.UserLevel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated
    private UserLevel userLevel;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime signUpDate;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime signOutDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RowStatus isSignOut = RowStatus.N;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();
}