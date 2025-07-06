package com.rtc.app.account.entity;

import com.rtc.app.common.entity.BaseTimeEntity;
import com.rtc.app.account.type.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_key", nullable = false, updatable = false)
    private Long userKey;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "name", nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserType type;
}
