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
    private MiniTablero[][] tableroPrincipal; // Matriz para almacenar los mini-tableros
    private MiniTablero ganadoresMiniTableros; // Minitablero para registrar los ganadores

    // Métodos

    /** Inicializa un nuevo tablero */
    public Tablero() {
        this.tableroPrincipal = new MiniTablero[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tableroPrincipal[i][j] = new MiniTablero();
            }
        }

        this.ganadoresMiniTableros = new MiniTablero();

    }

    /** Realiza una jugada en el tablero */
    public boolean jugada(String caracterJugador, String coordenada, String coordenadaEnMiniTablero) {
        MiniTablero miniTablero = this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
        boolean jugada = miniTablero.jugada(coordenadaEnMiniTablero, caracterJugador);

        // Revisa si la jugada es valida y si hay un ganador con esa jugada
        if (jugada && miniTablero.determinarGanador() != "indeterminado") {
            // En caso de que si, registra el ganador en ganadoresMiniTableros
            this.ganadoresMiniTableros.jugada(coordenada, miniTablero.determinarGanador());
            System.out.println("primer minitablero ganado");
        }

        return jugada;
    }

    public boolean esJugadaValida(String coordenada, String coordenadaEnMiniTablero) {
        MiniTablero miniTablero = this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
        if (!estaLleno() && miniTablero.estaCasilleroVacio(coordenadaEnMiniTablero)) {
            return true;
        } else {
            return false;
        }
    }

    /** Ejecuta la jugada mágica en el mini-tablero */
    public void limpiarMiniTablero(String coordenada) {
        MiniTablero miniTablero = this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
        miniTablero.limpiarMiniTablero();
    }

    /** Verifica si el tablero está completo */
    public boolean estaLleno() {
        boolean retorno = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                retorno = retorno && this.tableroPrincipal[i][j].estaLleno();
            }
        }
        return retorno;
    }

    /**
     * Dada una coordenada, devuelve el valor númerico de la fila correspondiente
     */
    private int getFila(String coordenada) {
        int retorno;
        switch (coordenada.charAt(0)) {
            case 'A':
                retorno = 0;
                break;
            case 'B':
                retorno = 1;
                break;
            case 'C':
                retorno = 2;
                break;
            default:
                retorno = -1;
                break;
        }
        return retorno;
    }

    /**
     * Dada una coordenada, devuelve el valor númerico de la columna correspondiente
     */
    private int getColumna(String coordenada) {
        int retorno;
        switch (coordenada.charAt(1)) {
            case '1':
                retorno = 0;
                break;
            case '2':
                retorno = 1;
                break;
            case '3':
                retorno = 2;
                break;
            default:
                retorno = -1;
                break;
        }
        return retorno;
    }

    /** Asigna un MiniTablero a una coordenada del tablero principal **/
    public void setMiniTablero(int fila, int columna, MiniTablero miniTablero) {
        this.tableroPrincipal[fila][columna] = miniTablero;
    }

    public MiniTablero getGanadoresMiniTableros() {
        return this.ganadoresMiniTableros;
    }

    @Override
    /** Muestra el tablero en su estado actual **/
    public String toString() {
        String retorno = "";
        String lineaAsteriscos = "*".repeat(25) + "\n";
        String lineaHorizontal = "*--+-+--*--+-+--*--+-+--*\n";

        for (int i = 0; i < 3; i++) {
            retorno += lineaAsteriscos;

            for (int j = 0; j < 3; j++) {
                retorno += "* ";
                for (int k = 0; k < 3; k++) {
                    retorno += this.tableroPrincipal[i][k].filaToString(j) + " * ";
                }
                retorno += "\n";
                retorno += lineaHorizontal;
            }
        }
        retorno += lineaAsteriscos;

        return retorno;
    }

}
