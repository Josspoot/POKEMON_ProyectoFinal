package excepcions;

public class PokemonDebilitadoException extends Exception {
    private int hpMaximo;
    private int hpActual;

    public PokemonDebilitadoException(String mensaje, int hpMaximo, int hpActual) {
        super(mensaje);
        this.hpMaximo = hpMaximo;
        this.hpActual = hpActual;
    }

    public int getHpMaximo() {
        return this.hpMaximo;
    }

    public int getHpActual() {
        return this.hpActual;
    }

}
