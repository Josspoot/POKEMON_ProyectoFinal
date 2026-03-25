package pokemones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Pokedex {
    private List<Pokemon> pokemones;

    public Pokedex() {
        this.pokemones = new ArrayList<>();
    }

    public void agregar(Pokemon p) {
        pokemones.add(p);
    }

    public void listar() {
        if (pokemones.isEmpty())
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + pokemones.get(i));
        }
        System.out.println();
    }

    public List<Pokemon> getTodos() {
        return pokemones;
    }

    public Pokemon buscarNombre(Scanner sc) {
        if (pokemones.isEmpty())
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        System.out.print("Ingresa el nombre a buscar: ");
        String nombre = sc.nextLine().toLowerCase();
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

    public List<Pokemon> buscarPorElemento(Scanner sc) {
        if (pokemones.isEmpty())
            throw new IllegalArgumentException("No hay pokemones en la pokedex");
        System.out.print("Ingresa el tipo a buscar: ");
        String tipo = sc.nextLine().toLowerCase();
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