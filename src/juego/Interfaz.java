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
import java.util.ArrayList;

public class Interfaz {
    
    // Métodos

    //mostrar menu con animacion de 2 seg
    public void mostrarBienvenida() throws InterruptedException {
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
        mostrarMenu();
    }
    /** muestra el menu con opción  */
    public void mostrarMenu() {
        System.out.println("\t digite la opcion deseada:");
        System.out.println("1: Registrar un jugador.");
        System.out.println("2: Jugar al Gran Tateti entre 2 personas.");
        System.out.println("3: Jugar al Gran Tateti vs la Computadora.");
        System.out.println("4: Ranking.");
        System.out.println("5: Fin.");

        
    }

    /** Procesa la opción seleccionada por el usuario */
    // Procesa la opción del menú
    public void procesarOpcion(int opcion) {
        if(opcion == 1){
            System.out.println(" opcion 1:");
            registrarJugador();

        };
        if(opcion == 2){
            System.out.println(" opcion 2:");
            jugar();

            
        };
        if(opcion == 3){
            System.out.println(" opcion 3:");
            jugarVsCPU();

            
        };
        if(opcion == 4){
            System.out.println(" opcion 4:");
            mostrarRanking();

            
        };
        if(opcion == 5){
            System.out.println(" opcion 5:");
            mostrarRanking();

        }
    }


    /** Registra un nuevo jugador en el sistema */
    public void registrarJugador(/**Jugador jugador */) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("digite el nombre del jugador: ");
        String nombre = teclado.nextLine();
        System.out.println("digite edad: ");
        int edad = teclado.nextInt();
        teclado.nextLine();// consumir linea
        System.out.println("digite el alias ");
        String alias = teclado.nextLine();
        
        System.out.print(nombre + edad +alias);
        //teclado.close();
        Jugador nuevoJugador = new Jugador(nombre, edad, alias, null, false);
        if (aliasUnico(nuevoJugador)) {
            jugadores.add(nuevoJugador);  // Alias es único, se puede agregar el jugador
            System.out.println("Jugador registrado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un jugador con el alias '" + alias);
        }
        


        // Agrega un jugador a la lista, verificando que el alias sea único
    }

    

    /** Muestra los jugadores  */
    public Jugador  mostrarYSeleccionarJugador() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores disponibles.");
            return null;
        }

        System.out.println("Lista de jugadores:");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getAlias());
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("Selecciona un jugador ingresando el numero correspondiente:");
        int seleccion = teclado.nextInt() - 1;  // Restamos 1 para que sea el indice correcto
        //teclado.close();
        if (seleccion >= 0 && seleccion < jugadores.size()) {
            return jugadores.get(seleccion);
        } else {
            System.out.println("Selección inválida.");
            return null;
        }
        
        
    }

    public Jugador mostrarYSeleccionarOtroJugador(Jugador excluido) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores disponibles.");
            return null;
        }

        System.out.println("Lista de jugadores :");
        for (int i = 0; i < jugadores.size(); i++) {
            if (!jugadores.get(i).equals(excluido)) {
                System.out.println((i + 1) + ". " + jugadores.get(i).getAlias());
            }
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("Selecciona otro jugador ingresando el número correspondiente:");
        int seleccion = teclado.nextInt() - 1; 
       // teclado.close();
        if (seleccion >= 0 && seleccion < jugadores.size() && !jugadores.get(seleccion).equals(excluido)) {
            return jugadores.get(seleccion);
        } else {
            System.out.println("Selección inválida o es el mismo jugador.");
            return null;
        }
    }

    /** Muestra el ranking de los jugadores basado en su puntaje */
    public void mostrarRanking() {
        // Muestra el ranking de los jugadores
    }

    /** Renderiza el tablero actual en el sistema */
    public void renderTablero(Tablero tablero) {
        // Muestra el tablero visualmente
    }

    
}