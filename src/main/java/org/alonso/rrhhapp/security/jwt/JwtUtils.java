package org.alonso.rrhhapp.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.alonso.rrhhapp.models.entities.UserEntity;
import org.alonso.rrhhapp.models.exceptions.UserNotFoundException;
import org.alonso.rrhhapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    public String generateAccessToken(String username) {
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("The user entered is not found"));

        List<String> roles = user.getRoles().stream().map((role) -> role.getName()).collect(Collectors.toList());
        boolean isAdmin = roles.stream().anyMatch(role -> role.equals("ROLE_ADMIN"));

        Claims claims = Jwts.claims();
        claims.put("authorities", roles);
        claims.put("isAdmin", isAdmin);
        claims.put("username", username);
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            log.error("Invalid token. Error: " + e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = extractClaims(token);
        return claimsFunction.apply(claims);
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
