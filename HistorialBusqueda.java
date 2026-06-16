package com.pokemoncenter.model;

import java.util.Date;

/**
 * Representa un registro en el historial de consultas realizadas
 * por un usuario al asistente de IA del Centro Pokémon.
 * Cada objeto corresponde a una fila en la tabla SQL de historial.
 */
public class HistorialBusqueda {

    private int id;
    private Usuario usuario;
    private String consulta;
    private String respuestaIA;
    private Date fecha;

    /**
     * Constructor de HistorialBusqueda.
     *
     * @param id          Identificador único del registro
     * @param usuario     Usuario que realizó la consulta
     * @param consulta    Texto de la consulta enviada a la IA
     * @param respuestaIA Respuesta recibida de la IA
     */
    public HistorialBusqueda(int id, Usuario usuario, String consulta, String respuestaIA) {
        this.id = id;
        this.usuario = usuario;
        this.consulta = consulta;
        this.respuestaIA = respuestaIA;
        this.fecha = new Date();
    }

    /**
     * Guarda este registro en la base de datos SQL.
     * La lógica de conexión se implementa en la capa de persistencia (DAO).
     */
    public void guardar() {
        System.out.println("Guardando consulta en historial: \"" + consulta + "\"");
    }

    /**
     * Elimina este registro del historial en la base de datos.
     */
    public void eliminar() {
        System.out.println("Registro #" + id + " eliminado del historial.");
    }

    /**
     * Obtiene todos los registros del historial de un usuario.
     * La lógica real se implementa en la capa DAO con una consulta SQL.
     *
     * @param usuario el usuario del que se desea obtener el historial
     */
    public void obtenerPorUsuario(Usuario usuario) {
        System.out.println("Obteniendo historial de consultas del usuario: " + usuario.getUsername());
    }

    // ── Getters y Setters ──

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getConsulta() { return consulta; }
    public void setConsulta(String consulta) { this.consulta = consulta; }

    public String getRespuestaIA() { return respuestaIA; }
    public void setRespuestaIA(String respuestaIA) { this.respuestaIA = respuestaIA; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "[" + fecha + "] " + usuario.getUsername() + " preguntó: " + consulta;
    }
}
