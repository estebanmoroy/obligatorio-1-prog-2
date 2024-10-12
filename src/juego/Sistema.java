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
    /** agregar jugador para pruebas */
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
    
    /** Inicia una nueva partida entre dos jugadores */
    public void jugar() {
        Jugador jugador1 = this.mostrarYSeleccionarJugador();
        Jugador jugador2 = this.mostrarYSeleccionarOtroJugador(jugador1);

        
        Partida partida = new Partida();
        partida.nuevaPartida(jugador1 , jugador2);
        System.out.println("jugador 1 es:" + jugador1 + "" + "jugador 2 es: " + jugador2);
        // Inicia el flujo de una partida entre dos jugadores
    }

    /** Inicia una partida contra la CPU */
    public void jugarVsCPU() {
        Jugador jugador1 = this.mostrarYSeleccionarJugador();
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
