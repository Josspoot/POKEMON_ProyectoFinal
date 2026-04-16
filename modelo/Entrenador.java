package modelo;

import excepciones.EquipoLlenoException;
import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    private String nombre;
    private List<Pokemon> pokemones = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Entrenador(String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El Entrenador debe de tener un nombre inicial");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getPokemones() {
        return pokemones;
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarPokemon(Pokemon p) throws EquipoLlenoException {
        if (pokemones.size() >= 6)
            throw new EquipoLlenoException("Equipo lleno, máximo 6 Pokémon", 6);
        pokemones.add(p);
    }

    public void agregarItem(Item i) throws IllegalArgumentException {
        if (i == null)
            throw new IllegalArgumentException("El item no puede ser nulo");
        items.add(i);
    }

    public void usarItem(Item i, Pokemon p) {
        i.usar(p);
    }

    public Pokemon getPokemonActivo() {
        return pokemones.getFirst();
    }

    public boolean tieneVivos() {
        for (Pokemon p : pokemones) {
            if (p.estaVivo())
                return true;
        }
        return false;
    }
}
