package com.pokemoncenter.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Representa un registro en el historial de consultas realizadas
 * por un usuario al asistente de IA del Centro Pokémon.
 * Conecta directamente con la base de datos SQL para la persistencia.
 */
public class HistorialBusqueda {

    private int id;
    private Usuario usuario;
    private String consulta;
    private String respuestaIA;
    private Date fecha;

    /**
     * Constructor de HistorialBusqueda.
     */
    public HistorialBusqueda(int id, Usuario usuario, String consulta, String respuestaIA) {
        this.id = id;
        this.usuario = usuario;
        this.consulta = consulta;
        this.respuestaIA = respuestaIA;
        this.fecha = new Date();
    }

    /**
     * Guarda este registro en la base de datos SQL (CREATE).
     */
    public void guardar() {
        String sql = "INSERT INTO historial_busqueda (usuario_id, consulta, respuesta_ia, fecha) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, this.usuario.getId());
            ps.setString(2, this.consulta);
            ps.setString(3, this.respuestaIA);
            ps.setTimestamp(4, new Timestamp(this.fecha.getTime()));
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.id = generatedKeys.getInt(1);
                        System.out.println("Consulta guardada en SQL exitosamente con ID: " + this.id);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar en el historial SQL: " + e.getMessage());
        }
    }

    /**
     * Elimina este registro del historial en la base de datos (DELETE).
     */
    public void eliminar() {
        String sql = "DELETE FROM historial_busqueda WHERE id = ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, this.id);
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Registro #" + this.id + " eliminado del historial SQL.");
            } else {
                System.out.println("No se encontró el registro #" + this.id + " para eliminar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar del historial SQL: " + e.getMessage());
        }
    }

    /**
     * Obtiene y muestra todos los registros del historial de un usuario (READ).
     */
    public void obtenerPorUsuario(Usuario usuario) {
        String sql = "SELECT id, consulta, respuesta_ia, fecha FROM historial_busqueda WHERE usuario_id = ? ORDER BY fecha DESC";
        
        System.out.println("=== Historial de consultas de: " + usuario.getUsername() + " ===");
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, usuario.getId());
            try (ResultSet rs = ps.executeQuery()) {
                boolean tieneRegistros = false;
                while (rs.next()) {
                    tieneRegistros = true;
                    int regId = rs.getInt("id");
                    String q = rs.getString("consulta");
                    String r = rs.getString("respuesta_ia");
                    Timestamp f = rs.getTimestamp("fecha");
                    
                    System.out.println("[" + f + "] ID #" + regId);
                    System.out.println("Pregunta: " + q);
                    System.out.println("Respuesta: " + r);
                    System.out.println("----------------------------------------");
                }
                if (!tieneRegistros) {
                    System.out.println("No hay consultas registradas para este usuario.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el historial de SQL: " + e.getMessage());
        }
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
        return "[" + fecha + "] Usuario ID: " + usuario.getId() + " preguntó: " + consulta;
    }
}