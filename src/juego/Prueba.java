/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package juego;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martínez
 */
public class Prueba {

    public static void main(String[] args) throws InterruptedException {

        String[][] tableroPrueba = {
                { "", "X", "O" },
                { "X", "O", "X" },
                { "", "X", "O" }
        };

        MiniTablero miniTablero = new MiniTablero();
        miniTablero.setMiniTablero(tableroPrueba);
        System.out.println(miniTablero.toString());
        System.out.println("");

        miniTablero.jugada("A1", "X");
        System.out.println(miniTablero.toString());
        System.out.println("");

        System.out.println(miniTablero.estaLleno());
        System.out.println(miniTablero.determinarGanador());

        System.out.println(miniTablero.filaToString(0, "O"));

        Tablero tablero = new Tablero();

        System.out.println(tablero.toString());

        // Sistema sistema = new Sistema();
        // Interfaz interfaz = new Interfaz();
        // Partida partida1 = new Partida();
        Jugador nuevoJugador = new Jugador("facu", 23, "fada", null, false);
        Jugador nuevoJugador2 = new Jugador("esteban", 24, "este", null, false);
        Jugador nuevoJugador3 = new Jugador("ana", 24, "ana", null, false);
        // Sistema.agregarJugador(nuevoJugador);
        // Sistema.agregarJugador(nuevoJugador2);
        // Sistema.agregarJugador(nuevoJugador3);

        // Partida partida1 = new
        // Partida(nuevoJugador,nuevoJugador2,null,null,true,"ganador",nuevoJugador);

        // Partida partida1 = new Partida(nuevoJugador, nuevoJugador2, null, null, true,
        // "ganador", nuevoJugador);
        // Partida partida2 = new Partida(nuevoJugador, nuevoJugador2, null, null, true,
        // "ganador", nuevoJugador2);
        // Partida partida3 = new Partida(nuevoJugador, nuevoJugador2, null, null, true,
        // "ganador", nuevoJugador);
        // Partida partida4 = new Partida(nuevoJugador, nuevoJugador2, null, null, true,
        // "ganador", nuevoJugador);
        // Sistema.guardarPartida(partida1);
        // Sistema.guardarPartida(partida2);
        // Sistema.guardarPartida(partida3);
        // Sistema.guardarPartida(partida4);

        // Scanner teclado = new Scanner(System.in);
        // Interfaz.mostrarBienvenida();
        // Sistema.iniciarMenu();
        // int opcion = teclado.nextInt();
        // Interfaz.procesarOpcion(opcion);
        // teclado.close();
        // asd
    }

}
