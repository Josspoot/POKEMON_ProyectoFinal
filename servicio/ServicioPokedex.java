package servicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import modelo.Pokemon;
import persistencia.Pokedex;
import persistencia.PokemonArchivo;

public class ServicioPokedex {

    private final Pokedex pokedex;
    private final PokemonArchivo archivo;

    public ServicioPokedex(Pokedex pokedex, PokemonArchivo archivo) {
        this.pokedex = pokedex;
        this.archivo = archivo;
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

    public Pokemon buscarNombre(String nombre) {
        return pokedex.buscarNombre(nombre);
    }

    public List<Pokemon> buscarPorElemento(String tipo) {
        return pokedex.buscarPorElemento(tipo);
    }

    public void agregar(Pokemon p) throws IOException {
        pokedex.agregar(p, archivo);
    }

    public void eliminarPorTipo(String tipo) throws IOException {
        pokedex.eliminarPorTipo(tipo);
        archivo.guardar(pokedex.getTodos());
    }
}