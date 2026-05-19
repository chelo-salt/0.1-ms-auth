package cl.municipalidad.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Clave secreta de 256 bits para firmar los tokens de forma segura
    private final SecretKey CLAVE_SECRETA = Keys.hmacShaKeyFor(
            "ClaveUltraSecretaEInviolableParaLaMunicipalidad2026!".getBytes()
    );

    // El token vencerá en 2 horas (en milisegundos)
    private final long TIEMPO_EXPIRACION = 7200000; 

    /**
     * Generador de Tokens JWT
     */
    public String generarToken(String correo, String rol, Long idUsuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol);
        claims.put("idUsuario", idUsuario);

        return Jwts.builder()
                .claims(claims)
                .subject(correo)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .signWith(CLAVE_SECRETA)
                .compact();
    }
}