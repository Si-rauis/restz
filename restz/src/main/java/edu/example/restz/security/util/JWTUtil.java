package edu.example.restz.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
@Log4j2
public class JWTUtil {
    private static String key //서명에 사용할 키 - 30자 이상으로
        = "1234567890123456789012345678901234567890";

    //JWT 문자열 생성                           //저장 문자열, 만료 시간 - 분 단위
    public String createToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor( JWTUtil.key.getBytes("UTF-8") );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Date now = new Date();  //토큰 발행 시간
        return Jwts.builder().header().add("alg", "HS256").add("type", "JWT")
                             .and()
                             .issuedAt(now)     //토큰 발행 시간
                             .expiration(       //토큰 만료 시간
                                 new Date( now.getTime() +
                                           Duration.ofMinutes(min).toMillis()) )
                             .claims(valueMap)  //저장 데이터
                             .signWith(key)     //서명
                             .compact();
    }

    //검증 기능 수행
    public Map<String, Object> validateToken(String token) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor( JWTUtil.key.getBytes("UTF-8") );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Claims claims = Jwts.parser().verifyWith(key).build()
                            .parseSignedClaims(token).getPayload();
        log.info("--- claims : " + claims);

        return claims;
    }
}

















