package modelo;

public class PokemonPlanta extends Pokemon {
    private int bonusPlanta;

    public PokemonPlanta(String nombre, int hp, int nivel) {
        super(nombre, hp, nivel, "Planta");
        this.bonusPlanta = 20;
    }

    @Override
    public int calcularDmgEspecial() {
        return bonusPlanta;
    }

}
