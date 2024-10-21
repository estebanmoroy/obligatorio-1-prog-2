/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 * Representa un jugador controlado por la CPU en el juego Gran Tateti.
 * 
 * @author Esteban Moroy 338885, Facundo Martinez 342426
 */
public class JugadorCPU extends Jugador {

    private static final String[] COORDENADAS = { "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3" };

    /**
     * Constructor para crear un nuevo jugador CPU.
     * 
     * @param nombre                 Nombre del jugador CPU
     * @param edad                   Edad del jugador CPU (típicamente 0)
     * @param alias                  Alias único del jugador CPU
     * @param caracter               Carácter que representa al jugador CPU en el
     *                               tablero (X u O)
     * @param jugadaMagicaDisponible Indica si el jugador CPU puede usar la jugada
     *                               mágica
     */
    public JugadorCPU(String nombre, int edad, String alias, String caracter, boolean jugadaMagicaDisponible) {
        super(nombre, edad, alias, caracter, jugadaMagicaDisponible);
    }

    /**
     * Genera una jugada aleatoria para la primera jugada de la CPU.
     * 
     * @return Un array de Strings con la coordenada del tablero y la miniCoordenada
     */
    public String[] generarPrimeraJugada() {
        String coordenada = COORDENADAS[(int) (Math.random() * COORDENADAS.length)];
        String miniCoordenada = COORDENADAS[(int) (Math.random() * COORDENADAS.length)];
        return new String[] { coordenada, miniCoordenada };
    }

    /**
     * Genera una jugada aleatoria para las jugadas subsiguientes de la CPU.
     * 
     * @return Una String con la miniCoordenada seleccionada
     */
    public String generarJugada() {
        return COORDENADAS[(int) (Math.random() * COORDENADAS.length)];
    }
}