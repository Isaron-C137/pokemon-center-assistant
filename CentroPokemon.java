package com.pokemoncenter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el Centro Pokémon donde los entrenadores pueden curar
 * a sus Pokémon, recibir pociones y registrar sus visitas.
 */
public class CentroPokemon {

    private String nombre;
    private String ciudad;
    private List<Pocion> inventarioPociones;

    /**
     * Constructor de CentroPokemon.
     *
     * @param nombre  Nombre del centro (ej: "Centro Pokémon de Pueblo Paleta")
     * @param ciudad  Ciudad donde está ubicado
     */
    public CentroPokemon(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.inventarioPociones = new ArrayList<>();
        inicializarInventario();
    }

    /**
     * Carga un inventario inicial de pociones al centro.
     */
    private void inicializarInventario() {
        inventarioPociones.add(new Pocion("Poción", 20));
        inventarioPociones.add(new Pocion("SuperPoción", 50));
        inventarioPociones.add(new Pocion("HiperPoción", 200));
    }

    /**
     * Cura a todos los Pokémon del equipo de un entrenador restaurando su HP al máximo.
     *
     * @param entrenador el entrenador cuyo equipo será curado
     */
    public void curarEquipo(Entrenador entrenador) {
        System.out.println("=== " + nombre + " ===");
        System.out.println("¡Bienvenido, " + entrenador.getNombre() + "! Curaremos a tus Pokémon.");
        for (Pokemon pokemon : entrenador.getEquipo()) {
            pokemon.curar();
        }
        System.out.println("¡Tus Pokémon han sido restaurados al máximo de salud!");
    }

    /**
     * Registra la visita de un entrenador al centro.
     *
     * @param entrenador el entrenador que visita el centro
     */
    public void registrarVisita(Entrenador entrenador) {
        System.out.println(entrenador.getNombre() + " visitó " + nombre + " en " + ciudad + ".");
    }

    /**
     * Entrega una poción del inventario a un entrenador.
     * Devuelve null si el inventario está vacío.
     *
     * @param indice posición de la poción en el inventario
     * @return la poción entregada, o null si no hay disponibles
     */
    public Pocion darPocion(int indice) {
        if (inventarioPociones.isEmpty()) {
            System.out.println("No hay pociones disponibles en el inventario.");
            return null;
        }
        if (indice < 0 || indice >= inventarioPociones.size()) {
            System.out.println("Índice de poción inválido.");
            return null;
        }
        Pocion pocion = inventarioPociones.get(indice);
        System.out.println("Se entregó: " + pocion.getInfo());
        return pocion;
    }

    /**
     * Muestra todas las pociones disponibles en el inventario.
     */
    public void mostrarInventario() {
        System.out.println("=== Inventario de Pociones ===");
        for (int i = 0; i < inventarioPociones.size(); i++) {
            System.out.println(i + ". " + inventarioPociones.get(i).getInfo());
        }
    }

    // ── Getters y Setters ──

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public List<Pocion> getInventarioPociones() { return inventarioPociones; }

    @Override
    public String toString() {
        return nombre + " — " + ciudad;
    }
}
