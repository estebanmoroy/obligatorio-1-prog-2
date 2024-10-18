/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martínez
 */

public class Main {
    public static void main(String[] args) {
        try {
            inicializarJuego();
            Sistema.iniciarMenu();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarJuego();
        }
    }

    private static void inicializarJuego() {
        System.out.println("Inicializando el Gran Tateti...");

        // Crear y agregar jugadores precargados
        JugadorCPU cpu = new JugadorCPU("CPU", 0, "cpu", null, true);
        Jugador esteban = new Jugador("Esteban", 27, "esteban", null, true);
        Jugador facundo = new Jugador("Facundo", 24, "facundo", null, true);

        Sistema.agregarJugador(cpu);
        Sistema.agregarJugador(esteban);
        Sistema.agregarJugador(facundo);

        try {
            Interfaz.mostrarBienvenida();
        } catch (InterruptedException e) {
            System.out.println("La animación de bienvenida fue interrumpida.");
        }
    }

    private static void cerrarJuego() {
        System.out.println("Cerrando el juego...");
    }
}
