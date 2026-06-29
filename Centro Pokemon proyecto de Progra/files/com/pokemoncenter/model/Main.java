package com.pokemoncenter.model;

public class Main {
    public static void main(String[] args) {
        // 1. Crear el Centro Pokémon
        CentroPokemon centro = new CentroPokemon("Centro Pokémon de Ciudad Verde", "Ciudad Verde");

        // 2. Crear Usuarios y Entrenadores
        Entrenador entrenador1 = new Entrenador(1, "Freddy");
        Usuario usuario1 = new Usuario(1, "freddy_admin", "1234", "freddy@pokemon.com");
        usuario1.setEntrenador(entrenador1);

        Entrenador entrenadorRival = new Entrenador(2, "Gary");

        // 3. Crear Pokémon (Usando la herencia y polimorfismo)
        PokemonFuego charmander = new PokemonFuego("Charmander", 5, 39, 52);
        PokemonAgua squirtle = new PokemonAgua("Squirtle", 5, 44, 65);

        entrenador1.agregarPokemon(charmander);
        entrenadorRival.agregarPokemon(squirtle);

        // 4. Probar el sistema de Batalla
        System.out.println("\n--- INICIANDO SIMULACIÓN DE BATALLA ---");
        Batalla batalla = new Batalla(entrenador1, entrenadorRival);
        batalla.iniciar();

        // 5. Probar el Centro Pokémon y la Interfaz ICurable
        System.out.println("\n--- VISITANDO EL CENTRO POKÉMON ---");
        centro.curarEquipo(entrenador1);
        Pocion pocion = centro.darPocion(0); // Pedir una poción básica
        if(pocion != null) {
            pocion.aplicar(charmander);
        }

        // 6. Probar la Consulta de IA y Persistencia SQL
        System.out.println("\n--- PROBANDO IA Y PERSISTENCIA SQL ---");
        ConsultaIA consulta = new ConsultaIA("¿Qué debilidades tiene el tipo Fuego?");
        String respuesta = consulta.enviar();
        System.out.println("Respuesta IA: " + respuesta);
        
        // TODO: Guardar en la base de datos (comentado hasta instalar SQL)
        // consulta.guardarHistorial(usuario1);

        // TODO: Leer el historial desde la base de datos (comentado hasta instalar SQL)
        // HistorialBusqueda historial = new HistorialBusqueda(0, null, "", "");
        // System.out.println("\n--- LEYENDO HISTORIAL DESDE MYSQL ---");
        // historial.obtenerPorUsuario(usuario1);

        ServidorWeb.iniciar();
    }
}   