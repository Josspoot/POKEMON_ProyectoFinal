package util;

import java.util.Scanner;

public class Consola {

    public Consola(){
        //No debe llevar atributos esta cosa
    }

    public static int leerEntero(Scanner sc, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor < min || valor > max)
                    throw new IllegalArgumentException("Número entre " + min + " y " + max);
                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: por favor ingresa un valor válido. " + e.getMessage() + "\n");
            }
        }
    }

    public static void pausa(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("ERROR: Error con la pausa.");
        }
    }
}
