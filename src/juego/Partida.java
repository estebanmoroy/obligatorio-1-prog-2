/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package juego;

/**
 * Representa una partida del juego Gran Tateti.
 */
public class Partida {
    // Constantes
    private static final String CARACTER_ROJO = "X";
    private static final String CARACTER_AZUL = "O";

    // Atributos
    private final Jugador jugadorRojo;
    private final Jugador jugadorAzul;
    private final Tablero tablero;
    private Jugador turnoActual;
    private boolean finalizada;
    private String resultado;
    private Jugador ganador;

    /**
     * Constructor que inicia una nueva partida entre dos jugadores.
     * 
     * @param jugadorRojo El jugador que usará las fichas rojas (X)
     * @param jugadorAzul El jugador que usará las fichas azules (O)
     */
    public Partida(Jugador jugadorRojo, Jugador jugadorAzul) {
        this.jugadorRojo = inicializarJugador(jugadorRojo, CARACTER_ROJO);
        this.jugadorAzul = inicializarJugador(jugadorAzul, CARACTER_AZUL);
        this.tablero = new Tablero();
        this.turnoActual = jugadorRojo;
        this.finalizada = false;
        this.resultado = "";
        this.ganador = null;
    }

    private Jugador inicializarJugador(Jugador jugador, String caracter) {
        jugador.setCaracter(caracter);
        jugador.setJugadaMagicaDisponible(true);
        return jugador;
    }

    /**
     * Registra una jugada en la partida.
     * 
     * @param coordenada            La coordenada del mini-tablero
     * @param coordenadaMiniTablero La coordenada dentro del mini-tablero
     * @return true si la jugada fue válida y se registró correctamente, false en
     *         caso contrario
     */
    public boolean registrarJugada(String coordenada, String coordenadaMiniTablero) {
        if (finalizada || !tablero.esJugadaValida(coordenada, coordenadaMiniTablero)) {
            return false;
        }

        ejecutarJugada(coordenada, coordenadaMiniTablero);
        verificarEstadoPartida();
        return true;
    }

    private void ejecutarJugada(String coordenada, String coordenadaMiniTablero) {
        tablero.jugada(turnoActual.getCaracter(), coordenada, coordenadaMiniTablero);
    }

    private void verificarEstadoPartida() {
        String estadoGanador = tablero.getGanadoresMiniTableros().determinarGanador();
        if (!estadoGanador.equals("indeterminado")) {
            finalizarPartida(turnoActual);
        } else if (tablero.estaLleno()) {
            finalizarPartida(null);
        } else {
            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        if (turnoActual == jugadorRojo) {
            turnoActual = jugadorAzul;
        } else {
            turnoActual = jugadorRojo;
        }
    }

    /**
     * Realiza una jugada mágica en la coordenada especificada.
     * 
     * @param coordenada La coordenada donde se realizará la jugada mágica
     */
    public void jugadaMagica(String coordenada) {
        if (turnoActual.isJugadaMagicaDisponible()) {
            tablero.limpiarMiniTablero(coordenada);
            turnoActual.setJugadaMagicaDisponible(false);
            verificarEstadoPartida();
        }
    }

    private void finalizarPartida(Jugador posibleGanador) {
        finalizada = true;
        ganador = posibleGanador;
        if (ganador != null) {
            resultado = ganador.getCaracter();
        } else {
            resultado = "Empate";
        }
    }

    /**
     * Finaliza la partida por abandono del jugador actual.
     */
    public void abandonarPartida() {
        if (turnoActual == jugadorRojo) {
            finalizarPartida(jugadorAzul);
        } else {
            finalizarPartida(jugadorRojo);
        }
    }

    /**
     * Finaliza la partida cuando el jugador actual debe jugar en un mini-tablero
     * lleno.
     * El jugador actual pierde y se declara ganador al oponente.
     */
    public void finalizarPartidaPorMiniTableroLleno() {
        this.finalizada = true;
        if (this.turnoActual == jugadorRojo) {
            this.ganador = jugadorAzul;
        } else {
            this.ganador = jugadorRojo;
        }
        this.resultado = this.ganador.getCaracter();
    }

    /**
     * Verifica si la partida ha finalizado.
     * 
     * @return true si la partida ha finalizado, false en caso contrario
     */
    public boolean isPartidaFinalizada() {
        return finalizada;
    }

    /**
     * Obtiene el jugador que tiene el turno actual.
     * 
     * @return El jugador que tiene el turno actual
     */
    public Jugador getTurnoActual() {
        return turnoActual;
    }

    /**
     * Obtiene el tablero de la partida.
     * 
     * @return El tablero de la partida
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Obtiene el jugador ganador de la partida.
     * 
     * @return El jugador ganador, o null si no hay ganador o la partida no ha
     *         finalizado
     */
    public Jugador getGanador() {
        return ganador;
    }

    /**
     * Obtiene el resultado de la partida.
     * 
     * @return El resultado de la partida ("X", "O" o "Empate")
     */
    public String getResultado() {
        return resultado;
    }
}