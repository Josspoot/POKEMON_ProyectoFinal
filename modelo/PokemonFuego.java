package modelo;

public class PokemonFuego extends Pokemon {
    private int bonusFuego;

    public PokemonFuego(String nombre, int hp, int nivel) {
        super(nombre, hp, nivel, "Fuego");
        this.bonusFuego = 20;
    }

    @Override
    public int calcularDmgEspecial() {
        return bonusFuego;
    }

}
