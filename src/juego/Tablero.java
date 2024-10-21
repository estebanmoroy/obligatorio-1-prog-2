/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 * Representa el tablero principal del juego Gran Tateti
 * 
 * @author Esteban Moroy 338885, Facundo Martínez 342426
 */
public class Tablero {

    // Atributos
    private MiniTablero[][] tableroPrincipal;
    private MiniTablero ganadoresMiniTableros;

    // Colores ANSI
    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[31m";
    private static final String AZUL = "\u001B[34m";
    private static final String FONDO_VERDE = "\u001B[42m";
    private static final String FONDO_AMARILLO = "\u001B[43m";

    /**
     * Constructor que inicializa un nuevo tablero principal.
     */
    public Tablero() {
        this.tableroPrincipal = new MiniTablero[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tableroPrincipal[i][j] = new MiniTablero();
            }
        }

        this.ganadoresMiniTableros = new MiniTablero();
    }

    /**
     * Realiza una jugada en el tablero principal.
     * 
     * @param caracterJugador         El carácter del jugador ("X" o "O")
     * @param coordenada              La coordenada del mini-tablero
     * @param coordenadaEnMiniTablero La coordenada dentro del mini-tablero
     * @return true si la jugada es válida, false si no
     */
    public boolean jugada(String caracterJugador, String coordenada, String coordenadaEnMiniTablero) {
        MiniTablero miniTablero = this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
        boolean jugada = miniTablero.jugada(coordenadaEnMiniTablero, caracterJugador);

        if (jugada && !miniTablero.determinarGanador().equals("indeterminado")) {
            this.ganadoresMiniTableros.jugada(coordenada, miniTablero.determinarGanador());
        }

        return jugada;
    }

    /**
     * Verifica si una jugada es válida.
     * 
     * @param coordenada              La coordenada del mini-tablero
     * @param coordenadaEnMiniTablero La coordenada dentro del mini-tablero
     * @return true si la jugada es válida, false si no
     */
    public boolean esJugadaValida(String coordenada, String coordenadaEnMiniTablero) {
        MiniTablero miniTablero = this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
        return !estaLleno() && miniTablero.estaCasilleroVacio(coordenadaEnMiniTablero);
    }

    /**
     * Limpia un mini-tablero específico (jugada mágica).
     * 
     * @param coordenada La coordenada del mini-tablero a limpiar
     */
    public void limpiarMiniTablero(String coordenada) {
        int fila = getFila(coordenada);
        int columna = getColumna(coordenada);

        this.tableroPrincipal[fila][columna].limpiarMiniTablero();
        this.ganadoresMiniTableros.limpiarCasillero(coordenada);
    }

    /**
     * Verifica si el tablero principal está completamente lleno.
     * 
     * @return true si está lleno, false si no
     */
    public boolean estaLleno() {
        boolean retorno = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                retorno = retorno && this.tableroPrincipal[i][j].estaLleno();
            }
        }
        return retorno;
    }

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

    /**
     * Obtiene un mini-tablero específico.
     * 
     * @param coordenada La coordenada del mini-tablero
     * @return El MiniTablero en la coordenada especificada
     */
    public MiniTablero getMiniTablero(String coordenada) {
        return this.tableroPrincipal[getFila(coordenada)][getColumna(coordenada)];
    }

    /**
     * Obtiene el mini-tablero de ganadores.
     * 
     * @return El MiniTablero que registra los ganadores de cada mini-tablero
     */
    public MiniTablero getGanadoresMiniTableros() {
        return this.ganadoresMiniTableros;
    }

    /**
     * Genera una representación en String del tablero principal.
     * 
     * @param coordenadaAResaltar La coordenada del mini-tablero a resaltar
     * @return Una String que representa el tablero principal completo
     */
    public String toString(String coordenadaAResaltar) {
        String retorno = "";
        String lineaHorizontalCompleta = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    String ast = FONDO_VERDE + "*" + RESET;
                    if (coordenadaAResaltar != null && getFila(coordenadaAResaltar) == i
                            && getColumna(coordenadaAResaltar) == k) {
                        ast = FONDO_AMARILLO + "*" + RESET;
                    }

                    if (k == 0) {
                        retorno += ast + " ";
                        lineaHorizontalCompleta += ast;
                    }

                    int cantidadDeCaracteresARemover = 11;
                    String ganadorMiniTablero = this.ganadoresMiniTableros.getCasillero(i, k);
                    if (ganadorMiniTablero.equals("indeterminado") || ganadorMiniTablero.equals("E")
                            || ganadorMiniTablero.isEmpty()) {
                        ganadorMiniTablero = null;
                        cantidadDeCaracteresARemover = 9;
                    }

                    if (coordenadaAResaltar != null && getFila(coordenadaAResaltar) == i
                            && getColumna(coordenadaAResaltar) == k) {
                        retorno = retorno.substring(0, retorno.length() - cantidadDeCaracteresARemover) + ast + " ";
                        if (getColumna(coordenadaAResaltar) == 0) {
                            lineaHorizontalCompleta = ast;
                        } else {
                            lineaHorizontalCompleta = lineaHorizontalCompleta.substring(0,
                                    lineaHorizontalCompleta.length() - cantidadDeCaracteresARemover) + RESET + ast;
                        }
                    }

                    lineaHorizontalCompleta += lineaHorizontal(ganadorMiniTablero) + ast;
                    retorno += this.tableroPrincipal[i][k].filaToString(j, ganadorMiniTablero) + " " + ast + " ";
                }

                retorno += "\n";
                retorno += lineaHorizontalCompleta + "\n";
                lineaHorizontalCompleta = "";
            }

            if (coordenadaAResaltar != null
                    && (getFila(coordenadaAResaltar) == i || getFila(coordenadaAResaltar) - 1 == i)) {
                retorno += lineaAsteriscos(coordenadaAResaltar);
            } else {
                retorno += lineaAsteriscos(null);
            }

            if (i == 0) {
                if (coordenadaAResaltar != null && getFila(coordenadaAResaltar) == 0) {
                    retorno = lineaAsteriscos(coordenadaAResaltar) + retorno;
                } else {
                    retorno = lineaAsteriscos(null) + retorno;
                }
            }
        }

        return retorno;
    }

    private String lineaHorizontal(String ganador) {
        String linea = "--+-+--";
        String retorno = "";

        if (ganador == "X") {
            retorno += ROJO + linea + RESET;
        } else if (ganador == "O") {
            retorno += AZUL + linea + RESET;
        } else {
            retorno += linea;
        }

        return retorno;
    }

    private String lineaAsteriscos(String coordenada) {
        String asteriscos = "";
        String retorno = "";

        if (coordenada == null) {
            retorno = FONDO_VERDE + "*".repeat(25) + RESET + "\n";
        } else {
            int nroColumna = getColumna(coordenada);
            for (int i = 0; i < 3; i++) {
                if (nroColumna == i) {
                    asteriscos += FONDO_AMARILLO + "*".repeat(9) + RESET;
                } else {
                    asteriscos += FONDO_VERDE + "*".repeat(8) + RESET;
                }
            }
            retorno += asteriscos + "\n";
        }

        return retorno;
    }
}