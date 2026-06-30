package com.pokemoncenter.model;

public interface ICurable {
    void restaurarSalud(int cantidad);
    int getHpActual();
    int getHpMaximo();
}

