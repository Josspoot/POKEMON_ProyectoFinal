package combate;

import excepcions.PokemonDebilitadoException;
import java.util.List;
import java.util.Scanner;
import util.Consola;

public class ControladorCombate {

    private SistemaCombate sistema;
    private Scanner sc;

    public ControladorCombate(SistemaCombate sistema, Scanner sc) {
        this.sistema = sistema;
        this.sc = sc;
    }

    public void mostrarEncabezado() {
        System.out.println("\n========================================");
        System.out.println("  ¡" + sistema.getJugador().getNombre().toUpperCase() + " VS "
                + sistema.getIA().getNombre().toUpperCase() + "!");
        System.out.println("========================================\n");
    }

    public void mostrarEstadoTurno() {
        System.out.println("════════ Turno " + sistema.getTurno() + " ════════");
        System.out.println(" Tu Pokémon:    " + sistema.getPokemonJugador());
        System.out.println(" Pokémon rival: " + sistema.getPokemonIA());
        System.out.println();
    }

    public void mostrarReporteAtaque(ReporteAtaque r, boolean esIA) {
        System.out.println("> ¡" + r.getNombreAtacante() + " usó " + r.getNombreMovimiento() + "!");
        Consola.pausa(900);
        System.out.println("¡Hizo " + r.getDmg() + " de daño!");
        Consola.pausa(400);
        System.out.println("  " + r.getNombreDefensor() + " tiene " + r.getHpRestante() + " HP restantes.\n");

        if (r.isDefensorDebilitado()) {
            Consola.pausa(300);
            System.out.println("¡" + r.getNombreDefensor() + " se ha debilitado!\n");

            if (r.getNombreSiguiente() != null) {
                Consola.pausa(600);
                if (esIA) {
                    System.out.println("> Tu siguiente Pokémon: " + r.getNombreSiguiente() + "\n");
                } else {
                    System.out
                            .println("> " + sistema.getIA().getNombre() + " envió a " + r.getNombreSiguiente() + "!\n");
                }
            }
        }
    }

    public void mostrarResultadoItem(int itemIdx) {
        Item item = sistema.getJugador().getItems().get(itemIdx);
        String nombrePokemon = sistema.getPokemonJugador().getNombre();
        sistema.ejecutarItemJugador(itemIdx);
        System.out.println("  > Usaste " + item.getNombre() + " sobre " + nombrePokemon + "!");
        Consola.pausa(500);
        System.out.println("  " + nombrePokemon + " ahora tiene " + sistema.getPokemonJugador().getHp() + " HP.\n");
    }

    public void mostrarResultado() {
        Consola.pausa(800);
        System.out.println("========================================");
        Consola.pausa(400);
        if (sistema.getGanador() == sistema.getJugador()) {
            System.out.println("¡GANASTE! ¡Eres el mejor entrenador!\n");
        } else {
            System.out.println("¡Perdiste! El rival fue demasiado fuerte.");
        }
        Consola.pausa(300);
        System.out.println("Ganador: " + sistema.getGanador().getNombre().toUpperCase());
        System.out.println("========================================\n");
    }

    public void turnoJugador() {
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Atacar");

        List<Item> items = sistema.getJugador().getItems();
        if (!items.isEmpty()) {
            System.out.println("2. Usar ítem");
        }
        System.out.println();

        int maxOpcion = 0;

        if (items.isEmpty()) {
            maxOpcion = 1;
        } else {
            maxOpcion = 2;
        }

        int accion = Consola.leerEntero(sc, "Elige (1-" + maxOpcion + "): ", 1, maxOpcion);
        System.out.println();

        if (accion == 1) {
            List<Movimiento> movs = sistema.getPokemonJugador().getMovimientos();
            System.out.println("Movimientos disponibles:");
            for (int i = 0; i < movs.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + movs.get(i));
            }
            System.out.println(" ");
            int movIdx = Consola.leerEntero(sc, "Elige movimiento (1-" + movs.size() + "): ", 1, movs.size()) - 1;
            System.out.println();
            Consola.pausa(700);
            try {
                mostrarReporteAtaque(sistema.ejecutarAtaqueJugador(movIdx), false);
            } catch (PokemonDebilitadoException e) {
                System.out.println("> " + e.getMessage() + "\n");
            }

        } else {
            System.out.println("Ítems disponibles:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + items.get(i));
            }
            System.out.println();
            int itemIdx = Consola.leerEntero(sc, "Elige ítem (1-" + items.size() + "): ", 1, items.size()) - 1;
            System.out.println();
            Consola.pausa(600);
            mostrarResultadoItem(itemIdx);
        }
    }

    public void turnoIA() {
        System.out.println(">> " + sistema.getIA().getNombre() + " está pensando...");
        Consola.pausa(1200);
        try {
            mostrarReporteAtaque(sistema.ejecutarAtaqueIA(), true);
        } catch (PokemonDebilitadoException e) {
            System.out.println("> " + e.getMessage() + "\n");
        }
    }

    public void iniciar() {
        mostrarEncabezado();
        Consola.pausa(800);

        while (!sistema.hayGanador()) {
            boolean turnoJugador = sistema.avanzarTurno();
            mostrarEstadoTurno();
            Consola.pausa(500);

            if (turnoJugador) {
                turnoJugador();
            } else {
                turnoIA();
            }

            Consola.pausa(600);
        }

        mostrarResultado();
    }

}