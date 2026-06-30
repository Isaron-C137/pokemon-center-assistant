package com.pokemoncenter.model;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServidorWeb {

    public static void iniciar() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8025), 0);
            
            // 1. MAIN ENDPOINT: Serves index.html
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String path = exchange.getRequestURI().getPath();
                    if (!path.equals("/")) {
                        exchange.sendResponseHeaders(404, 0);
                        exchange.close();
                        return;
                    }

                    OutputStream os = exchange.getResponseBody();
                    try {
                        Path rutaHtml = Paths.get("web/index.html").toAbsolutePath();
                        if (!Files.exists(rutaHtml)) {
                            rutaHtml = Paths.get("files/web/index.html").toAbsolutePath();
                        }
                        
                        if (Files.exists(rutaHtml)) {
                            byte[] response = Files.readAllBytes(rutaHtml);
                            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                            exchange.sendResponseHeaders(200, response.length);
                            os.write(response);
                        } else {
                            throw new IOException();
                        }
                    } catch (Exception e) {
                        String error = "System Error: index.html asset not found in workspace routes.";
                        exchange.sendResponseHeaders(404, error.length());
                        os.write(error.getBytes());
                    } finally {
                        os.close();
                    }
                }
            });

            // 2. BATTLE ENDPOINT: Simulador de Combate Dinámico (Inglés)
            server.createContext("/api/batalla", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String query = exchange.getRequestURI().getQuery();
                    String jugador = "Trainer";
                    
                    if (query != null && query.contains("entrenador=")) {
                        try {
                            jugador = query.split("entrenador=")[1];
                            jugador = URLDecoder.decode(jugador, StandardCharsets.UTF_8.toString());
                        } catch (Exception e) {
                            jugador = "Trainer";
                        }
                    }

                    // Filtro de seguridad bilingüe
                    String[] prohibidas = {"tonto", "idiota", "estupido", "mierda", "basura", "put", "perr", "shit", "fuck", "bitch", "dumb"};
                    boolean invalido = false;
                    for (String malaPalabra : prohibidas) {
                        if (jugador.toLowerCase().contains(malaPalabra)) {
                            invalido = true;
                            break;
                        }
                    }

                    String respuestaFinal = "";
                    if (invalido) {
                        respuestaFinal = "[SECURITY ERROR]\nThe entered trainer name contains restricted language.\nPlease maintain respect within the Pokémon Center facilities.";
                    } else {
                        
                        String[] posiblesRivales = {"Gary", "Ash", "Cynthia", "Professor Oak", "Misty", "Lance", "Mysterious Rival"};
                        String rival = posiblesRivales[(int)(Math.random() * posiblesRivales.length)];

                        StringBuilder log = new StringBuilder();
                        log.append("=== Battle Commences! ===\n");
                        log.append("Registered Trainer: ").append(jugador).append(" vs ").append(rival).append("\n");
                        log.append(jugador).append(" sends out Charmander!\n");
                        log.append(rival).append(" sends out Squirtle!\n\n");
                        
                        int hpC = 39; int hpS = 44; int turno = 1;
                        while(hpC > 0 && hpS > 0) {
                            log.append("--- Turn ").append(turno).append(" ---\n");
                            int d1 = (int)(Math.random() * 12) + 2; 
                            hpS -= d1; if(hpS < 0) hpS = 0;
                            log.append("Charmander attacks Squirtle dealing ").append(d1).append(" damage!\n");
                            log.append("Squirtle now has ").append(hpS).append(" HP.\n");
                            
                            if(hpS <= 0) break;
                            
                            int d2 = (int)(Math.random() * 12) + 2; 
                            hpC -= d2; if(hpC < 0) hpC = 0;
                            log.append("Squirtle attacks Charmander dealing ").append(d2).append(" damage!\n");
                            log.append("Charmander now has ").append(hpC).append(" HP.\n\n");
                            turno++;
                        }
                        
                        log.append("\n=== Combat Summary! ===\n");
                        if(hpC > 0) {
                            log.append("").append(jugador).append(" won the battle with Charmander!\n");
                        } else {
                            log.append("").append(rival).append(" won the battle with Squirtle!\n");
                        }
                        respuestaFinal = log.toString();
                    }

                    byte[] response = respuestaFinal.getBytes(StandardCharsets.UTF_8);
                    exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
                    exchange.sendResponseHeaders(200, response.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response);
                    os.close();
                }
            });

            // 3. AI GEMINI ENDPOINT: Processes dynamic queries
            server.createContext("/api/ia", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String query = exchange.getRequestURI().getQuery();
                    String question = "";
                    
                    if (query != null && query.contains("pregunta=")) {
                        try {
                            question = query.split("pregunta=")[1];
                            question = URLDecoder.decode(question, StandardCharsets.UTF_8.toString());
                        } catch (Exception e) {
                            question = "¿Qué debilidades tiene el tipo Fuego?";
                        }
                    }

                    // Instanciamos la clase ConsultaIA con la pregunta real que viene desde la web
                    ConsultaIA consulta = new ConsultaIA(question);
                    
                    // Ejecutamos el método enviar() para activar las reglas de idioma y dominio de Gemini
                    String aiOutput = consulta.enviar();
                    
                    // Enviamos la respuesta real generada por la IA al navegador
                    byte[] response = aiOutput.getBytes(StandardCharsets.UTF_8);
                    exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
                    exchange.sendResponseHeaders(200, response.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response);
                    os.close();
                }
            });

            server.setExecutor(null); 
            server.start();
            System.out.println("=================================================");
            System.out.println("Web Server SUCCESSFULLY INITIALIZED.");
            System.out.println("Abre tu navegador y entra a: http://localhost:8025");
            System.out.println("=================================================");
            
            System.out.println("Presiona ENTER en esta consola para apagar el servidor...");
            System.in.read();
            
            server.stop(0);
            System.out.println("Server terminated.");

        } catch (Exception e) {
            System.out.println("Error initializing web server core: " + e.getMessage());
        }
    }
}