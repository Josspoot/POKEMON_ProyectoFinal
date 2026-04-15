package modelo;

public interface Combatible {

    void atacar(Pokemon p);

    boolean estaVivo();

    void recibirDmg(int cantidad);

}
