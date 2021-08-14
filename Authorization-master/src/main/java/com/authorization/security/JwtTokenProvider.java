package com.authorization.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.authorization.pojo.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.SECRETKEY}")
    private String SECRETKEY;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(LoginRequest loginRequest) {

    	
        Date expiryDate = new Date(new Date().getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(loginRequest.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRETKEY)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRETKEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
            	.setSigningKey(SECRETKEY)
            	.parseClaimsJws(authToken);
            
            return true;
        } 
        catch (SignatureException ex) {
        	log.error("Invalid JWT signature");
        	throw new SignatureException("Invalid JWT signature");
        } 
        catch (MalformedJwtException ex) {
        	log.error("Invalid JWT token");
        	throw new MalformedJwtException("Invalid JWT token");
        } 
        catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ExpiredJwtException(null, null, "Expired JWT Token");
        } 
        catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new UnsupportedJwtException("Unsupported JWT token");
        } 
        catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
        
    }
}
