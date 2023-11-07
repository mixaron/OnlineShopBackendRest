package ru.mixaron.secuirty.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "m8LYfmRQD8lwfTDVEnVTNyy0h1xxLk7S";


    // Проверяет валидность токена
     public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

    // Извлекает юзернейм из клеймы
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    // нижние два в один

    // Проверяет токен на валидность по времени
    private boolean isTokenExpired(String token) {
        return extractExcpiration(token).before(new Date());
    }

    // Возвращает время токена
    private Date extractExcpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    // извлекает все клеймы для проверки подленности
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // извлекает одну клейму
    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }


    // нижние три на генерацию токена
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }




    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // сетим емейл
                .setIssuedAt(new Date(System.currentTimeMillis())) // дата создания
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // дата утсарвения
                .signWith(SignatureAlgorithm.HS256, getSignInKey()) // шифрует токен
                .compact(); // возвращает и гененрирует токен
    }

    // декодирует сикрет кей
    private Key getSignInKey() { //todo вроде получает зашифрованный сикрет кей
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)); // возвращает токен при регистрации, хотя оно не недао
    }
}
