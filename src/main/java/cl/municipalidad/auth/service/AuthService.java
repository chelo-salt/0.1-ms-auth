package cl.municipalidad.auth.service;

import cl.municipalidad.auth.entity.Usuario;
import cl.municipalidad.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Map<String, Object> verificarCredenciales(String username, String password) {
        // 1. Buscamos al usuario en MySQL usando el repositorio
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        // Si no existe, preparamos un mapa con el error
        if (usuarioOpt.isEmpty()) {
            return Map.of("error", "Usuario no encontrado en el sistema municipal");
        }

        Usuario usuario = usuarioOpt.get();

        // 2. [ACTIVADO]: Validación real y estricta de la contraseña
        if (!usuario.getPassword().equals(password)) {
            return Map.of("error", "Contraseña incorrecta");
        }

        // 3. Si todo coincide, retornamos sus datos reales de la BD de forma segura
        return Map.of(
            "status", "¡Autenticación Real Exitosa!",
            "rut", usuario.getRut(),
            "email", usuario.getEmail(),
            "rol", usuario.getRol()
        );
    }
}