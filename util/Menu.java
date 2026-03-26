package util;

import excepcions.ItemNuloException;
import excepcions.PokemonDebilitadoException;

import java.io.IOException;
import java.util.Scanner;
import pokemones.*;

public class Menu {
    private Scanner sc;
    private Pokedex pokemones;
    private PokemonArchivo pokeArchivo;

    public Menu(Scanner sc) throws IOException {
        this.sc = sc;
        this.pokeArchivo = new PokemonArchivo("pokemones.csv");
        this.pokemones = construirPokedex();
    }

    private Pokedex construirPokedex() throws IOException {
        Pokedex pokedex = new Pokedex();
        try {
            for (Pokemon p : pokeArchivo.cargar()) {
                pokedex.agregar(p);
            }

        } catch (IOException e) {
            System.out.println("Error al cargar archivo " + e.getMessage());
        }

        pokedex.agregar(new PokemonFuego("Charmander", 100, 10));
        pokedex.agregar(new PokemonAgua("Squirtle", 100, 10));
        pokedex.agregar(new PokemonPlanta("Bulbasaur", 100, 10));
        pokedex.agregar(new PokemonNormal("Eevee", 100, 10));
        pokedex.agregar(new PokemonFuego("Arcanine", 100, 15));
        pokedex.agregar(new PokemonAgua("Gyarados", 100, 15));
        pokedex.agregar(new PokemonPlanta("Venusaur", 100, 15));
        pokedex.agregar(new PokemonNormal("Snorlax", 100, 15));

        java.util.List<Pokemon> todos = pokedex.getTodos();
        todos.get(0).agregarMovimiento("Ascuas", "Fuego", 15, 100);
        todos.get(0).agregarMovimiento("Arañazo", "Normal", 10, 100);
        todos.get(1).agregarMovimiento("Pistola Agua", "Agua", 15, 100);
        todos.get(1).agregarMovimiento("Placaje", "Normal", 10, 100);
        todos.get(2).agregarMovimiento("Látigo Cepa", "Planta", 15, 100);
        todos.get(2).agregarMovimiento("Placaje", "Normal", 10, 100);
        todos.get(3).agregarMovimiento("Placaje", "Normal", 10, 100);
        todos.get(3).agregarMovimiento("Gruñido", "Normal", 8, 100);
        todos.get(4).agregarMovimiento("Colmillo Ígneo", "Fuego", 20, 90);
        todos.get(4).agregarMovimiento("Rugido", "Normal", 8, 100);
        todos.get(5).agregarMovimiento("Hidrobomba", "Agua", 20, 90);
        todos.get(5).agregarMovimiento("Mordisco", "Normal", 12, 100);
        todos.get(6).agregarMovimiento("Rayo Solar", "Planta", 22, 85);
        todos.get(6).agregarMovimiento("Látigo Cepa", "Planta", 15, 100);
        todos.get(7).agregarMovimiento("Golpe Cuerpo", "Normal", 22, 85);
        todos.get(7).agregarMovimiento("Amnesia", "Normal", 5, 100);

        return pokedex;
    }

    private void mostrarTitulo() {
        Consola.pausa(200);
        System.out.println("\n========================================");
        Consola.pausa(150);
        System.out.println("       POKÉBATTLE TERMINAL v1.00000000000002");
        Consola.pausa(150);
        System.out.println("========================================\n");
        Consola.pausa(300);
    }

    private void mostrarOpciones() {
        System.out.println("---- MENÚ PRINCIPAL ----");
        System.out.println("1. Nueva batalla");
        System.out.println("2. Instrucciones");
        System.out.println("3. Ver Pokemones");
        System.out.println("4. Salir");
        System.out.println();
    }

    private void mostrarInstrucciones() {
        System.out.println("\n---- INSTRUCCIONES ----");
        System.out.println("Elige tu nombre y selecciona 2 Pokémon para tu equipo.");
        System.out.println("Los turnos se alternan: tú primero, luego el rival.");
        System.out.println("Cada turno puedes atacar o usar un ítem de tu bolsa.");
        System.out.println("Gana quien debilite todos los Pokémon del rival.");
        System.out.println("Cada tipo tiene un bonus de daño especial distinto.\n");
        Consola.pausa(300);
    }

    private void mostrarPokemones() {
        boolean salir = false;
        while (!salir) {
            System.out.println("--- Menú Pokemones ---");
            System.out.println();
            System.out.println("¿Que te gustaria ver?");
            System.out.println("1. Lista de Pokemones.");
            System.out.println("2. Buscar Pokemon por nombre.");
            System.out.println("3. Buscar Pokemon por tipo.");
            System.out.println("4. Salir.");
            int caso = Consola.leerEntero(sc, "Elige (1-4): ", 1, 4);
            System.out.println();
            switch (caso) {
                case 1:
                    System.out.println("##############");
                    System.out.println("#  POKEMONES #");
                    System.out.println("##############");
                    pokemones.listar();
                    break;
                case 2:
                    System.out.println("POKEMONES ENCONTRADOS: ");
                    pokemones.buscarNombre(sc);
                    break;
                case 3:
                    System.out.println("POKEMONES ENCONTRADOS: ");
                    pokemones.buscarPorElemento(sc);
                    break;
                case 4:
                    salir = true;
            }
        }
    }

    public void guardarPokemons() {
        try {
            pokeArchivo.guardar(pokemones.getTodos());
        } catch (IOException e) {
            System.out.println("Error al guardar pokemones:" + e.getMessage());
        }
    }

    public void iniciar() throws ItemNuloException, PokemonDebilitadoException {
        mostrarTitulo();
        guardarPokemons();
        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            int opcion = Consola.leerEntero(sc, "Elige (1-4): ", 1, 4);
            switch (opcion) {
                case 1:
                    new Juego(sc, pokemones).iniciar();
                    break;
                case 2:
                    mostrarInstrucciones();
                    break;
                case 3:
                    mostrarPokemones();
                    break;
                case 4:
                    salir = true;
            }
        }
        System.out.println("\n¡Hasta la próxima, entrenador!\n");
    }
}