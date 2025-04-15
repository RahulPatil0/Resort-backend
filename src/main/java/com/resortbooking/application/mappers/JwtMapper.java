package com.resortbooking.application.mappers;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtMapper {

    // 🔐 Secret key loaded from application.properties or environment variable
    @Value("${jwt.secret}")
    private String secretKey;

    // 📌 Generate a JWT token based on the username and roles
    public String generateToken(String username, List<String> roles) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setSubject(username) // token subject (typically the username or userId)
                .claim("roles", roles) // Add roles as a claim in the token
                .setIssuedAt(new Date()) // issue time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // expiration time (10 hours)
                .signWith(key, SignatureAlgorithm.HS256) // sign with HS256 and secret key
                .compact();
    }

    // 🔍 Extract username (subject) from the JWT token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔍 Extract roles from the JWT token
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class); // Extract roles as a List from the claims
    }

    // ✅ Validate the token based on username and expiration
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 🧠 Get all claims from the token
    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // ⏳ Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 📝 Check if the token is valid based on username
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername != null && extractedUsername.equals(username)) && !isTokenExpired(token);
    }
}
