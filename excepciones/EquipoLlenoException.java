package excepciones;

public class EquipoLlenoException extends Exception {
    private int maximo;

    public EquipoLlenoException(String mensaje, int maximo) {
        super(mensaje);
        this.maximo = maximo;
    }

    public int getLimite() {
        return maximo;
    }
}