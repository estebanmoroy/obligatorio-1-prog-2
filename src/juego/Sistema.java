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
    private static  Partida partidaActual;
    private static  ArrayList <Partida> historialPartidas = new ArrayList<>() ;
    private static  ArrayList <Jugador> jugadores =  new ArrayList<>() ;

    //contructor
    //public  Sistema() {
      //  historialPartidas = new ArrayList<>();  // Inicializa lista de historial 
        //jugadores = new ArrayList<>();          // Inicializa lista de jugadores
    //}



    // Métodos
    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public static ArrayList<Partida> getHistorialPartidas() {
        return historialPartidas;
    }
    



    /** agregar jugador para pruebas */
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
        Jugador jugador1 = Interfaz.mostrarYSeleccionarJugador();
        Jugador jugador2 = Interfaz.mostrarYSeleccionarOtroJugador(jugador1);

        
        Partida partida = new Partida();
        partida.nuevaPartida(jugador1 , jugador2);
        System.out.println("jugador 1 es:" + jugador1 + "" + "jugador 2 es: " + jugador2);
        // Inicia el flujo de una partida entre dos jugadores
    }

    /** Inicia una partida contra la CPU */
    public static  void jugarVsCPU() {
        Jugador jugador1 = Interfaz.mostrarYSeleccionarJugador();
        // Inicia una partida contra la computadora
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
    private static  boolean aliasUnico(Jugador nuevoJugador) {
        boolean esUnico = true;  
        for (Jugador jugador : jugadores) {
            if (jugador.equals(nuevoJugador)) {
                esUnico = false;  
            }
        }
    
        return esUnico;
    }
}
