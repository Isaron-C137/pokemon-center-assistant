package com.pokemoncenter.model;
 
/**
 * Clase base abstracta que representa un Pokémon.
 * Todos los tipos específicos de Pokémon deben extender esta clase.
 * Implementa Curable para cumplir con el contrato de la interfaz.
 */
public abstract class Pokemon implements Curable {
 
    private String nombre;
    private int nivel;
    private int hp;
    private int hpMaximo;
    private String tipo;
 
    /**
     * Constructor de Pokemon.
     *
     * @param nombre   Nombre del Pokémon
     * @param nivel    Nivel del Pokémon
     * @param hp       Puntos de vida del Pokémon
     * @param tipo     Tipo del Pokémon
     */
    public Pokemon(String nombre, int nivel, int hp, String tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.hp = hp;
        this.hpMaximo = hp;
        this.tipo = tipo;
    }
 
    /**
     * Ataca a un Pokémon objetivo, reduciendo sus puntos de vida.
     *
     * @param objetivo el Pokémon objetivo a atacar
     */
    public void atacar(Pokemon objetivo) {
        int dano = calcularDano();
        objetivo.recibirDano(dano);
        System.out.println(this.nombre + " atacó a " + objetivo.getNombre() + " causando " + dano + " de daño.");
    }
 
    /**
     * Calcula el daño que este Pokémon inflige. Las subclases pueden sobreescribir para daño personalizado.
     *
     * @return cantidad de daño
     */
    protected int calcularDano() {
        return nivel * 5;
    }
 
    /**
     * Reduce los puntos de vida de este Pokémon según el daño recibido.
     *
     * @param dano daño a recibir
     */
    public void recibirDano(int dano) {
        this.hp = Math.max(0, this.hp - dano);
    }
 
    /**
     * Implementación de curación por defecto — restaura los HP al máximo.
     * Las subclases pueden sobreescribir para un comportamiento de curación personalizado.
     */
    @Override
    public void curar() {
        this.hp = this.hpMaximo;
        System.out.println(this.nombre + " ha sido curado. HP restaurado a " + this.hpMaximo + ".");
    }
 
    /**
     * Indica si el Pokémon está debilitado (HP en cero).
     *
     * @return true si el Pokémon está debilitado
     */
    public boolean estaDebilitado() {
        return this.hp <= 0;
    }
 
    // ── Getters y Setters ──
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
 
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
 
    public int getHpMaximo() { return hpMaximo; }
    public void setHpMaximo(int hpMaximo) { this.hpMaximo = hpMaximo; }
 
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
 
    @Override
    public String toString() {
        return nombre + " [Tipo: " + tipo + " | Nivel: " + nivel + " | HP: " + hp + "/" + hpMaximo + "]";
    }
}
 
