/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez
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

    
    /** Constructor para pruebas facu
    public Partida(Jugador jugadorRojo, Jugador jugadorAzul, Tablero tablero, Jugador turnoActual, boolean finalizada, String resultado, Jugador ganador) {
        this.jugadorRojo = jugadorRojo;
        this.jugadorRojo.setCaracter("X");
        this.jugadorRojo.setJugadaMagicaDisponible(true);
        
        this.jugadorAzul = jugadorAzul;
        this.jugadorAzul.setCaracter("O");
        this.jugadorAzul.setJugadaMagicaDisponible(true);
        
        // Asignar directamente sin verificación de null
        this.tablero = tablero;
        this.turnoActual = turnoActual;
        
        this.finalizada = finalizada;
        this.resultado = resultado;
        this.ganador = ganador;
    }
    */
    // Métodos

    /** Inicia una nueva partida entre los jugadores */
    public void nuevaPartida(Jugador jugadorRojo, Jugador jugadorAzul) {
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
        this.ganador = ganador;
    }

    //getters
    public Jugador getGanador() {
        return ganador;
    }

    /** Registra una jugada del jugador en la partida */
    public void registrarJugada(Jugador jugador, String coordenada, String coordenadaMiniTablero) {
        if (tablero.esJugadaValida(coordenada, coordenadaMiniTablero)) {
            ejecutarJugada(jugador, coordenada, coordenadaMiniTablero);
            verificarGanador();
            if (!finalizada) {
                cambiarTurno();
            }
        }
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

    /** Retorna el jugador que tiene el turno actual */
    public Jugador getTurnoActual() {
        return turnoActual;
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
        if (!ganador.equals("indeterminado")) {
            finalizarPartida();
            resultado = turnoActual.getCaracter();
        } else if (tablero.estaLleno()) {
            finalizarPartida();
            resultado = "Empate";
        }
    }

    /** Finaliza la partida y establece el resultado */
    public void finalizarPartida() {
        finalizada = true;
        //aplicar ganador
    }

    public void abandonarPartida() {
        finalizarPartida();
        if (turnoActual.equals(jugadorRojo)) {
            resultado = "O";
        } else {
            resultado = "X";
        }
        //aplicar ganador
    }

    /** Verifica si la partida está finalizada */
    public boolean isPartidaFinalizada() {
        return finalizada;
    }

}
