package combate;

public class Movimiento {
    private String nombre;
    private String tipo;
    private int poder;
    private int precision;

    public Movimiento(String nombre, String tipo, int poder, int precision) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El movimiento debe de tener un nombre inicial");
        }
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
        this.precision = precision;
    }

    public String getNombre(){ 
        return nombre; 
    }
    public String getTipo(){ 
        return tipo; 
    }
    public int getPoder(){
        return poder;
    }
    public int getPrecision(){ 
        return precision; 
    }

    @Override
    public String toString() {
        return nombre + " | Tipo: " + tipo + " | Poder: " + poder + " | Precisión: " + precision;
    }
}
