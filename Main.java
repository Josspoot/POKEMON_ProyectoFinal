
import excepcions.ItemNuloException;
import excepcions.PokemonDebilitadoException;
import util.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ItemNuloException, PokemonDebilitadoException, IOException {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        menu.iniciar();
        sc.close();

    }
}
