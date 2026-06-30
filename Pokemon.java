package com.pokemoncenter.model;

/**
 * Representa un Pokémon en el Centro Pokémon.
 * Cada Pokémon tiene atributos de combate, puntos de vida y puede atacar a otros Pokémon.
 * Implementa ICurable para poder ser curado con pociones.
 */
public class Pokemon implements ICurable {

    private int id;
    private String nombre;
    private String tipo;
    private int nivel;
    private int hp;
    private int hpMaximo;
    private int ataque;
    private int defensa;
    private int velocidad;

    /**
     * Constructor de Pokemon.
     *
     * @param id        Identificador único del Pokémon
     * @param nombre    Nombre del Pokémon
     * @param tipo      Tipo del Pokémon (ej: "Fuego", "Agua", "Planta")
     * @param nivel     Nivel del Pokémon
     * @param hp        Puntos de vida actuales
     * @param hpMaximo  Puntos de vida máximos
     * @param ataque    Poder de ataque
     * @param defensa   Poder de defensa
     * @param velocidad Velocidad en combate
     */
    public Pokemon(int id, String nombre, String tipo, int nivel, int hp, int hpMaximo,
                   int ataque, int defensa, int velocidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = hp;
        this.hpMaximo = hpMaximo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    /**
     * Constructor simplificado para subclases de Pokémon.
     * Asigna valores por defecto a id, ataque, defensa y velocidad.
     *
     * @param nombre    Nombre del Pokémon
     * @param nivel     Nivel del Pokémon
     * @param hp        Puntos de vida
     * @param tipo      Tipo del Pokémon (ej: "Fuego", "Agua", "Planta")
     */
    public Pokemon(String nombre, int nivel, int hp, String tipo) {
        this.id = 0; // Valor por defecto
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = hp;
        this.hpMaximo = hp;
        this.ataque = 5; // Valor por defecto
        this.defensa = 5; // Valor por defecto
        this.velocidad = 5; // Valor por defecto
    }

    /**
     * Verifica si el Pokémon está debilitado (sin puntos de vida).
     *
     * @return true si hp <= 0, false en caso contrario
     */
    public boolean estaDebilitado() {
        return hp <= 0;
    }

    /**
     * El Pokémon ataca a otro Pokémon.
     * El daño se calcula basándose en el ataque del atacante y la defensa del defensor.
     *
     * @param rival Pokémon al que se le realiza el ataque
     */
    public void atacar(Pokemon rival) {
        if (this.estaDebilitado()) {
            System.out.println(this.nombre + " está debilitado y no puede atacar!");
            return;
        }

        // Calcular daño: ataque - defensa del rival + variación aleatoria
        int danoBase = Math.max(1, this.ataque - rival.defensa);
        int variacion = (int) (Math.random() * 10 + 1); // 1-10
        int danoTotal = danoBase + variacion;

        rival.recibirDano(danoTotal);
        System.out.println(this.nombre + " ataca a " + rival.nombre + " causando " + danoTotal + " de daño!");
    }

    /**
     * El Pokémon recibe daño y reduce sus puntos de vida.
     *
     * @param dano cantidad de daño a recibir
     */
    public void recibirDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.nombre + " ahora tiene " + this.hp + " HP.");
    }

    /**
     * Implementación de ICurable: restaura una cantidad específica de HP.
     */
    @Override
    public void restaurarSalud(int cantidad) {
        int hpAntes = this.hp;
        this.hp = Math.min(this.hpMaximo, this.hp + cantidad);
        System.out.println(this.nombre + " recuperó " + (this.hp - hpAntes) + " de HP. HP actual: " + this.hp);
    }

    /**
     * Implementación de ICurable: devuelve los HP actuales.
     */
    @Override
    public int getHpActual() {
        return this.hp;
    }

    /**
     * Cura completamente el Pokémon restaurando su HP al máximo.
     */
    public void curar() {
        this.hp = this.hpMaximo;
        System.out.println(this.nombre + " ha sido curado completamente. HP: " + this.hp);
    }

    /**
     * Calcula el daño base del Pokémon.
     * Este método puede ser sobrescrito por las subclases para cálculos especializados.
     *
     * @return cantidad de daño base
     */
    protected int calcularDano() {
        return this.ataque;
    }

    /**
     * El Pokémon sube de nivel aumentando sus estadísticas.
     */
    public void subirNivel() {
        this.nivel++;
        this.hpMaximo += 5;
        this.hp = this.hpMaximo;
        this.ataque += 3;
        this.defensa += 2;
        this.velocidad += 2;
        System.out.println(this.nombre + " subió a nivel " + this.nivel + "!");
    }

    /**
     * Obtiene un resumen de los estadísticas del Pokémon.
     *
     * @return string con la información del Pokémon
     */
    @Override
    public String toString() {
        return nombre + " (Nivel " + nivel + " | Tipo: " + tipo + " | HP: " + hp + "/" + hpMaximo + ")";
    }

    // ── Getters y Setters ──

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getHpMaximo() { return hpMaximo; }
    public void setHpMaximo(int hpMaximo) { this.hpMaximo = hpMaximo; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}
