package com.caiwei.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName: JWTAuthenticationFilter
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/6/26 00:19
 */
@Component
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final static String HEADER = "Authorization";
    private final static String BEARER = "Bearer ";
    private final static String loginUrl = "/user/login";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String username =
                    (String)Jwts.parser()
                    .setSigningKey("caiwei")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .get("name");
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null);
            }
            return null;
        }
        return null;
    }



}
