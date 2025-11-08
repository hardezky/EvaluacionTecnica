package com.pruebatecnica.auth.security;



import com.pruebatecnica.auth.entity.Empleado;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {


    private static final String SECRET_KEY = "verySecretKeyChangeMe1234567890verySecretKeyChangeMe1234567890";

    private Key getSigningKey() {
        // Convierte la clave a un objeto Key válido
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Empleado empleado) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", empleado.getId());
        claims.put("nombre", empleado.getNombre());
        claims.put("usuario", empleado.getUsuario());
        claims.put("rol", empleado.getRol());
        claims.put("idSucursal", empleado.getSucursal().getId());
        claims.put("nombreSucursal", empleado.getSucursal().getNombre());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(empleado.getUsuario())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        System.out.println("🔑 Token generado: " + token);
        return token;
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}