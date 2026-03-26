import excepcions.ItemNuloException;
import excepcions.PokemonDebilitadoException;
import java.util.Scanner;
import util.Menu;

public class Main {
    public static void main(String[] args) throws ItemNuloException, PokemonDebilitadoException {

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        menu.iniciar();
        sc.close();

    }
}
