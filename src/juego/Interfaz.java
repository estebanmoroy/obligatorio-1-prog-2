/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import java.util.Scanner;

public class Interfaz {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra la animación de bienvenida.
     */
    public static void mostrarBienvenida() throws InterruptedException {
        // Mostrar menú de opciones
        String mensaje = "Bienvenidos";
        int espaciosMax = 7; // Número de espacios para mover el texto

        // Animar el texto moviéndolo hacia la derecha
        for (int i = 0; i < espaciosMax; i++) {
            // Limpiar la línea anterior
            System.out.print("\r");

            // Imprimir el número de espacios seguido del mensaje
            System.out.print(" ".repeat(i) + mensaje);

            // Pausar durante aproximadamente 300 milisegundos para crear la animación
            try {
                Thread.sleep(300); // Suspender el hilo por 300 milisegundos
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }

        // Mantener el mensaje en pantalla antes de eliminarlo
        try {
            Thread.sleep(1000); // Suspender el hilo por 1000 milisegundos
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }

        // Limpiar el mensaje de la terminal
        System.out.println("\r" + " ".repeat(espaciosMax + mensaje.length()));
    }

    /**
     * Muestra el menú principal del juego.
     */
    public static void mostrarMenu() {
        System.out.println("\tDigite la opción deseada:");
        System.out.println("1: Registrar un jugador.");
        System.out.println("2: Jugar al Gran Tateti entre 2 personas.");
        System.out.println("3: Jugar al Gran Tateti vs la Computadora.");
        System.out.println("4: Ranking.");
        System.out.println("5: Fin.");
    }

    /**
     * Solicita y registra la información de un nuevo jugador.
     */
    public static void TextoRegistrarJugador() {
        String nombre = leerEntrada("Digite el nombre del jugador: ");
        int edad = leerEnteroValido("Digite edad: ");
        String alias = leerEntrada("Digite el alias: ");

        Jugador nuevoJugador = new Jugador(nombre, edad, alias, null, false);
        Sistema.agregarJugador(nuevoJugador);
    }

    private static String leerEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEnteroValido(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }

    /**
     * Muestra el ranking de jugadores.
     */
    public static void mostrarRanking() {
        String[][] ranking = Sistema.calcularRanking();
        System.out.println("Ranking de jugadores:");
        for (String[] jugador : ranking) {
            System.out.print(jugador[0] + " | ");
            for (int i = 0; i < Integer.parseInt(jugador[1]); i++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}