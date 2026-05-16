package cl.municipalidad.auth.controller;

import cl.municipalidad.auth.service.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        try {
            // Intentamos la conexión real a la base de datos
            Map<String, Object> resultado = authService.verificarCredenciales(username, password);

            if (resultado.containsKey("error")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultado);
            }

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            // SI LA BASE DE DATOS FALLA, ATRAPAMOS EL ERROR AQUÍ
            // Esto imprimirá el error real en tu terminal de VS Code
            e.printStackTrace(); 
            
            // Y te lo mostrará en Postman para saber qué pasa
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Error interno al conectar con la Base de Datos",
                "detalle_tecnico", e.getMessage() != null ? e.getMessage() : e.toString()
            ));
        }
    }
}