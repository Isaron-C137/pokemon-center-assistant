# pokemon-center-assistant
Pokemon Center Assistant - OOP Final Project
# Pokémon Center Assistant

Pokémon Center Assistant is an Object-Oriented desktop application developed in Java that simulates the experience of visiting a Pokémon Center. The system allows trainers to register, manage their Pokémon team, heal their Pokémon, and interact with an AI-powered assistant that answers questions strictly related to the Pokémon universe.

This project was developed as the final project for the Object-Oriented Programming (OOP) course.

## Overview

The application combines core OOP principles (encapsulation, inheritance, polymorphism, and abstraction) with modern software development practices, including SQL persistence, AI integration via the Gemini API, and version control through GitHub.

## Features

- **Trainer registration and login** with secure credential validation.
- **Pokémon team management** supporting up to 6 Pokémon per trainer, with specialized subclasses for Water, Fire, and Grass types.
- **Pokémon Center healing system**, including a potion inventory and full team recovery.
- **Turn-based battle system** between two trainers.
- **AI Assistant** powered by the Gemini API, restricted exclusively to Pokémon-related queries through domain validation.
- **Query history** stored in a SQL database, allowing trainers to review previous AI interactions.
- **Web interface** served through a lightweight Java web server.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application language |
| MySQL | Data persistence (CRUD operations) |
| Gemini API | AI assistant integration |
| MySQL Connector/J | Database connectivity driver |
| HTML | Frontend interface |
| Git & GitHub | Version control and project management |

## Project Structure

```
pokemon-center-assistant/
├── src/
│   └── com/pokemoncenter/model/
│       ├── ICurable.java
│       ├── Pokemon.java
│       ├── PokemonAgua.java
│       ├── PokemonFuego.java
│       ├── PokemonPlanta.java
│       ├── Entrenador.java
│       ├── Usuario.java
│       ├── CentroPokemon.java
│       ├── Pocion.java
│       ├── Batalla.java
│       ├── HistorialBusqueda.java
│       ├── ConsultaIA.java
│       ├── ConexionDB.java
│       └── Main.java
├── sql/
│   └── schema.sql
├── lib/
│   └── mysql-connector-j-9.7.0.jar
├── web/
│   └── index.html
├── docs/
│   └── uml/
└── README.md
```

## Class Design

The system is built around 14 core classes and 1 interface, following SOLID design principles:

- **`ICurable`** — interface defining the healing contract implemented by all Pokémon subclasses.
- **`Pokemon`** — abstract base class with shared attributes and behavior.
- **`PokemonAgua`, `PokemonFuego`, `PokemonPlanta`** — concrete subclasses with type-specific stats and moves.
- **`Entrenador`** — manages a trainer's Pokémon team (max. 6).
- **`Usuario`** — handles authentication, separated from game logic.
- **`CentroPokemon`** — manages healing operations and the potion inventory.
- **`Pocion`** — represents an item that restores HP.
- **`Batalla`** — implements the turn-based battle system.
- **`HistorialBusqueda`** — manages persistence of AI query history.
- **`ConsultaIA`** — handles communication with the Gemini API and domain validation.

A full UML class diagram is available in `docs/uml/`.

## Database Design

The application uses a MySQL database (`centro_pokemon`) with tables for trainers, Pokémon teams, and AI query history. Full CRUD operations (Create, Read, Update, Delete) are implemented across all tables. The complete schema is available in `sql/schema.sql`.

## AI Assistant — Domain Restriction

The AI assistant is configured to respond exclusively to Pokémon-related questions. Before any query is sent to the Gemini API, the system validates the prompt against a list of domain-specific keywords (Pokémon, trainer, battle, type, move, evolution, etc.). If the query falls outside this domain, the assistant declines to answer, ensuring functional coherence as required by the project specification.

## Setup and Installation

### Prerequisites

- Java JDK 17 or higher
- MySQL Server 8.0 or higher
- A valid Gemini API key

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Isaron-C137/pokemon-center-assistant.git
   ```

2. Import the database schema:
   ```bash
   mysql -u root -p < sql/schema.sql
   ```

3. Set the Gemini API key as an environment variable:
   ```bash
   set GEMINI_API_KEY=your_api_key_here
   ```

4. Compile and run the project from your Java IDE (IntelliJ IDEA or VS Code), or via terminal:
   ```bash
   javac -d bin src/com/pokemoncenter/model/*.java
   java -cp bin com.pokemoncenter.model.Main
   ```

## Branching Strategy

This project follows a feature-branch workflow:

| Branch | Purpose |
|---|---|
| `main` | Stable, production-ready code |
| `development` | Active integration branch |
| `feature/modelo-pokemon` | Core OOP model classes |
| `feature/base-de-datos` | SQL schema and persistence layer |
| `feature/integracion-ia` | Gemini API integration |
| `feature/interfaz-usuario` | Web server and frontend |
| `feature/documentacion` | Documentation and README |

## Project Management

Task tracking, sprint planning, and backlog management were carried out using a Kanban-style board (Trello / GitHub Projects), documented separately as part of the project deliverables.

## Authors

- [Student Name 1]
- [Student Name 2]

## Course

Object-Oriented Programming (OOP) — Final Project

## License

This project was developed for academic purposes as part of a university course assignment.
