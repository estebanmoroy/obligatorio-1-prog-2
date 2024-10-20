/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martínez 342426
 */
public class MiniTablero {
    private String[][] miniTablero;

    // Códigos de color ANSI
    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[31m";
    private static final String AZUL = "\u001B[34m";

    // Constructor
    public MiniTablero() {
        this.miniTablero = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.miniTablero[i][j] = "";
            }
        }

    }

    public boolean jugada(String coordenada, String caracterJugador) {
        int fila = getFila(coordenada);
        int columna = getColumna(coordenada);
        if (this.miniTablero[fila][columna].isEmpty()) {
            this.miniTablero[fila][columna] = caracterJugador;
            return true; // Jugada válida
        } else {
            return false; // Jugada inválida
        }
    }

    public String getCasillero(int fila, int columna) {
        return this.miniTablero[fila][columna];
    }

    // Sobrecarga del método limpiar para usar en ganadoresMiniTableros
    public void limpiarMiniTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.miniTablero[i][j] = "";
            }
        }
    }

    // limpiar un casillero específico
    public void limpiarCasillero(String coordenada) {
        int fila = getFila(coordenada);
        int columna = getColumna(coordenada);
        this.miniTablero[fila][columna] = "";
    }

    public boolean estaCasilleroVacio(String coordenada) {
        int fila = getFila(coordenada);
        int columna = getColumna(coordenada);
        return this.miniTablero[fila][columna].isEmpty();
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

    public String determinarGanador() {
        String resultado = "indeterminado";
        // Checkeo de filas
        for (int i = 0; i < 3; i++) {
            if (!this.miniTablero[i][0].isEmpty() &&
                    this.miniTablero[i][0].equals(this.miniTablero[i][1]) &&
                    this.miniTablero[i][1].equals(this.miniTablero[i][2])) {
                resultado = this.miniTablero[i][0];
            }
        }
        // Checkeo de columnas
        for (int j = 0; j < 3; j++) {
            if (!this.miniTablero[0][j].isEmpty() &&
                    this.miniTablero[0][j].equals(this.miniTablero[1][j]) &&
                    this.miniTablero[1][j].equals(this.miniTablero[2][j])) {
                resultado = this.miniTablero[0][j];
            }
        }
        // Checkeo de diagonales
        if (!this.miniTablero[0][0].isEmpty() &&
                this.miniTablero[0][0].equals(this.miniTablero[1][1]) &&
                this.miniTablero[1][1].equals(this.miniTablero[2][2])) {
            resultado = this.miniTablero[0][0];
        }
        if (!this.miniTablero[0][2].isEmpty() &&
                this.miniTablero[0][2].equals(this.miniTablero[1][1]) &&
                this.miniTablero[1][1].equals(this.miniTablero[2][0])) {
            resultado = this.miniTablero[0][2];
        }
        // Checkeo de empate
        if (estaLleno() && resultado == "indeterminado" || resultado == "E") {
            /*
             * Se agrega checkeo con caracter "E" dado a que en el tablero donde registramos
             * los ganadores,
             * el caracter "E" se usa para indicar los empates
             */

            resultado = "E";
        }
        return resultado;
    }

    public boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.miniTablero[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setMiniTablero(String[][] miniTablero) {
        this.miniTablero = miniTablero;
    }

    public String filaToString(int numeroDeFila, String ganador) {
        String retorno = "";
        String color = null;

        if (ganador == "X") {
            color = ROJO;
        } else if (ganador == "O") {
            color = AZUL;
        }

        for (int i = 0; i < this.miniTablero[numeroDeFila].length; i++) {
            String contenido = this.miniTablero[numeroDeFila][i];
            if (contenido.isEmpty()) {
                retorno += " "; // Si el casillero está vacío agrega un espacio
            } else {
                if (color != null) {
                    // Si se especifica un color, colorea todo (incluidos los separadores)
                    retorno += color + contenido + RESET;
                } else {
                    // Si no se especifica color, usa rojo para X y azul para O
                    if (contenido.equals("X")) {
                        retorno += ROJO + contenido + RESET;
                    } else if (contenido.equals("O")) {
                        retorno += AZUL + contenido + RESET;
                    } else {
                        retorno += contenido;
                    }
                }
            }
            if (i < this.miniTablero[numeroDeFila].length - 1) {
                if (color != null) {
                    retorno += color + "|" + RESET; // Colorea el separador si se especifica un color
                } else {
                    retorno += "|"; // Agrega las barras divisorias entre columnas sin color
                }
            }
        }
        return retorno;

    }

    public String toStringColor(String color) {
        String retorno = "";
        for (int i = 0; i < this.miniTablero.length; i++) {
            if (!(i < 2)) { // Revisa si no es la ultima columna
                retorno += this.filaToString(i, color);
            } else {
                retorno += this.filaToString(i, color) + "\n"; // En caso de que si le agrega un salto de liena
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < this.miniTablero.length; i++) {
            if (!(i < 2)) { // Revisa si no es la ultima columna
                retorno += this.filaToString(i, null);
            } else {
                retorno += this.filaToString(i, null) + "\n"; // En caso de que si le agrega un salto de liena
            }
        }
        return retorno;
    }

}
