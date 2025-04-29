package br.edu.utfpr.api.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt_secret}")
    private String secret;

    @Value("${jwt_expires}")
    private long expirationMs;

    private Key key; // ? Aqui vamos armazenar a chave gerada

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims validateToken(String token) throws ServletException, IOException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getEncoded()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new RuntimeException("Token expirado");
        } catch (Exception e) {
            throw new RuntimeException("Token inválido");
        }
    }
}
