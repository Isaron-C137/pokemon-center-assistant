package com.pokemoncenter.model;

/**
 * Representa un Pokémon de tipo Fuego.
 * Extiende Pokemon y agrega atributos y movimientos propios del tipo Fuego.
 */
public class PokemonFuego extends Pokemon {

    private int ataque;

    /**
     * Constructor de PokemonFuego.
     *
     * @param nombre  Nombre del Pokémon
     * @param nivel   Nivel del Pokémon
     * @param hp      Puntos de vida del Pokémon
     * @param ataque  Estadística de ataque del tipo Fuego
     */
    public PokemonFuego(String nombre, int nivel, int hp, int ataque) {
        super(nombre, nivel, hp, "Fuego");
        this.ataque = ataque;
    }

    /**
     * Usa el movimiento Lanzallamas — ataque de fuego sostenido.
     */
    public void lanzaLlamas() {
        System.out.println(getNombre() + " usó Lanzallamas! Ráfaga de fuego abrasador.");
    }

    /**
     * Usa el movimiento Colmillo Fuego — mordida de combate cercano con fuego.
     */
    public void colmilloFuego() {
        System.out.println(getNombre() + " usó Colmillo Fuego! Mordida ardiente.");
    }

    /**
     * Curación tipo Fuego — enfriamiento cuidadoso en el Centro Pokémon.
     */
    @Override
    public void curar() {
        super.curar();
        System.out.println(getNombre() + " fue enfriado con cuidado en el Centro Pokémon.");
    }

    /**
     * Calcula el daño considerando la estadística de ataque del tipo Fuego.
     *
     * @return cantidad de daño
     */
    @Override
    protected int calcularDano() {
        return getNivel() * 5 + ataque;
    }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
}
