package com.phrms.HealthCareSystem.util;

import com.phrms.HealthCareSystem.entity.RefreshToken;
import com.phrms.HealthCareSystem.entity.User;
import com.phrms.HealthCareSystem.repository.RefreshTokenRepository;
import com.phrms.HealthCareSystem.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${service.secretKey}")
    private String SECRET_KEY;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;

    public String generateToken(User userDetails) {
        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(userDetails.getAadharNumber()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(User userDetails) {
        String refreshToken = Jwts.builder()
                .setClaims(Jwts.claims().setSubject(userDetails.getAadharNumber()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // Save the refresh token in MongoDB
        RefreshToken tokenDocument = new RefreshToken(userService.patientExist(userDetails.getAadharNumber()).getId(), refreshToken,
                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)); // 7 days expiration
        refreshTokenRepository.save(tokenDocument);

        return refreshToken;
    }

    public boolean validateToken(String token, User userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getAadharNumber()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
