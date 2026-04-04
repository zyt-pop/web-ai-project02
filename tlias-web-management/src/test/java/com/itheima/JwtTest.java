package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 生成JWT
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username","tom");
        dataMap.put("password","123");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
        System.out.println(jwt);
    }
    /**
     * 解析JWT
     */
    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMyIsInVzZXJuYW1lIjoidG9tIiwiZXhwIjoxNzc0OTQ3MjU2fQ.kjMWb522PysOSmeceEqBNrvVM_OKqUK8T7Xn5ZEMfac";
        Claims claims = Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println(claims);

    }
}
