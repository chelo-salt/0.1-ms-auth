package cl.municipalidad.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") // <-- Corregido: Coincide con tu SQL
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(name = "role", nullable = false) // <-- Corregido: Coincide con tu SQL (role)
    private String rol;

    // Métodos Getters y Setters actualizados
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}