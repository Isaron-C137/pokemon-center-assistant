package com.pokemoncenter.model;
 
/**
 * Representa un Pokémon de tipo Planta.
 * Extiende Pokemon y agrega atributos y movimientos propios del tipo Planta.
 */
public class PokemonPlanta extends Pokemon {
 
    private int velocidad;
 
    /**
     * Constructor de PokemonPlanta.
     *
     * @param nombre     Nombre del Pokémon
     * @param nivel      Nivel del Pokémon
     * @param hp         Puntos de vida del Pokémon
     * @param velocidad  Estadística de velocidad del tipo Planta
     */
    public PokemonPlanta(String nombre, int nivel, int hp, int velocidad) {
        super(nombre, nivel, hp, "Planta");
        this.velocidad = velocidad;
    }
 
    /**
     * Usa el movimiento Látigo Cepa — ataque rápido con enredaderas.
     */
    public void latigo() {
        System.out.println(getNombre() + " usó Látigo Cepa! Enredaderas que golpean rápido.");
    }
 
    /**
     * Usa el movimiento Rayo Solar — carga energía solar y lanza un rayo devastador.
     */
    public void rayoSolar() {
        System.out.println(getNombre() + " usó Rayo Solar! Absorbe luz y lanza un rayo devastador.");
    }
 
    /**
     * Curación tipo Planta — absorbe energía solar para recuperarse.
     */
    @Override
    public void curar() {
        super.curar();
        System.out.println(getNombre() + " absorbió energía solar para recuperarse más rápido.");
    }
 
    /**
     * Calcula el daño considerando la estadística de velocidad del tipo Planta.
     *
     * @return cantidad de daño
     */
    @Override
    protected int calcularDano() {
        return getNivel() * 5 + (velocidad / 2);
    }
 
    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}