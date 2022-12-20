package com.slcube.shop.business.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
