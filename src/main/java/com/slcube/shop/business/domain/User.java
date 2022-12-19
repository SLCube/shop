package com.slcube.shop.business.domain;

import com.slcube.shop.common.code.RowStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDateTime signUpDate;

    @Column(insertable = false)
    private LocalDateTime signOutDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RowStatus isSignOut = RowStatus.N;


}
