package combate;

import pokemones.Pokemon;

public interface Combatible {

    void atacar(Pokemon p);

    boolean estaVivo();

    void recibirDmg(int cantidad);

}
