package com.pokemoncenter.model;
 
/**
 * Interfaz que define el contrato de curación para los Pokémon.
* Cualquier Pokémon que pueda ser curado en el Centro Pokémon debe implementar esta interfaz.
 */
public interface Curable {
 
    /**
     * Cura al Pokémon, restaurando sus HP.
     */
    void curar();
}