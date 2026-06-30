package com.pokemoncenter.model;

/**
 * Clase de prueba para verificar que la conexión a la API de Gemini funciona.
 * No requiere base de datos SQL.
 */
public class PruebaGemini {

    public static void main(String[] args) {
        System.out.println("=== Iniciando prueba de Gemini API ===\n");

        // Test 1: Consulta válida del dominio Pokémon
        ConsultaIA consulta1 = new ConsultaIA("¿Qué tipo de Pokémon es Pikachu?");
        System.out.println("Consulta 1: " + consulta1.getPrompt());
        System.out.println("¿Es del dominio? " + consulta1.validarDominio());
        String respuesta1 = consulta1.enviar();
        System.out.println("Respuesta:\n" + respuesta1);
        System.out.println("\n---\n");

        // Test 2: Consulta válida del dominio
        ConsultaIA consulta2 = new ConsultaIA("¿Cuáles son los movimientos de Charizard?");
        System.out.println("Consulta 2: " + consulta2.getPrompt());
        System.out.println("¿Es del dominio? " + consulta2.validarDominio());
        String respuesta2 = consulta2.enviar();
        System.out.println("Respuesta:\n" + respuesta2);
        System.out.println("\n---\n");

        // Test 3: Consulta fuera del dominio (debe rechazar)
        ConsultaIA consulta3 = new ConsultaIA("¿Cuál es la capital de Francia?");
        System.out.println("Consulta 3: " + consulta3.getPrompt());
        System.out.println("¿Es del dominio? " + consulta3.validarDominio());
        String respuesta3 = consulta3.enviar();
        System.out.println("Respuesta:\n" + respuesta3);

        System.out.println("\n=== Prueba completada ===");
    }
}
