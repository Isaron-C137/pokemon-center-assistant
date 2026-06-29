package com.pokemoncenter.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public ConsultaIA(String prompt) {
        this.prompt = prompt;
        this.respuesta = "";
        this.timestamp = new Date();
    }

    public String enviar() {
        // Creamos el comando maestro que le da las reglas estrictas a Gemini
        // antes de enviarle la pregunta real del usuario.
        String promptMaestro = "Actúa como la Inteligencia Artificial médica y táctica del Centro Pokémon. "
                + "Debes cumplir estas 3 reglas estrictas: "
                + "1. Responde de forma dinámica y completa cualquier duda, pero SOLO sobre el universo Pokémon. "
                + "2. Si la pregunta NO tiene nada que ver con Pokémon (por ejemplo: dolores de estómago humanos, política, programación, tareas, etc.), discúlpate muy educadamente diciendo que tu sistema fue creado únicamente para gestionar temas del Centro Pokémon. "
                + "3. Detecta automáticamente el idioma en el que está escrita la pregunta y responde exactamente en ese mismo idioma. "
                + "Esta es la pregunta del usuario: " + this.prompt;

        System.out.println("Enviando consulta controlada a Gemini API...");
        
        // Le enviamos a tu método de conexión el promptMaestro en lugar del texto crudo
        this.respuesta = llamarAPIGemini(promptMaestro);
        return this.respuesta;
    }

    public boolean validarDominio() {
        String promptMinusculas = prompt.toLowerCase();
        for (String palabra : PALABRAS_DOMINIO) {
            if (promptMinusculas.contains(palabra)) {
                return true;
            }
        }
        return false;
    }

    public HistorialBusqueda guardarHistorial(Usuario usuario) {
        HistorialBusqueda registro = new HistorialBusqueda(0, usuario, prompt, respuesta);
        registro.guardar();
        return registro;
    }

    private String llamarAPIGemini(String promptTexto) {
        // Tu nueva API Key (Formato AQ)
        String apikey = System.getenv("GEMINI_API_KEY");
        
        // ¡SOLUCIÓN AL ERROR 404! 
        // Actualizamos el modelo a gemini-2.5-flash porque el 1.5 fue dado de baja.
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            try {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // Construcción del JSON con instrucciones del sistema
                String jsonInputString = "{"
                    + "\"contents\": [{"
                    + "  \"parts\": [{"
                    + "    \"text\": \"Actúa como un asistente experto del Centro Pokémon. Responde de forma concisa a la siguiente consulta: " + promptTexto + "\""
                    + "  }]"
                    + "}]"
                    + "}";

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        
                        // Extraer el texto de la respuesta del JSON
                        String jsonRespuesta = response.toString();
                        int start = jsonRespuesta.indexOf("\"text\": \"") + 9;
                        int end = jsonRespuesta.indexOf("\"", start);
                        
                        if (start > 8 && end > start) {
                            return jsonRespuesta.substring(start, end).replace("\\n", "\n").replace("\\\"", "\"");
                        }
                        return "Error: No se pudo procesar el formato de la respuesta de la IA.";
                    }
                } else {
                    return "Error en la API de Gemini: Código HTTP " + responseCode;
                }
            } finally {
                conn.disconnect();
            }
        } catch (Exception e) {
            return "Error de red o conexión al intentar llamar a la IA: " + e.getMessage();
        }
    }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public String getRespuesta() { return respuesta; }
    public Date getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Consulta: " + prompt + "\nRespuesta: " + respuesta;
    }
}