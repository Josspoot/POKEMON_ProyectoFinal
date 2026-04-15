package servicio;

import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;

import java.util.List;

public class IAEntrenador extends Entrenador {

    public IAEntrenador(String nombre) {
        super(nombre);
    }

    public int elegirMovimiento(Pokemon pokemonIA, Pokemon pokemonJugador) {
        List<Movimiento> movs = pokemonIA.getMovimientos();
        int mejorIdx = 0;
        int mayorPoder = -1;

        for (int i = 0; i < movs.size(); i++) {
            int poderTotal = movs.get(i).getPoder() + pokemonIA.calcularDmgEspecial();

            if (pokemonJugador.getHp() <= poderTotal && pokemonJugador.getHp() > 0) {
                return i;
            }

            if (poderTotal > mayorPoder) {
                mayorPoder = poderTotal;
                mejorIdx = i;
            }
        }

        return mejorIdx;
    }
}
