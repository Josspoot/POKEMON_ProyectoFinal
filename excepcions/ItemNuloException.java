package excepcions;

public class ItemNuloException extends Exception {
    private int limite;

    public ItemNuloException(String mensaje, int limite) {
        super(mensaje);
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }
}
