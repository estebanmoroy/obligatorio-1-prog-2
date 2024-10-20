/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez
 */

public class JugadorCPU extends Jugador {

    public JugadorCPU(String nombre, int edad, String alias, String caracter, boolean jugadaMagicaDisponible) {
        super(nombre, edad, alias, caracter, jugadaMagicaDisponible);
    }

    /**
     * Genera una jugada para la CPU y retorna un array de Strings con valores de
     * coordenada y miniCoordenada
     **/
    public String[] generarJugadaPrimera(Tablero tablero) {
        // Simplemente se retorna una jugada aleatoria
        String[] coordenadas = { "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3" };
        String[] miniCoordenadas = { "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3" };

        String coordenada = coordenadas[(int) (Math.random() * coordenadas.length)];
        String miniCoordenada = miniCoordenadas[(int) (Math.random() * miniCoordenadas.length)];

        return new String[] { coordenada, miniCoordenada };
    }


//Solo minicoordenada
public String generarJugada(Tablero tablero) {
    // Simplemente se retorna una miniCoordenada aleatoria
    String[] miniCoordenadas = { "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3" };

    String miniCoordenada = miniCoordenadas[(int) (Math.random() * miniCoordenadas.length)];

    return miniCoordenada;
    }

}