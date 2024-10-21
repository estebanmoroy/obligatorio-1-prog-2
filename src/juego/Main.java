/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

public class Main {
    public static void main(String[] args) {
        try {
            inicializarJuego();
            Sistema.iniciarMenu();
        } catch (Exception e) {
            manejarError(e);
        } finally {
            cerrarJuego();
        }
    }

    private static void inicializarJuego() {
        System.out.println("Inicializando Gran Tateti...");
        mostrarBienvenida();
    }

    private static void mostrarBienvenida() {
        try {
            Interfaz.mostrarBienvenida();
        } catch (InterruptedException e) {
            System.out.println("La animación de bienvenida fue interrumpida.");
        }
    }

    private static void manejarError(Exception e) {
        System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
        e.printStackTrace();
    }

    private static void cerrarJuego() {
        System.out.println("Cerrando el juego...");
        // Aquí se pueden agregar operaciones de limpieza si son necesarias
    }
}