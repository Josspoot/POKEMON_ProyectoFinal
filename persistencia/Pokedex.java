package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Pokemon;
import servicio.ServicioPokedex;

public class Pokedex {
    private List<Pokemon> pokemones;
    ServicioPokedex servicio = new ServicioPokedex();

    public Pokedex() {
        this.pokemones = new ArrayList<>();
    }

    public void agregar(Pokemon p, PokemonArchivo a) throws IOException {
        pokemones.add(p);
        a.guardar(pokemones);

    }

    public void listar() {
        String ruta = "pokemones.csv";
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int i = 0;
            while ((linea = br.readLine()) != null) {
                i++;
                System.out.print(i + ". ");
                String[] campos = linea.split(",");
                for (String campo : campos) {
                    System.out.print(campo + " | ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            ;
        }
    }

    public List<Pokemon> getTodos() {
        return pokemones;
    }

    public Pokemon buscarNombre(String nombre) {
        if (pokemones.isEmpty())
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        for (Pokemon p : pokemones) {
            if (p.getNombre().toLowerCase().equals(nombre)) {
                System.out.println(p);
                return p;
            }
        }
        System.out.println("No se encontro ningun pokemon con ese nombre.");
        System.out.println();
        return null;
    }

    public List<Pokemon> buscarPorElemento(String tipo) {
        if (pokemones.isEmpty())
            throw new IllegalArgumentException("No hay pokemones en la pokedex");

        List<Pokemon> resultado = new ArrayList<>();
        for (Pokemon p : pokemones) {
            if (p.getTipo().toLowerCase().equals(tipo)) {
                resultado.add(p);
            }
        }
        if (resultado.isEmpty()) {
            System.out.println("No se encontraron pokemones de ese tipo.");
        } else {
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println((i + 1) + ". " + resultado.get(i));
            }
        }
        System.out.println();
        return resultado;
    }

    public void eliminarPorTipo(String tipo) {
        Iterator<Pokemon> it = pokemones.iterator();
        while (it.hasNext()) {
            Pokemon p = it.next();
            if (p.getTipo().toLowerCase().equals(tipo.toLowerCase())) {
                it.remove();
            }
        }
    }

}