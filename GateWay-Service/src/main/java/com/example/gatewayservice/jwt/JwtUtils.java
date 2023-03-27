package com.example.gatewayservice.jwt;

import com.example.gatewayservice.exceptionhandle.ErrorCus;
import com.example.gatewayservice.exceptionhandle.JwtTokenMalformedException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {
    private String jwtSecret = "dhfksjdhsjdhfkjshgjksdhgksdgfhsdfghdfjfgjfgjhdfgjfhkfgjdgjfkgjlkfhjdfhg" +
            "dfhfgkhfghdfhdfjkfgjdfghdfhfgkfgjfghfgfhfjfgjdfgdfhdfhfgjghkghkghjghkjghtyurtyeryturth";
    private int jwtExp = 600000;

    public String generateJwt(String username) {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration((new Date((new Date()).getTime() + jwtExp)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserFromToken(String token) {

        return getClaimsFromToken(token, Claims -> Claims.getSubject());
    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> function) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return function.apply(claims);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public boolean validateJwtToken(String authToken) throws JwtTokenMalformedException {
        try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new JwtTokenMalformedException(e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw new JwtTokenMalformedException(e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw new JwtTokenMalformedException(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());

        }
        return false;
    }
}
