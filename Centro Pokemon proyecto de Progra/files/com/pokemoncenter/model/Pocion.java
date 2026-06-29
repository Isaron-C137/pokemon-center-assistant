package com.pokemoncenter.model;

/**
 * Representa una Poción que puede ser usada para curar a un Pokémon.
 * Puede ser de diferentes tipos: Poción, SuperPoción, HiperPoción.
 */
public class Pocion {

    private String tipo;
    private int recuperacion;

    /**
     * Constructor de Pocion.
     *
     * @param tipo         Tipo de poción (ej: "Poción", "SuperPoción", "HiperPoción")
     * @param recuperacion Cantidad de HP que restaura
     */
    public Pocion(String tipo, int recuperacion) {
        this.tipo = tipo;
        this.recuperacion = recuperacion;
    }

    /**
     * Aplica la poción a un Pokémon, restaurando sus puntos de vida.
     *
     * @param pokemon el Pokémon al que se le aplica la poción
     */
    public void aplicar(Pokemon pokemon) {
        int hpAntes = pokemon.getHp();
        int hpNuevo = Math.min(pokemon.getHpMaximo(), pokemon.getHp() + recuperacion);
        pokemon.setHp(hpNuevo);
        System.out.println(tipo + " usada en " + pokemon.getNombre() + ". HP: " + hpAntes + " → " + hpNuevo + ".");
    }

    /**
     * Devuelve información de la poción en formato legible.
     *
     * @return descripción de la poción
     */
    public String getInfo() {
        return tipo + " (Restaura " + recuperacion + " HP)";
    }

    // ── Getters y Setters ──

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getRecuperacion() { return recuperacion; }
    public void setRecuperacion(int recuperacion) { this.recuperacion = recuperacion; }

    @Override
    public String toString() {
        return getInfo();
    }
}
