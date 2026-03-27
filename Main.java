import excepcions.ItemNuloException;
import excepcions.PokemonDebilitadoException;
import java.io.IOException;
import java.util.Scanner;
import util.Menu;

public class Main {
    public static void main(String[] args) throws ItemNuloException, PokemonDebilitadoException, IOException {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        menu.iniciar();
        sc.close();

    }
}
