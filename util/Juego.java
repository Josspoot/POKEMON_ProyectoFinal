package util;

import excepciones.EquipoLlenoException;
import excepciones.PokemonDebilitadoException;
import java.util.List;
import java.util.Scanner;
import modelo.Entrenador;
import modelo.Item;
import modelo.Pokemon;
import persistencia.Pokedex;
import servicio.ControladorCombate;
import servicio.IAEntrenador;
import servicio.SistemaCombate;

public class Juego {
    private Scanner sc;
    private Pokedex pokedex;

    public Juego(Scanner sc, Pokedex pokedex) {
        this.sc = sc;
        this.pokedex = pokedex;
    }

    private Entrenador crearJugador() {
        System.out.println("\n---- REGISTRO ----");
        Entrenador jugador = null;

        while (jugador == null) {
            System.out.print("Ingresa tu nombre de entrenador: ");
            String nombre = sc.nextLine().trim();
            try {
                jugador = new Entrenador(nombre);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("\n¡Bienvenido, " + jugador.getNombre() + "! Elige tu equipo:\n");
        Consola.pausa(300);

        List<Pokemon> lista = pokedex.getTodos();
        System.out.println("Pokémon disponibles:");
        pokedex.listar();

        for (int slot = 1; slot <= 2; slot++) {
            int eleccion = Consola.leerEntero(sc, "Pokémon " + slot + " (1-" + lista.size() + "): ", 1, lista.size());
            Pokemon elegido = lista.get(eleccion - 1);

            if (jugador.getPokemones().contains(elegido)) {
                System.out.println("Ya tienes ese Pokémon en tu equipo, elige otro.\n");
                slot--;
                continue;
            }
            try {
                jugador.agregarPokemon(elegido);
            } catch (EquipoLlenoException e) {
                System.out.println("¡" + e.getMessage() + "!");
            }

            Consola.pausa(300);
            System.out.println("  > ¡" + elegido.getNombre() + " se unió al equipo!\n");
        }
        return jugador;
    }

    private IAEntrenador crearIA(Entrenador jugador) {
        IAEntrenador ia = new IAEntrenador("ENTRENADOR RIVAL");
        List<Pokemon> lista = pokedex.getTodos();
        List<Pokemon> equipoJugador = jugador.getPokemones();

        int agregados = 0;
        for (Pokemon p : lista) {
            if (!equipoJugador.contains(p) && agregados < 2) {
                try {
                    ia.agregarPokemon(p);
                } catch (EquipoLlenoException e) {
                    System.out.println("¡" + e.getMessage() + "!");
                }

                agregados++;
            }
        }
        ia.agregarItem(new Item("Poción", 20, "Cura"));
        return ia;
    }

    public void iniciar() throws PokemonDebilitadoException {
        Entrenador jugador = crearJugador();
        jugador.agregarItem(new Item("Poción", 20, "Cura"));
        jugador.agregarItem(new Item("Super Poción", 50, "Cura"));

        IAEntrenador ia = crearIA(jugador);

        SistemaCombate sistema = new SistemaCombate(jugador, ia);
        new ControladorCombate(sistema, sc).iniciar();
    }
}