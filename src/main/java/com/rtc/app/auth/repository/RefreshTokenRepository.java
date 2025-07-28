package com.rtc.app.auth.repository;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RefreshTokenRepository {

    private final String REFRESH_TOKEN_PREFIX = "refreshToken:";

    private final RedissonClient client;

    public void save(String refreshToken, Long userId, Duration ttl) {
        RBucket<String> bucket = client.getBucket(REFRESH_TOKEN_PREFIX + userId);
        bucket.set(refreshToken, ttl);
    }

    public void delete(Long userId) {
        RBucket<String> bucket = client.getBucket(REFRESH_TOKEN_PREFIX + userId);
        bucket.delete();
    }
}