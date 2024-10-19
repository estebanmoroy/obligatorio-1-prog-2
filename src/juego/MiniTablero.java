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

    // Sobrecarga del método limpiar para usar en ganadoresMiniTableros
    public void limpiarMiniTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.miniTablero[i][j] = "";
            }
        }
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

    public String filaToString(int numeroDeFila) {
        String retorno = "";

        for (int i = 0; i < this.miniTablero[numeroDeFila].length; i++) {
            if (this.miniTablero[numeroDeFila][i].isEmpty()) {
                retorno += " "; // Si el casillero está vacío agrega un espacio
            } else {
                retorno += this.miniTablero[numeroDeFila][i]; // Si no está vacío agrega el valor
            }
            if (i < this.miniTablero[numeroDeFila].length - 1) {
                retorno += "|"; // Agrega las barras divisorias entre columnas
            }
        }
        return retorno;

    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < this.miniTablero.length; i++) {
            if (!(i < 2)) { // Revisa si no es la ultima columna
                retorno += this.filaToString(i);
            } else {
                retorno += this.filaToString(i) + "\n"; // En caso de que si le agrega un salto de liena
            }
        }
        return retorno;
    }

}
