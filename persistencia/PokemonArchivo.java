package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import modelo.Pokemon;
import modelo.PokemonAgua;
import modelo.PokemonFuego;
import modelo.PokemonNormal;
import modelo.PokemonPlanta;

public class PokemonArchivo {
    private String rutaArchivo;

    public PokemonArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(List<Pokemon> estudiantes)
            throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            pw.println("nombre,hp,nivel,tipo");
            for (Pokemon est : estudiantes) {
                pw.println(est.toCSV());
            }
        }
    }

    public List<Pokemon> cargar() throws IOException {
        List<Pokemon> pokemones = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return pokemones;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;

                }
                if (!linea.trim().isEmpty()) {
                    try {
                        Pokemon po = fromCSV(linea);
                        pokemones.add(po);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Línea ignorada: " +
                                e.getMessage());
                    }
                }
            }
        }

        return pokemones;
    }

    public static Pokemon fromCSV(String linea) {
        String[] datos = linea.split(",");

        if (datos.length != 4) {
            throw new IllegalArgumentException("Formato invalido " + linea);
        }
        String nombre = datos[0].trim();
        int hp;
        int nivel;
        String tipo = datos[3].trim();

        try {
            hp = Integer.parseInt(datos[1].trim());
            nivel = Integer.parseInt(datos[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Numeros invalidos: " + linea);
        }

        switch (tipo.toLowerCase()) {
            case "fuego":
                return new PokemonFuego(nombre, hp, nivel);
            case "agua":
                return new PokemonAgua(nombre, hp, nivel);
            case "planta":
                return new PokemonPlanta(nombre, hp, nivel);
            case "normal":
                return new PokemonNormal(nombre, hp, nivel);
            default:
                throw new IllegalArgumentException("Tipo de pokemon erroneo: " + tipo);
        }

    }

}
