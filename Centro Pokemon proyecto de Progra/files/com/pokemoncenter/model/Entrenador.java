package com.pokemoncenter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un Entrenador Pokémon dentro del universo del juego.
 * Cada entrenador puede tener hasta 6 Pokémon en su equipo
 * y puede iniciar batallas contra otros entrenadores.
 */
public class Entrenador {

    private static final int MAXIMO_EQUIPO = 6;

    private int id;
    private String nombre;
    private List<Pokemon> equipo;

    /**
     * Constructor de Entrenador.
     *
     * @param id     Identificador único del entrenador
     * @param nombre Nombre del entrenador
     */
    public Entrenador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
    }

    /**
     * Agrega un Pokémon al equipo del entrenador.
     * El equipo tiene un máximo de 6 Pokémon.
     *
     * @param pokemon el Pokémon a agregar
     */
    public void agregarPokemon(Pokemon pokemon) {
        if (equipo.size() >= MAXIMO_EQUIPO) {
            System.out.println("El equipo ya está completo. No se puede agregar " + pokemon.getNombre() + ".");
            return;
        }
        equipo.add(pokemon);
        System.out.println(pokemon.getNombre() + " fue agregado al equipo de " + nombre + ".");
    }

    /**
     * Elimina un Pokémon del equipo por su posición.
     *
     * @param indice posición del Pokémon en el equipo (0-5)
     */
    public void eliminarPokemon(int indice) {
        if (indice < 0 || indice >= equipo.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Pokemon eliminado = equipo.remove(indice);
        System.out.println(eliminado.getNombre() + " fue eliminado del equipo de " + nombre + ".");
    }

    /**
     * Devuelve la lista de Pokémon del equipo del entrenador.
     *
     * @return lista de Pokémon
     */
    public List<Pokemon> verEquipo() {
        return equipo;
    }

    /**
     * Muestra en consola el estado completo del equipo.
     */
    public void mostrarEquipo() {
        System.out.println("=== Equipo de " + nombre + " ===");
        if (equipo.isEmpty()) {
            System.out.println("El equipo está vacío.");
            return;
        }
        for (int i = 0; i < equipo.size(); i++) {
            System.out.println((i + 1) + ". " + equipo.get(i));
        }
    }

    /**
     * Inicia una batalla contra otro entrenador.
     *
     * @param rival el entrenador rival
     * @return objeto Batalla listo para iniciar
     */
    public Batalla iniciarBatalla(Entrenador rival) {
        System.out.println(nombre + " desafió a " + rival.getNombre() + " a una batalla!");
        return new Batalla(this, rival);
    }

    /**
     * Verifica si el entrenador tiene al menos un Pokémon que no esté debilitado.
     *
     * @return true si puede seguir batallando
     */
    public boolean puedeCombatr() {
        for (Pokemon p : equipo) {
            if (!p.estaDebilitado()) return true;
        }
        return false;
    }

    // ── Getters y Setters ──

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Pokemon> getEquipo() { return equipo; }
    public void setEquipo(List<Pokemon> equipo) { this.equipo = equipo; }

    @Override
    public String toString() {
        return "Entrenador: " + nombre + " | Pokémon en equipo: " + equipo.size();
    }
}
