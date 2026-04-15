package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Pokemon implements Combatible {
    private String nombre, tipo;
    private int hp, hpMax, nivel;
    private List<Movimiento> movimientos = new ArrayList<>();

    public Pokemon(String nombre, int hp, int nivel, String tipo) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El Pokemon debe de tener un nombre inicial.");
        }
        if (hp <= 0 || hp > 100) {
            throw new IllegalArgumentException("La vida máxima del Pokemon es de 100pts.");
        }
        if (nivel <= 0) {
            throw new IllegalArgumentException("El nivel minimo del Pokemon debe de ser de 1lv.");
        }
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El Pokemon debe de tener un tipo especifico. ");
        }

        this.nombre = nombre;
        this.hp = hp;
        this.nivel = nivel;
        this.tipo = tipo;
        this.hpMax = hp;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("Nombre inválido");
        this.nombre = nombre;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpMax() {
        return this.hpMax;
    }

    public int getNivel() {
        return nivel;
    }

    public String getTipo() {
        return this.tipo;
    }

    public List<Movimiento> getMovimientos() {
        return this.movimientos;
    }

    public void agregarMovimiento(String nombre, String tipo, int poder, int precision) {
        Movimiento m = new Movimiento(nombre, tipo, poder, precision);
        movimientos.add(m);
    }

    @Override
    public void atacar(Pokemon objetivo) {
        System.out.println("Se ha realizado el ataque al Pokemon.");
    }

    @Override
    public void recibirDmg(int cantidad) {
        hp -= cantidad;

    }

    @Override
    public boolean estaVivo() {
        return this.hp > 0;
    }

    public abstract int calcularDmgEspecial();

    @Override
    public String toString() {
        return nombre + " | Tipo: " + tipo + " | HP: " + hp + "/" + hpMax + " | Nivel: " + nivel;
    }

    public String toCSV() {
        return nombre + "," + hp + "," + nivel + "," + tipo;
    }

}
