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
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;


public class Sistema {
    // Atributos
    private static  Partida partidaActual;
    private static  ArrayList <Partida> historialPartidas = new ArrayList<>() ;
    private static  ArrayList <Jugador> jugadores =  new ArrayList<>() ;

    //contructor
    //public  Sistema() {
      //  historialPartidas = new ArrayList<>();  // Inicializa lista de historial 
        //jugadores = new ArrayList<>();          // Inicializa lista de jugadores
    //}



    // Métodos

    /** Getters */
    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public static ArrayList<Partida> getHistorialPartidas() {
        return historialPartidas;
    }
   
//menu    
    /** Inicia menu */
    public static void iniciarMenu() {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        
        while (opcion != 5) {
            Interfaz.mostrarMenu();  
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();  
            procesarOpcion(opcion);  
        }

        //teclado.close();
    }



    /** Procesa la opción seleccionada por el usuario */
   
    public static void procesarOpcion(int opcion) {
        if(opcion == 1){
            System.out.println(" opcion 1:");
            Interfaz.TextoRegistrarJugador();

        };
        if(opcion == 2){
            System.out.println(" opcion 2:");
            if (jugadores.isEmpty() || jugadores.size() < 2) {
                System.out.println("Error: debe tener al menos 2 jugadores registrados.");
            } else {
                Sistema.jugar();
            }
            

            
        };
        if(opcion == 3){
            System.out.println(" opcion 3:");
            if (jugadores.isEmpty() || jugadores.size() < 1) {
                System.out.println("Error: debe tener al menos 1 jugador registrado.");
            } else {
                Sistema.jugarVsCPU();
            }
            

            
        };
        if(opcion == 4){
            System.out.println(" opcion 4:");
            if (jugadores.isEmpty()) {
                System.out.println("No hay jugadores registrados");
            } else {
                Interfaz.mostrarRanking();
            }
            

            
        };
        if(opcion == 5){
            System.out.println(" opcion 5:");
            //salir();

        }
    }

    // Método para seleccionar un jugador sin exclusión
    private static Jugador seleccionarJugadorGenerico(Jugador excluido) {
        /** Procesa la opción seleccionada por el usuario 
        if (jugadores.isEmpty() || jugadores.size() < 1) {
            System.out.println("No hays jugadores disponibles.");
            return null;
        }
        */
        System.out.println("Lista de jugadores:");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            // Si hay un jugador a excluir, no lo mostramos en la lista
            if (excluido == null || !jugador.equals(excluido)) {
                System.out.println((i + 1) + ". " + jugador.getAlias());
            }
        }

        Scanner teclado = new Scanner(System.in);
        Jugador jugadorSeleccionado = null;
        boolean seleccionValida = false;

        // Repetir hasta que la selección sea válida
        while (!seleccionValida) {
            try {
                System.out.println("Selecciona un jugador ingresando el número correspondiente:");
                int seleccion = teclado.nextInt() - 1;  // Ajustar el índice

                jugadorSeleccionado = jugadores.get(seleccion);

                // Verificar que no se seleccionó al jugador excluido
                if (excluido == null || !jugadorSeleccionado.equals(excluido)) {
                    seleccionValida = true;  // La selección es válida, salir del bucle
                } else {
                    System.out.println("Error: El jugador seleccionado está excluido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                teclado.next();  // Limpiar el buffer de entrada
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Seleccion fuera de rango.");
            } 
        }

        return jugadorSeleccionado;
    }

    // Método para seleccionar un jugador sin exclusión
    public static Jugador seleccionarJugador() {
        return seleccionarJugadorGenerico(null);  // Llamar al método sin excluir a ningún jugador
    }

    // Método para seleccionar un jugador excluyendo uno específico
    public static Jugador seleccionarJugadorExcluyendo(Jugador excluido) {
        return seleccionarJugadorGenerico(excluido);  // Llamar al método excluyendo al jugador especificado
    }


//juegos
    /** agregar jugador  */
    public static  void agregarJugador(Jugador nuevoJugador) {
        if (aliasUnico(nuevoJugador)) {
            jugadores.add(nuevoJugador);  // Alias es único, se puede agregar el jugador
            System.out.println("Jugador:" +  nuevoJugador.getAlias() + "registrado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un jugador con el alias '" + nuevoJugador.getAlias());
        }
       
    }
    
    /** Inicia una nueva partida entre dos jugadores */
    public static  void jugar() {
        
       
            Jugador jugador1 = seleccionarJugador();
            Jugador jugador2 = seleccionarJugadorExcluyendo(jugador1);
        
            Partida partida = new Partida();
            partida.nuevaPartida(jugador1 , jugador2);
            System.out.println("jugador 1 es:" + jugador1 + "" + "jugador 2 es: " + jugador2);
        // Inicia el flujo de una partida entre dos jugadores
        
    }

    /** Inicia una partida contra la CPU */
    public static  void jugarVsCPU() {
        Jugador jugador1 = seleccionarJugador();
        // Inicia una partida contra la computadora
    }

    
//partidas
    /** Guarda una partida finalizada en el historial */
    public static void guardarPartida(Partida partida) {
        historialPartidas.add(partida);
        // Almacena la partida en el historial
    }

    /** Carga una partida del historial según el ID */
    public static Partida cargarPartida(int id) {
        // Retorna una partida del historial
        return null;
    }
    /** verificar duplicado de alias*/
    private static  boolean aliasUnico(Jugador nuevoJugador) {
        boolean esUnico = true;  
        for (Jugador jugador : jugadores) {
            if (jugador.equals(nuevoJugador)) {
                esUnico = false;  
            }
        }
    
        return esUnico;
    }


public static String[][] calcularRanking() {
    // Obtener la lista de jugadores
    ArrayList<Jugador> jugadores = Sistema.getJugadores();

    // Crear la matriz: Cada fila tiene 2 columnas [alias, victorias]
    String[][] ranking = new String[jugadores.size()][2];

    // Inicializar la matriz con los alias de los jugadores y 0 victorias
    for (int i = 0; i < jugadores.size(); i++) {
        ranking[i][0] = jugadores.get(i).getAlias();  
        ranking[i][1] = "0";  // Inicializamos con 0 victorias
    }

    // Obtener el historial de partidas
    ArrayList<Partida> historial = Sistema.getHistorialPartidas();

    // Contar cuantas veces cada jugador gano
    for (Partida partida : historial) {
        Jugador ganador = partida.getGanador();
        if (ganador != null) {
            // Encontrar el alias del ganador en la matriz y aumentar el contador
            for (int i = 0; i < ranking.length; i++) {
                if (ranking[i][0].equals(ganador.getAlias())) {
                    // Convertir la cantidad de victorias a entero, incrementar y volver a String
                    int victorias = Integer.parseInt(ranking[i][1]);
                    ranking[i][1] = Integer.toString(victorias + 1);
                }
            }
        }
    }

    // Ordenar la matriz por la columna de victorias (de mayor a menor)
    Arrays.sort(ranking, new Comparator<String[]>() {
        @Override
        public int compare(String[] a, String[] b) {
            return Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1]));  // Ordenar por victorias (columna 1)
        }
    });

    return ranking;
    }

}