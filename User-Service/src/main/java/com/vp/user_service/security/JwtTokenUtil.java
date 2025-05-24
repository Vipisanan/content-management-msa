package com.vp.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//    private final String SECRET_KEY = "your-secret-key";

    // Use a sufficiently long and random secret key (32+ chars for HS256)
    private static final String SECRET_KEY = "p1xZ&V7k2!f@rTgLmN0a#Qe9$yXhBwCmD4Uz";

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiry = new Date(nowMillis + 3600000); // 1 hour

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }
}

