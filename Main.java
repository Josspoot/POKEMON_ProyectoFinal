import java.io.IOException;
import java.util.Scanner;

import excepciones.PokemonDebilitadoException;
import util.Menu;

public class Main {
    public static void main(String[] args) throws PokemonDebilitadoException, IOException {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);

        try {
            menu.iniciar();

        } catch (PokemonDebilitadoException e) {
            System.out.println("Error " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error " + e.getMessage());
        }
        sc.close();

    }
}
