/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez 342426
 */
public class Partida {

    // Atributos
    private Jugador jugadorRojo;
    private Jugador jugadorAzul;
    private Tablero tablero;
    private Jugador turnoActual;
    private boolean finalizada;
    private String resultado;
    private Jugador ganador;

    // Métodos

    /** Inicia una nueva partida entre los jugadores */
    public  Partida(Jugador jugadorRojo, Jugador jugadorAzul) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorRojo.setCaracter("X");
        this.jugadorRojo.setJugadaMagicaDisponible(true);
        this.turnoActual = jugadorRojo; // Siempre empieza el jugador rojo
        this.jugadorAzul = jugadorAzul;
        this.jugadorAzul.setCaracter("O");
        this.jugadorAzul.setJugadaMagicaDisponible(true);
        this.tablero = new Tablero();
        this.finalizada = false;
        this.resultado = "";
        this.ganador = null;
    }
    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    /** Registra una jugada del jugador en la partida */
    public boolean registrarJugada(Jugador jugador, String coordenada, String coordenadaMiniTablero) {
        boolean retorno = false;
        if (tablero.esJugadaValida(coordenada, coordenadaMiniTablero)) {
            ejecutarJugada(jugador, coordenada, coordenadaMiniTablero);
            verificarGanador();
            if (!finalizada) {
                cambiarTurno();
            }
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    /** Ejecuta una jugada en el tablero */
    public void ejecutarJugada(Jugador jugador, String coordenada, String coordenadaMiniTablero) {
        tablero.jugada(jugador.getCaracter(), coordenada, coordenadaMiniTablero);
    }

    /** Ejecuta la jugada de la CPU en el tablero */
    public void ejecutarJugadaCPU() {
        if (turnoActual instanceof JugadorCPU) {
            JugadorCPU cpu = (JugadorCPU) turnoActual;
            String[] jugada = cpu.generarJugada(tablero);
            while (!tablero.esJugadaValida(jugada[0], jugada[1])) {
                jugada = cpu.generarJugada(tablero);
            }
            registrarJugada(cpu, jugada[0], jugada[1]);
        }
    }

    /** Cambia el turno al siguiente jugador */
    public void cambiarTurno() {
        if (turnoActual == jugadorRojo) {
            turnoActual = jugadorAzul;
        } else {
            turnoActual = jugadorRojo;
        }
    }

    /** Realiza la jugada mágica del jugador */
    public void jugadaMagica(Jugador jugador, String coordenada) {
        if (jugador.isJugadaMagicaDisponible()) {
            tablero.limpiarMiniTablero(coordenada);
            jugador.setJugadaMagicaDisponible(false);
        }
        cambiarTurno();
    }

    /** Verifica si hay un ganador en la partida */
    public void verificarGanador() {
        String ganador = tablero.getGanadoresMiniTableros().determinarGanador();
        System.out.println("chequeo de ganador");
        System.out.println(ganador);
        if (!ganador.equals("indeterminado")) {
            finalizarPartida();
            resultado = turnoActual.getCaracter();
            setGanador(turnoActual);
        } else if (tablero.estaLleno()) {
            finalizarPartida();
            resultado = "Empate";
        }
    }

    /** Finaliza la partida y establece el resultado */
    public void finalizarPartida() {
        finalizada = true;
    }

    public void abandonarPartida() {
        
        if (turnoActual.equals(jugadorRojo)) {
            System.out.println("ganador jugador2: " + jugadorAzul);
            resultado = "O";
            ganador=jugadorAzul;
        } else {
            resultado = "X";
            System.out.println("ganador jugador1" + jugadorRojo);
            ganador=jugadorRojo;
        }
        finalizarPartida();
        
    }

    /** Verifica si la partida está finalizada */
    public boolean isPartidaFinalizada() {
        return finalizada;
    }

    /** Retorna el jugador que tiene el turno actual */
    public Jugador getTurnoActual() {
        return turnoActual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public String getResultado() {
        return resultado;
    }

}
