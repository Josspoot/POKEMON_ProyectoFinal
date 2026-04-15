package modelo;

public class PokemonAgua extends Pokemon {
    private int bonusAgua;

    public PokemonAgua(String nombre, int hp, int nivel) {
        super(nombre, hp, nivel, "Agua");
        this.bonusAgua = 10;
    }

    @Override
    public int calcularDmgEspecial() {
        return bonusAgua;
    }

    @Override
    public String toCSV() {
        return super.toCSV();
    }

}
