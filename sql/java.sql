CREATE DATABASE IF NOT EXISTS centro_pokemon;
USE centro_pokemon;

CREATE TABLE IF NOT EXISTS historial_busqueda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    consulta TEXT NOT NULL,
    respuesta_ia TEXT NOT NULL,
    fecha DATETIME NOT NULL
);
