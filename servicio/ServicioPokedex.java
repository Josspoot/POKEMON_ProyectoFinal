package servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pokemon;
import persistencia.Pokedex;
import persistencia.PokemonArchivo;

public class ServicioPokedex {

    private List<Pokemon> pokemones = new ArrayList<>();

    Pokedex pokedex = new Pokedex();

    public void agregar(Pokemon p, PokemonArchivo a) throws IOException {
        pokemones.add(p);
        a.guardar(pokemones);

    }

    public void listar() {

    }

    public void buscar() {
    }

    public void filtrar() {
    }

    public void eliminar() {
    }

}
