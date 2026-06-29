package com.pokemoncenter.model;

/**
 * Representa un Pokémon de tipo Agua.
 * Extiende Pokemon y agrega atributos y movimientos propios del tipo Agua.
 */
public class PokemonAgua extends Pokemon {

    private int defensa;

    /**
     * Constructor de PokemonAgua.
     *
     * @param nombre   Nombre del Pokémon
     * @param nivel    Nivel del Pokémon
     * @param hp       Puntos de vida del Pokémon
     * @param defensa  Estadística de defensa del tipo Agua
     */
    public PokemonAgua(String nombre, int nivel, int hp, int defensa) {
        super(nombre, nivel, hp, "Agua");
        this.defensa = defensa;
    }

    /**
     * Usa el movimiento Surf — inflige daño a todos los oponentes.
     */
    public void surfear() {
        System.out.println(getNombre() + " usó Surf! Una ola gigante arrasa con todo.");
    }

    /**
     * Usa el movimiento Hidrobomba — ataque de agua de alto poder.
     */
    public void hidrobomba() {
        System.out.println(getNombre() + " usó Hidrobomba! Un chorro de agua a alta presión.");
    }

    /**
     * Curación tipo Agua — restaura HP adicional gracias a la alta defensa.
     */
    @Override
    public void curar() {
        super.curar();
        System.out.println(getNombre() + " recuperó energía adicional gracias a su alta defensa.");
    }

    /**
     * Calcula el daño considerando la estadística de defensa del tipo Agua.
     *
     * @return cantidad de daño
     */
    @Override
    protected int calcularDano() {
        return getNivel() * 5 + (defensa / 2);
    }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
}
