package com.rtc.app.auth.service.authentication;

import com.rtc.app.auth.dto.internal.RefreshTokenWithExpire;
import com.rtc.app.auth.dto.internal.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtService {

    private final long expirationMs;
    private final long refreshExpirationMs;
    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expirationMs}") long expirationMs,
                      @Value("${jwt.refreshExpirationMs}") long refreshExpirationMs) {

        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    public String generateAccessToken(Long id, String email, String type) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID는 필수 값입니다.");
        }

        if (Objects.isNull(email) || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수 값입니다.");
        }

        if (Objects.isNull(type) || type.isBlank()) {
            throw new IllegalArgumentException("유저 타입은 필수 값입니다.");
        }

        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .signWith(key)
                .header().add("typ", "JWT").and()
                .subject(id.toString())
                .issuedAt(now)
                .expiration(exp)
                .claim("email", email)
                .claim("type", type)
                .compact();
    }

    public RefreshTokenWithExpire generateRefreshToken(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID는 필수 값입니다.");
        }

        Date now = new Date();
        Date exp = new Date(now.getTime() + refreshExpirationMs);

        return new RefreshTokenWithExpire(Jwts.builder()
                .signWith(key)
                .header().add("typ", "JWT").and()
                .subject(id.toString())
                .issuedAt(now)
                .expiration(exp)
                .compact(), exp);
    }

    public UserInfo getUserInfoFromAccessToken(String token) {
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        String id = payload.getSubject();
        String email = payload.get("email", String.class);
        String type = payload.get("type", String.class);

        return new UserInfo(Long.valueOf(id), email, type);
    }
}
