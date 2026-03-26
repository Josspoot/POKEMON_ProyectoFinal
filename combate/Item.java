package combate;

import pokemones.Pokemon;

public class Item {
    private String nombre;
    private int efecto;
    private String tipo;

    public Item(String nombre, int efecto, String tipo) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El item debe tener un nombre");
        if (efecto <= 0)
            throw new IllegalArgumentException("El efecto debe ser mayor a 0");
        this.nombre = nombre;
        this.efecto = efecto;
        this.tipo = tipo;
    }

    public String getNombre(){
        return nombre;
    }
    public int getEfecto(){
        return efecto;
    }
    public String getTipo(){
        return tipo;
    }

    public void usar(Pokemon p) {
        p.setHp(p.getHp() + efecto);
    }

    @Override
    public String toString() {
        return nombre + " | Efecto: " + efecto + " | Tipo: " + tipo;
    }
}