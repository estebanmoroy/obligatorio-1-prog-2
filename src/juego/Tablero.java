/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martínez 342426
 */
public class Tablero {

    // Atributos
    private MiniTablero[][] tableroPrincipal; // Matriz para almacenar los mini-tableros
    private MiniTablero ganadoresMiniTableros; // Minitablero para registrar los ganadores

    // Colores ANSI
    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[31m";
    private static final String AZUL = "\u001B[34m";
    private static final String FONDO_VERDE = "\u001B[42m";
    private static final String FONDO_AMARILLO = "\u001B[43m";

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

    public void setGanadoresMiniTableros(MiniTablero ganadoresMiniTableros) {
        this.ganadoresMiniTableros = ganadoresMiniTableros;
    }

    /** Muestra el tablero en su estado actual **/
    public String toString(String coordenadaAResaltar) {
        String retorno = "";
        String lineaHorizontalCompleta = "";

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Revisa si el casillero actual es el que hay que resaltar
                    String ast = FONDO_VERDE + "*" + RESET;
                    if (coordenadaAResaltar != null) {
                        if (getFila(coordenadaAResaltar) == i && getColumna(coordenadaAResaltar) == k) {
                            ast = FONDO_AMARILLO + "*" + RESET;
                        }
                    }

                    // Si es la primera columna agrega un asterisco al principio
                    if (k == 0) {
                        retorno += ast + " ";
                        lineaHorizontalCompleta += ast;
                    }

                    // Determina el ganador del miniTablero para luego pintarlo del color
                    // correspondiente
                    // y determina la cantidad de caracteres a remover en caso de que esté pintado
                    // Son 11 caracteres en lugar de 2 por el código ASCII que pinta el asterisco
                    int cantidadDeCaracteresARemover = 11;
                    String ganadorMiniTablero = this.ganadoresMiniTableros.getCasillero(i, k);
                    if (ganadorMiniTablero == "indeterminado" || ganadorMiniTablero == "E"
                            || ganadorMiniTablero.isEmpty()) {
                        ganadorMiniTablero = null;
                        cantidadDeCaracteresARemover = 9;
                    }

                    // Si es el miniTablero a resaltar, remueve el asterisco anterior y agrega uno
                    // del color correspondiente
                    if (coordenadaAResaltar != null) {
                        if (getFila(coordenadaAResaltar) == i && getColumna(coordenadaAResaltar) == k) {
                            retorno = retorno.substring(0, retorno.length() - cantidadDeCaracteresARemover) + ast + " ";
                            if (getColumna(coordenadaAResaltar) == 0) {
                                lineaHorizontalCompleta = ast;
                            } else {
                                lineaHorizontalCompleta = lineaHorizontalCompleta.substring(0,
                                        lineaHorizontalCompleta.length() - cantidadDeCaracteresARemover) + RESET + ast;
                            }
                        }
                    }

                    lineaHorizontalCompleta += lineaHorizontal(ganadorMiniTablero) + ast;
                    retorno += this.tableroPrincipal[i][k].filaToString(j, ganadorMiniTablero) + " " + ast + " ";

                }

                retorno += "\n";
                retorno += lineaHorizontalCompleta + "\n";
                lineaHorizontalCompleta = "";

            }

            // Manejo de lineas de asteriscos divisoras
            if (coordenadaAResaltar != null) {
                if (getFila(coordenadaAResaltar) == i || getFila(coordenadaAResaltar) - 1 == i) {
                    retorno += lineaAsteriscos(coordenadaAResaltar);
                } else {
                    retorno += lineaAsteriscos(null);
                }
            } else {
                retorno += lineaAsteriscos(null);
            }

            // Agregar la linea de asteriscos en la primera linea
            if (i == 0) {
                if (coordenadaAResaltar != null) {
                    if (getFila(coordenadaAResaltar) == 0) {
                        retorno = lineaAsteriscos(coordenadaAResaltar) + retorno;
                    } else {
                        retorno = lineaAsteriscos(null) + retorno;
                    }
                } else {
                    retorno = lineaAsteriscos(null) + retorno;
                }
            }

        }

        return retorno;
    }

    public String lineaHorizontal(String ganador) {
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

    public String lineaAsteriscos(String coordenada) {
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
