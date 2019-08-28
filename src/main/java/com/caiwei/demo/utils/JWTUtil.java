package com.caiwei.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: JWTUtil
 * @Description: 生成 json web token工具类
 * @auther: caiwei
 * @date: 2019/6/24 23:42
 */
public class JWTUtil {

    private static SecretKey key;

    static {
        //创建JWT之前首先要创建一个base64URL需要的key
        String JWT_SECRET = "caiwei";
        byte[] encodeSecretKey = Base64.getUrlDecoder().decode(JWT_SECRET);
        key = new SecretKeySpec(encodeSecretKey, 0,encodeSecretKey.length,"AES");
    }

    public static String build(String username, String[] roles) {

        //然后根据这个secret创建jwt
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(now)
                .claim("phone", username)
                .claim("roles", roles)
                .setIssuer("caiwei")
                .setExpiration(DateUtil.getAppointedDateTimeByNow(TimeUnit.MINUTES, 30))
                .signWith(signatureAlgorithm, key);

        return builder.compact();
        /*String jwt = builder.compact();
        System.out.println("jwt:" + jwt);


        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        String name = (String) claims.get("name");
        String role = (String) claims.get("role");
        System.out.println("name:" + name + ";role:" + role);
*/
    }

    public static String build(String username) {

        //然后根据这个secret创建jwt
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(now)
                .claim("phone", username)
                .setIssuer("caiwei")
                .setExpiration(DateUtil.getAppointedDateTimeByNow(TimeUnit.MINUTES, 30))
                .signWith(signatureAlgorithm, key);

        return builder.compact();
        /*String jwt = builder.compact();
        System.out.println("jwt:" + jwt);


        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
        String name = (String) claims.get("name");
        String role = (String) claims.get("role");
        System.out.println("name:" + name + ";role:" + role);
*/
    }
}
