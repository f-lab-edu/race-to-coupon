package com.rtc.app.auth.entity;

import com.rtc.app.common.entity.BaseTimeEntity;
import com.rtc.app.auth.type.UserType;
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
    @Column(name = "coupon_user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "name", nullable = false, length = 30, unique = true)
    private String name;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserType type;

    // 유저 생성
    public static User create(String email, String name, String password) {
        return new User(email, name, password, UserType.USER);
    }

    private User(String email, String name, String password, UserType type) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
    }
}
