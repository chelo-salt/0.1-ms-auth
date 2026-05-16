-- Insertar usuarios demo para pruebas del sistema municipal
INSERT INTO usuario (rut, username, email, password, role, enabled)
VALUES ('11.111.111-1', 'admin_muni', 'admin@municipalidad.cl', '1234', 'ROLE_ADMIN', true);

INSERT INTO usuario (rut, username, email, password, role, enabled)
VALUES ('22.222.222-2', 'juan_encargado', 'juan.canchas@municipalidad.cl', '1234', 'ROLE_ENCARGADO', true);

INSERT INTO usuario (rut, username, email, password, role, enabled)
VALUES ('33.333.333-3', 'diego_vecino', 'diego.ciudadano@gmail.com', '1234', 'ROLE_CIUDADANO', true);