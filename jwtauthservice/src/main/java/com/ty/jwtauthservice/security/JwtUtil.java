package com.ty.jwtauthservice.security;

import com.ty.jwtauthservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${spring.secretKey}")
    private String secretKey;


    public String generateToken(User user){
        Map<String, Object> claims=new HashMap<>();

        claims.put("roles", List.of("ROLE "+user.getRole().name()));

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getEmail())
                .issuer("ty")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+10*60*1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey()
    {
        byte[] decode= Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(decode);
    }


    public String extractEmail(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {

        Claims claims=Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String userName=extractEmail(token);

        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return tokenExpiration(token).before(new Date());
    }

    private Date tokenExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

    public List<String> extractRoles(String token)
    {
        return extractClaims(token,claims -> claims.get("roles",List.class));
    }
}
