/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martínez
 */
public class Tablero {

    // Atributos
    private MiniTablero[][] tableroPrincipal;
    private MiniTablero[][] ganadoreMiniTableros;

    // Métodos

    /** Inicializa un nuevo tablero */
    public void nuevoTablero() {
        this.tableroPrincipal = new MiniTablero[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tableroPrincipal[i][j] = new MiniTablero();
            }
        }

    }

    /** Realiza una jugada en el tablero */
    public void jugada(String tipoJugador, String coordenada, String coordenadaMiniTablero) {

    }

    /** Ejecuta la jugada mágica en el mini-tablero */
    public void jugadaMagica(String posicionMiniTablero) {
        // Realiza una jugada mágica en un mini-tablero
    }

    /** Deshabilita un mini-cuadrado para que no se pueda usar más */
    public void deshabilitarMiniCuadrado(String coordenada) {
        // Deshabilita la casilla especificada
    }

    /** Verifica si el tablero está completo */
    public boolean tableroCompleto() {
        // Retorna true si el tablero está completamente lleno
        return false;
    }

    /** Actualiza el estado del tablero con una nueva jugada */
    public void actualizarTablero(String coordenada, String valor) {
        // Cambia el valor de la casilla en la coordenada especificada
    }

    /** Muestra el estado actual del tablero */
    public void mostrarTablero() {
        // Muestra el tablero en su estado actual
    }

}
