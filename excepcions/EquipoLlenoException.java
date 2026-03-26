package excepcions;

public class EquipoLlenoException extends Exception {
    private int limite;

    public EquipoLlenoException(String mensaje, int limite) {
        super(mensaje);
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }
}