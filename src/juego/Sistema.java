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

public class Sistema {
    // Atributos
    private Partida partidaActual;
    private ArrayList <Partida> historialPartidas;
    private ArrayList <Jugador> jugadores;

    //contructor
    public Sistema() {
        historialPartidas = new ArrayList<>();  // Inicializa lista de historial 
        jugadores = new ArrayList<>();          // Inicializa lista de jugadores
    }
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
        teclado.close();
        Jugador nuevoJugador = new Jugador(nombre, edad, alias, null, false);
        if (aliasUnico(nuevoJugador)) {
            jugadores.add(nuevoJugador);  // Alias es único, se puede agregar el jugador
            System.out.println("Jugador registrado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un jugador con el alias '" + alias);
        }
        


        // Agrega un jugador a la lista, verificando que el alias sea único
    }

    /** Inicia una nueva partida entre dos jugadores */
    public void jugar() {
        // Inicia el flujo de una partida entre dos jugadores
    }

    /** Inicia una partida contra la CPU */
    public void jugarVsCPU() {
        // Inicia una partida contra la computadora
    }

    /** Muestra el ranking de los jugadores basado en su puntaje */
    public void mostrarRanking() {
        // Muestra el ranking de los jugadores
    }

    /** Renderiza el tablero actual en el sistema */
    public void renderTablero(Tablero tablero) {
        // Muestra el tablero visualmente
    }

    /** Guarda una partida finalizada en el historial */
    public void guardarPartida(Partida partida) {
        // Almacena la partida en el historial
    }

    /** Carga una partida del historial según el ID */
    public Partida cargarPartida(int id) {
        // Retorna una partida del historial
        return null;
    }
    /** verificar duplicado de alias*/
    private boolean aliasUnico(Jugador nuevoJugador) {
        boolean esUnico = true;  
        for (Jugador jugador : jugadores) {
            if (jugador.equals(nuevoJugador)) {
                esUnico = false;  
            }
        }
    
        return esUnico;
    }
}
