# Municipalidad - Microservicio de Autenticación (`ms-auth`)

Este microservicio se encarga de gestionar la autenticación y el control de acceso para el ecosistema digital de la Municipalidad. Está construido utilizando **Spring Boot** y se conecta a una base de datos relacional administrada en contenedores.

## 🚀 Logros y Funcionalidades Completadas

* **Persistencia de Datos Real:** Conexión establecida de extremo a extremo con una base de datos MySQL corriendo en **Docker**.
* **Control de Migraciones:** Configuración e integración de **Flyway** para la creación automática de tablas (`usuario`) e inyección de datos semilla en el contenedor.
* **Arquitectura Limpia en Capas:**
    * `Entity`: Mapeo matemático estricto de las columnas de la base de datos (Llave primaria `id_usuario`, campos únicos como `rut` y `email`).
    * `Repository`: Implementación de Spring Data JPA con consultas automáticas personalizadas (`findByUsername`).
    * `Service`: Cerebro de negocio encargado de verificar las credenciales de los funcionarios de forma dinámica.
    * `Controller`: Endpoint expuesto de manera segura para peticiones externas.
* **Seguridad Configurada:** Implementación de **Spring Security** protegiendo el sistema y liberando la ruta pública de acceso mediante políticas *Stateless*.

---

## 🛠️ Tecnologías Utilizadas

* **Java 17** / **Spring Boot 3.x**
* **Spring Data JPA** & **Hibernate**
* **Spring Security**
* **Flyway Database Migrations**
* **MySQL** (Dockerizado)
* **Postman** (Para pruebas de integración)

---

## 📡 Endpoints Disponibles

### 1. Inicio de Sesión (Login)
* **Ruta:** `POST /api/v1/auth/login`
* **Cuerpo de la Petición (JSON):**
    ```json
    {
        "username": "admin_muni",
        "password": "1234"
    }
    ```
* **Respuesta Exitosa (200 OK):**
    ```json
    {
        "status": "¡Autenticación Real Exitosa!",
        "rut": "11.111.111-1",
        "email": "admin@municipalidad.cl",
        "rol": "ROLE_ADMIN"
    }
    ```
* **Respuesta Fallida (401 Unauthorized):**
    ```json
    {
        "error": "Contraseña incorrecta"
    }
    ```

---

## ⚙️ Cómo Ejecutar el Proyecto

1.  Asegúrate de tener el contenedor de la base de datos corriendo en **Docker**:
    ```bash
    docker start <nombre_de_tu_contenedor_mysql>
    ```
2.  Lanza el microservicio desde tu IDE (VS Code) o mediante la terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
3.  Prueba el endpoint enviando una petición `POST` mediante **Postman** a `http://localhost:8080/api/v1/auth/login`.