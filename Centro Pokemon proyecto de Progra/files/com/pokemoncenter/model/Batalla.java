package com.pokemoncenter.model;

/**
 * Representa una batalla entre dos entrenadores Pokémon.
 * Gestiona los turnos de ataque y determina al ganador.
 */
public class Batalla {

    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private String resultado;

    /**
     * Constructor de Batalla.
     *
     * @param entrenador1 primer entrenador (retador)
     * @param entrenador2 segundo entrenador (rival)
     */
    public Batalla(Entrenador entrenador1, Entrenador entrenador2) {
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.resultado = "Sin resultado";
    }

    /**
     * Inicia la batalla entre los dos entrenadores.
     * Los primeros Pokémon de cada equipo se enfrentan en turnos alternados.
     */
    public void iniciar() {
        System.out.println("=== ¡La batalla comienza! ===");
        System.out.println(entrenador1.getNombre() + " vs " + entrenador2.getNombre());

        if (entrenador1.getEquipo().isEmpty() || entrenador2.getEquipo().isEmpty()) {
            System.out.println("Uno de los entrenadores no tiene Pokémon. Batalla cancelada.");
            return;
        }

        Pokemon pokemon1 = entrenador1.getEquipo().get(0);
        Pokemon pokemon2 = entrenador2.getEquipo().get(0);

        System.out.println(entrenador1.getNombre() + " envía a " + pokemon1.getNombre() + "!");
        System.out.println(entrenador2.getNombre() + " envía a " + pokemon2.getNombre() + "!");

        int turno = 1;
        while (!pokemon1.estaDebilitado() && !pokemon2.estaDebilitado()) {
            System.out.println("\n--- Turno " + turno + " ---");
            if (turno % 2 != 0) {
                pokemon1.atacar(pokemon2);
            } else {
                pokemon2.atacar(pokemon1);
            }
            turno++;
            if (turno > 20) {
                resultado = "Empate por límite de turnos";
                System.out.println("Se alcanzó el límite de turnos. ¡Empate!");
                return;
            }
        }

        terminar(pokemon1, pokemon2);
    }

    /**
     * Finaliza la batalla y determina al ganador.
     *
     * @param pokemon1 Pokémon del entrenador 1
     * @param pokemon2 Pokémon del entrenador 2
     * @return mensaje con el resultado de la batalla
     */
    public String terminar(Pokemon pokemon1, Pokemon pokemon2) {
        System.out.println("\n=== ¡Fin de la batalla! ===");
        if (pokemon1.estaDebilitado()) {
            resultado = entrenador2.getNombre() + " ganó la batalla!";
        } else {
            resultado = entrenador1.getNombre() + " ganó la batalla!";
        }
        System.out.println(resultado);
        return resultado;
    }

    // ── Getters y Setters ──

    public Entrenador getEntrenador1() { return entrenador1; }
    public void setEntrenador1(Entrenador entrenador1) { this.entrenador1 = entrenador1; }

    public Entrenador getEntrenador2() { return entrenador2; }
    public void setEntrenador2(Entrenador entrenador2) { this.entrenador2 = entrenador2; }

    public String getResultado() { return resultado; }

    @Override
    public String toString() {
        return entrenador1.getNombre() + " vs " + entrenador2.getNombre() + " | " + resultado;
    }
}
