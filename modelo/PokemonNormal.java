package modelo;

public class PokemonNormal extends Pokemon {
    private int bonusNormal;

    public PokemonNormal(String nombre, int hp, int nivel) {
        super(nombre, hp, nivel, "Normal");
        this.bonusNormal = 20;
    }

    @Override
    public int calcularDmgEspecial() {
        return bonusNormal;
    }

}
