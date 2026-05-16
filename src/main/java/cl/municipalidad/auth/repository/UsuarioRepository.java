package cl.municipalidad.auth.repository;

import cl.municipalidad.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Agregamos este método clave para que Spring busque al usuario por su Username en MySQL
    Optional<Usuario> findByUsername(String username);
}