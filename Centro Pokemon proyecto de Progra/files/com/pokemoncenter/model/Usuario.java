package com.pokemoncenter.model;

/**
 * Representa a un Usuario del sistema del Centro Pokémon.
 * Gestiona la autenticación y está asociado a un Entrenador.
 * Separa la lógica de acceso al sistema de la lógica del juego.
 */
public class Usuario {

    private int id;
    private String username;
    private String password;
    private String email;
    private Entrenador entrenador;

    /**
     * Constructor de Usuario.
     *
     * @param id       Identificador único del usuario
     * @param username Nombre de usuario para el login
     * @param password Contraseña del usuario
     * @param email    Correo electrónico del usuario
     */
    public Usuario(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Verifica las credenciales del usuario para iniciar sesión.
     *
     * @param username nombre de usuario ingresado
     * @param password contraseña ingresada
     * @return true si las credenciales son correctas
     */
    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Sesión iniciada correctamente. ¡Bienvenido, " + username + "!");
            return true;
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }

    /**
     * Cierra la sesión del usuario.
     */
    public void logout() {
        System.out.println("Sesión cerrada. ¡Hasta luego, " + username + "!");
    }

    // ── Getters y Setters ──

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    @Override
    public String toString() {
        return "Usuario: " + username + " | Email: " + email;
    }
}
