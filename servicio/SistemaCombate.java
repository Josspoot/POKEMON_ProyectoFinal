package servicio;

import excepciones.PokemonDebilitadoException;
import java.util.List;
import modelo.Entrenador;
import modelo.Item;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.ReporteAtaque;

public class SistemaCombate {

    private Entrenador jugador;
    private IAEntrenador ia;
    private int turno;

    public SistemaCombate(Entrenador jugador, IAEntrenador ia) {
        if (jugador == null || ia == null) {
            throw new IllegalArgumentException("No hay entrenadores en el combate");
        }
        this.jugador = jugador;
        this.ia = ia;
        this.turno = 0;
    }

    public boolean avanzarTurno() {
        turno++;
        return turno % 2 != 0;
    }

    public ReporteAtaque ejecutarAtaqueJugador(int movimientoIdx) throws PokemonDebilitadoException {
        ReporteAtaque reporte = aplicarAtaque(getPokemonJugador(), getPokemonIA(), movimientoIdx);
        if (reporte.isDefensorDebilitado()) {
            Pokemon debilitado = getPokemonIA();
            ia.getPokemones().remove(debilitado);
        }
        return reporte;
    }

    public ReporteAtaque ejecutarAtaqueIA() throws PokemonDebilitadoException {
        int movIdx = ia.elegirMovimiento(getPokemonIA(), getPokemonJugador());

        ReporteAtaque reporte = aplicarAtaque(getPokemonIA(), getPokemonJugador(), movIdx);

        if (reporte.isDefensorDebilitado()) {
            Pokemon debilitado = getPokemonJugador();
            jugador.getPokemones().remove(0);
            throw new PokemonDebilitadoException(
                    reporte.getNombreDefensor() + " se ha debilitado",
                    debilitado.getHpMax(),
                    debilitado.getHp());
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
        String siguiente = null;

        if (debilitado) {
            List<Pokemon> equipo;
            if (defensor == getPokemonJugador()) {
                equipo = jugador.getPokemones();
            } else {
                equipo = ia.getPokemones();
            }
            for (Pokemon p : equipo) {
                if (p != defensor && p.estaVivo()) {
                    siguiente = p.getNombre();
                    break;
                }
            }
        }

        return new ReporteAtaque(atacante.getNombre(), mov.getNombre(), dmg, defensor.getNombre(), defensor.getHp(),
                debilitado, siguiente);
    }

    public boolean hayGanador() {
        return !jugador.tieneVivos() || !ia.tieneVivos();
    }

    public Entrenador getGanador() {
        if (!jugador.tieneVivos()) {
            return ia;
        } else {
            return jugador;
        }
    }

    public Pokemon getPokemonJugador() {
        return jugador.getPokemonActivo();
    }

    public Pokemon getPokemonIA() {
        return ia.getPokemonActivo();
    }

    public Entrenador getJugador() {
        return jugador;
    }

    public Entrenador getIA() {
        return ia;
    }

    public int getTurno() {
        return turno;
    }
}
