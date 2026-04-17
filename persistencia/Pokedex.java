package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Pokemon;

public class Pokedex {

    private List<Pokemon> pokemones;

    public Pokedex() {
        this.pokemones = new ArrayList<>();
    }

    public void agregar(Pokemon p, PokemonArchivo a) throws IOException {
        pokemones.add(p);
        a.guardar(pokemones);
    }

    public List<Pokemon> getTodos() {
        return pokemones;
    }

    public void listar() {
        String ruta = "pokemones.csv";
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int i = 0;
            while ((linea = br.readLine()) != null) {
                System.out.print(i + ". ");
                i++;
                String[] campos = linea.split(",");
                for (String campo : campos) {
                    System.out.print(campo + " | ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Pokemon buscarNombre(String nombre) {
        if (pokemones.isEmpty()) {
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        }
        for (Pokemon p : pokemones) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public List<Pokemon> buscarPorElemento(String tipo) {
        if (pokemones.isEmpty()) {
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        }
        List<Pokemon> resultado = new ArrayList<>();
        for (Pokemon p : pokemones) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void eliminarPorTipo(String tipo) {
        Iterator<Pokemon> it = pokemones.iterator();
        while (it.hasNext()) {
            Pokemon p = it.next();
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                it.remove();
            }
        }
    }
}