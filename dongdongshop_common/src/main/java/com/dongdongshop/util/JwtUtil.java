package com.dongdongshop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private String key = "dongke";
    private long ttl = 1000 * 60 * 60 * 24 * 30;

    /**
     * 生成jwt
     *
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(millis + ttl));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解密jwt
     * @param jwtStr
     * @return
     */
    public Claims parseJwt(String jwtStr) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
