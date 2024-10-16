/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez
 */


import java.util.Scanner;
//import java.util.ArrayList;
import java.util.InputMismatchException;

public  class Interfaz {
    
    // Métodos

    //mostrar menu con animacion de 2 seg
    public static void mostrarBienvenida() throws InterruptedException {
        // Mostrar menú de opciones
        String mensaje = "Bienvenidos";
        int espaciosMax = 7;  // Número de espacios para mover el texto

        // Animar el texto moviéndolo hacia la derecha
        for (int i = 0; i < espaciosMax; i++) {
            // Limpiar la línea anterior 
            System.out.print("\r");

            // Imprimir el número de espacios seguido del mensaje
            System.out.print(" ".repeat(i) + mensaje);

            // Pausar durante aproximadamente 300 milisegundos para crear la animación
            try {
                Thread.sleep(300);  // Suspender el hilo por 300 milisegundos
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }

        // Mantener el mensaje en pantalla  antes de eliminarlo
        try {
            Thread.sleep(1000);  // Suspender el hilo por 1000 milisegundos 
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }

        // Limpiar el mensaje de la terminal
        System.out.println("\r" + " ".repeat(espaciosMax + mensaje.length()));
        //mostrarMenu();
    }
    /** muestra el menu con opción  */
    public static void mostrarMenu() {
        System.out.println("\t digite la opcion deseada:");
        System.out.println("1: Registrar un jugador.");
        System.out.println("2: Jugar al Gran Tateti entre 2 personas.");
        System.out.println("3: Jugar al Gran Tateti vs la Computadora.");
        System.out.println("4: Ranking.");
        System.out.println("5: Fin.");

        
    }

    


    /** Registra un nuevo jugador en el sistema */
    public static void TextoRegistrarJugador(/**Jugador jugador */) {
        String nombre = "";
        String alias = "";
        int edad = 0;
        boolean inputValido = false;
        Scanner teclado = new Scanner(System.in);
        System.out.println("digite el nombre del jugador: ");
        nombre = teclado.nextLine();

        while (!inputValido) {
            try {
                System.out.println("Digite edad: ");
                edad = teclado.nextInt();
                inputValido = true;  
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un numero  para la edad.");
                teclado.nextLine();  // Limpiar el buffer del teclado
            }
        }
        
        teclado.nextLine();// consumir linea
        System.out.println("digite el alias ");
        alias = teclado.nextLine();
        
        System.out.print(nombre + edad +alias);
        //teclado.close();
        Jugador nuevoJugador = new Jugador(nombre, edad, alias, null, false);
        Sistema.agregarJugador(nuevoJugador);
        


        // Agrega un jugador a la lista, verificando que el alias sea único
    }

    

    /** Muestra los jugadores  */
    


    /** Muestra el ranking de los jugadores basado en su puntaje */
    public static void mostrarRanking() {
        String[][] ranking = Sistema.calcularRanking();

        // Mostrar el ranking
        System.out.println("Ranking de jugadores:");
        for (String[] jugador : ranking) {
            String alias = jugador[0];
            int victorias = Integer.parseInt(jugador[1]);

            // Mostrar alias y tantas # como victorias
            System.out.print(alias + " | ");
            for (int i = 0; i < victorias; i++) {
                System.out.print("#");
            }
            System.out.println();  // Salto de línea después de cada jugador
        }
    
    }
    /** Muestra el historial de las Partidas */
    /**borrar
    public static void mostrarPartidas() {
        ArrayList<Partida> historial = Sistema.getHistorialPartidas();
    
        for (int i = 0; i < historial.size(); i++) {
            Partida partida = historial.get(i);
            System.out.println("Partida " + (i + 1) + ":");
            System.out.println("Ganador: " + (partida.getGanador() != null ? partida.getGanador().getAlias() : "Sin ganador"));
            System.out.println("-----------------------");
        }
    
    }
*/
    /** Renderiza el tablero actual en el sistema */
    public static void renderTablero(Tablero tablero) {
        // Muestra el tablero visualmente
    }

    
}