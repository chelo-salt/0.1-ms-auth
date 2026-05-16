package cl.municipalidad.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cl.municipalidad.auth.configuration.JwtProperties;
import cl.municipalidad.auth.model.UsuarioModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    // Cambiamos los parámetros para recibir el objeto UsuarioModel completo
    public String generarToken(UsuarioModel usuario) {

        return JWT.create()
                // El dueño del token sigue siendo el username
                .withSubject(usuario.getUsername())

                // Cambiamos el emisor para que coincida con nuestra arquitectura municipal
                .withIssuer("ms-auth")

                // Mantenemos la lógica del profesor para los roles
                .withClaim("roles", List.of("ROLE_" + usuario.getRole()))

                // ¡Agregamos RUT y Email dentro del token para que sirva en las reservas!
                .withClaim("rut", usuario.getRut())
                .withClaim("email", usuario.getEmail())

                .withIssuedAt(new Date())
                .withExpiresAt(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtProperties.getExpiration()))
                
                .sign(
                        Algorithm.HMAC256(
                                jwtProperties.getSecret()));
    }
}