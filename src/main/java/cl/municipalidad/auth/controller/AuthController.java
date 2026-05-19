package cl.municipalidad.auth.controller;

import cl.municipalidad.auth.dto.request.DtoAuthRequest;
import cl.municipalidad.auth.dto.response.DtoAuthResponse;
import cl.municipalidad.auth.security.JwtUtil;
import cl.municipalidad.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil; // 🔑 Inyectamos tu nueva fábrica de tokens

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody DtoAuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        try {
            // Intentamos la conexión real a la base de datos
            Map<String, Object> resultado = authService.verificarCredenciales(username, password);

            if (resultado.containsKey("error")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultado);
            }

            // 🏭 Si las credenciales son válidas, fabricamos el Token JWT dinámico
            // Extraemos el rol del mapa (si no viene, por defecto asignamos ROLE_USER)
            String rol = resultado.containsKey("rol") ? resultado.get("rol").toString() : "ROLE_USER";
            
            // Extraemos el ID del usuario de forma segura
            Long idUsuario = resultado.containsKey("id") ? Long.parseLong(resultado.get("id").toString()) : 1L;

            // Generamos el string cifrado
            String tokenGenerado = jwtUtil.generarToken(username, rol, idUsuario);

            // 📦 Armamos tu respuesta estructurada con tu DtoAuthResponse actualizado
            DtoAuthResponse response = new DtoAuthResponse(
                    tokenGenerado,
                    username,
                    rol,
                    "AUTENTICADO_VIA_DATABASE"
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // SI LA BASE DE DATOS FALLA, ATRAPAMOS EL ERROR AQUÍ
            e.printStackTrace(); 
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Error interno al conectar con la Base de Datos",
                "detalle_tecnico", e.getMessage() != null ? e.getMessage() : e.toString()
            ));
        }
    }
}