package combate;

import entrenadores.Entrenador;
import entrenadores.IAEntrenador;
import pokemones.Pokemon;

import java.util.List;

public class SistemaCombate {

    private Entrenador jugador;
    private Entrenador ia;
    private int turno;

    public SistemaCombate(Entrenador jugador, Entrenador ia) {
        if (jugador == null || ia == null) {
            throw new IllegalArgumentException("No hay entrenadores en el combate");
        }
        this.jugador = jugador;
        this.ia      = ia;
        this.turno   = 0;
    }

    // Avanza el turno — retorna true si le toca al jugador
    public boolean avanzarTurno() {
        turno++;
        return turno % 2 != 0;
    }

    public ReporteAtaque ejecutarAtaqueJugador(int movimientoIdx) {
        ReporteAtaque reporte = aplicarAtaque(getPokemonJugador(), getPokemonIA(), movimientoIdx);
        if (reporte.isDefensorDebilitado()) {
            ia.getPokemones().remove(getPokemonIA());
        }
        return reporte;
    }

    public ReporteAtaque ejecutarAtaqueIA() {
        int movIdx;
        if (ia instanceof IAEntrenador) {
            movIdx = ((IAEntrenador) ia).elegirMovimiento(getPokemonIA(), getPokemonJugador());
        } else {
            movIdx = 0;
        }

        ReporteAtaque reporte = aplicarAtaque(getPokemonIA(), getPokemonJugador(), movIdx);
        if (reporte.isDefensorDebilitado()) {
            jugador.getPokemones().remove(getPokemonJugador());
        }
        return reporte;
    }

    public void ejecutarItemJugador(int itemIdx) {
        List<Item> items = jugador.getItems();
        Item item = items.get(itemIdx);
        jugador.usarItem(item, getPokemonJugador());
        items.remove(itemIdx);
    }

    private ReporteAtaque aplicarAtaque(Pokemon atacante, Pokemon defensor, int movIdx) {
        Movimiento mov = atacante.getMovimientos().get(movIdx);
        int dmg = atacante.calcularDmgEspecial() + mov.getPoder();

        atacante.atacar(defensor);
        defensor.recibirDmg(dmg);

        boolean debilitado = !defensor.estaVivo();
        String siguiente   = null;

        if (debilitado) {
            List<Pokemon> equipo = (defensor == getPokemonJugador())
                    ? jugador.getPokemones()
                    : ia.getPokemones();
            for (Pokemon p : equipo) {
                if (p != defensor && p.estaVivo()) {
                    siguiente = p.getNombre();
                    break;
                }
            }
        }

        return new ReporteAtaque(
                atacante.getNombre(),
                mov.getNombre(),
                dmg,
                defensor.getNombre(),
                defensor.getHp(),
                debilitado,
                siguiente
        );
    }

    public boolean hayGanador() {
        return !jugador.tieneVivos() || !ia.tieneVivos();
    }

    public Entrenador getGanador() {
        return !jugador.tieneVivos() ? ia : jugador;
    }

    public Pokemon getPokemonJugador() { return jugador.getPokemonActivo(); }
    public Pokemon getPokemonIA()      { return ia.getPokemonActivo(); }
    public Entrenador getJugador()     { return jugador; }
    public Entrenador getIA()          { return ia; }
    public int getTurno()              { return turno; }
}
