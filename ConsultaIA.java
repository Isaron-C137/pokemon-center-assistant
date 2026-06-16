package com.pokemoncenter.model;

import java.util.Date;

/**
 * Gestiona las consultas al asistente de IA del Centro Pokémon.
 * Se encarga de enviar el prompt a la API de Gemini, validar que
 * la consulta pertenezca al dominio Pokémon y guardar el resultado
 * en el historial.
 */
public class ConsultaIA {

    private String prompt;
    private String respuesta;
    private Date timestamp;

    private static final String[] PALABRAS_DOMINIO = {
        "pokemon", "pokémon", "entrenador", "batalla", "tipo", "movimiento",
        "evolución", "pokedex", "pokédex", "centro", "gimnasio", "hp",
        "ataque", "defensa", "velocidad", "nivel", "experiencia"
    };

    /**
     * Constructor de ConsultaIA.
     *
     * @param prompt Texto de la consulta a enviar a la IA
     */
    public ConsultaIA(String prompt) {
        this.prompt = prompt;
        this.respuesta = "";
        this.timestamp = new Date();
    }

    /**
     * Envía la consulta a la API de Gemini y devuelve la respuesta.
     * Solo envía si la consulta pertenece al dominio Pokémon.
     *
     * @return respuesta de la IA, o mensaje de rechazo si está fuera del dominio
     */
    public String enviar() {
        if (!validarDominio()) {
            respuesta = "Lo siento, solo puedo responder preguntas relacionadas con Pokémon y el Centro Pokémon.";
            System.out.println("Consulta rechazada: fuera del dominio Pokémon.");
            return respuesta;
        }
        System.out.println("Enviando consulta a Gemini API: \"" + prompt + "\"");
        respuesta = llamarAPIGemini(prompt);
        return respuesta;
    }

    /**
     * Valida que la consulta esté relacionada con el universo Pokémon.
     * Revisa si el prompt contiene palabras clave del dominio.
     *
     * @return true si la consulta pertenece al dominio Pokémon
     */
    public boolean validarDominio() {
        String promptMinusculas = prompt.toLowerCase();
        for (String palabra : PALABRAS_DOMINIO) {
            if (promptMinusculas.contains(palabra)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Guarda el resultado de esta consulta en el historial del usuario.
     *
     * @param usuario el usuario que realizó la consulta
     * @return objeto HistorialBusqueda con los datos de esta consulta
     */
    public HistorialBusqueda guardarHistorial(Usuario usuario) {
        HistorialBusqueda registro = new HistorialBusqueda(0, usuario, prompt, respuesta);
        registro.guardar();
        return registro;
    }

    /**
     * Simula la llamada a la API de Gemini.
     * En la implementación real, aquí va la solicitud HTTP a Gemini.
     *
     * @param prompt consulta a enviar
     * @return respuesta simulada de la IA
     */
    private String llamarAPIGemini(String prompt) {
        return "[Respuesta de Gemini para: " + prompt + "]";
    }

    // ── Getters y Setters ──

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getRespuesta() { return respuesta; }

    public Date getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Consulta: " + prompt + "\nRespuesta: " + respuesta;
    }
}
