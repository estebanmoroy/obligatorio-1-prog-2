/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Sistema {
    private static final ArrayList<Partida> historialPartidas = new ArrayList<>();
    private static final ArrayList<Jugador> jugadores = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static ArrayList<Partida> getHistorialPartidas() {
        return historialPartidas;
    }

    public static void iniciarMenu() {
        JugadorCPU cpu = new JugadorCPU("cpu", 0, "CPU", null, false);
        agregarJugador(cpu);

        int opcion;
        do {
            Interfaz.mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 5);
    }

    private static int leerOpcion() {
        while (true) {
            try {
                System.out.print("Elige una opción: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                Interfaz.TextoRegistrarJugador();
                break;
            case 2:
                if (jugadores.size() < 3) {
                    System.out.println("Error: debe tener al menos 2 jugadores registrados.");
                } else {
                    jugar();
                }
                break;
            case 3:
                if (jugadores.size() < 2) {
                    System.out.println("Error: debe tener al menos 1 jugador registrado.");
                } else {
                    jugarVsCPU();
                }
                break;
            case 4:
                if (jugadores.isEmpty()) {
                    System.out.println("No hay jugadores registrados");
                } else {
                    Interfaz.mostrarRanking();
                }
                break;
            case 5:
                System.out.println("Saliendo del juego...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    public static void agregarJugador(Jugador nuevoJugador) {
        if (aliasUnico(nuevoJugador)) {
            jugadores.add(nuevoJugador);
            System.out.println("Jugador " + nuevoJugador.getAlias() + " registrado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un jugador con el alias '" + nuevoJugador.getAlias() + "'");
        }
    }

    private static void jugar() {
        Jugador jugador1 = seleccionarJugador();
        Jugador jugador2 = seleccionarJugadorExcluyendo(jugador1);
        ejecutarPartida(new Partida(jugador1, jugador2), false);
    }

    private static void jugarVsCPU() {
        JugadorCPU cpu = (JugadorCPU) jugadores.get(0);
        Jugador jugador1 = seleccionarJugador();
        ejecutarPartida(new Partida(jugador1, cpu), true);
    }

    private static void ejecutarPartida(Partida partida, boolean vsCPU) {
        String coordenadaTablero = null;
        boolean esPrimerMovimiento = true;

        while (!partida.isPartidaFinalizada()) {
            Jugador jugadorActual = partida.getTurnoActual();
            System.out.println("Turno de " + jugadorActual.getAlias() + " (" + jugadorActual.getCaracter() + ")");

            boolean jugadaValida = false;
            while (!jugadaValida) {
                String[] jugada = solicitarJugada(esPrimerMovimiento ? null : coordenadaTablero);

                if (jugada[0].equals("A")) {
                    partida.abandonarPartida();
                    guardarPartida(partida);
                    return;
                }

                if (jugada[0].equalsIgnoreCase("M") && !vsCPU) {
                    if (jugadorActual.isJugadaMagicaDisponible()) {
                        partida.jugadaMagica(jugada[1]);
                        jugadaValida = true;
                    } else {
                        System.out.println("Error: Ya has usado tu jugada mágica en esta partida.");
                    }
                } else {
                    if (esPrimerMovimiento) {
                        jugadaValida = partida.registrarJugada(jugada[0], jugada[1]);
                        if (jugadaValida) {
                            coordenadaTablero = jugada[1];
                            esPrimerMovimiento = false;
                        }
                    } else {
                        jugadaValida = partida.registrarJugada(coordenadaTablero, jugada[0]);
                        if (jugadaValida) {
                            coordenadaTablero = jugada[0];
                        }
                    }
                }
            }

            if (!partida.isPartidaFinalizada()) {
                System.out.println("Estado actual del tablero:");
                System.out.println(partida.getTablero().toString(coordenadaTablero));
            }
        }

        mostrarResultadoFinal(partida);
        guardarPartida(partida);
    }

    private static String[] solicitarJugada(String coordenadaTablero) {
        while (true) {
            try {
                if (coordenadaTablero == null) {
                    System.out.println(
                            "Ingrese su jugada 'coordenada,miniCoordenada' , 'M,coordenada' para jugada mágica o 'A' para abandonar:");
                } else {
                    System.out.println("Ingrese su jugada en el mini-tablero " + coordenadaTablero +
                            " ('A1'-'C3'), 'M' para jugada mágica o 'A' para abandonar:");
                }

                String input = scanner.nextLine().trim().toUpperCase();
                return procesarInput(input, coordenadaTablero);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Por favor, intente de nuevo.");
            }
        }
    }

    private static String[] procesarInput(String input, String coordenadaTablero) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("La entrada no puede estar vacía.");
        }

        if (input.equals("A")) {
            return new String[] { "A" };
        }

        if (input.startsWith("M")) {
            return procesarJugadaMagica(input);
        }

        return procesarJugadaNormal(input, coordenadaTablero);
    }

    private static String[] procesarJugadaMagica(String input) {
        String[] partes = input.split(",");
        if (partes.length != 2) {
            throw new IllegalArgumentException("Para la jugada mágica, use el formato 'M,coordenada'.");
        }
        validarFormatoCoordenada(partes[1]);
        return new String[] { "M", partes[1] };
    }

    private static String[] procesarJugadaNormal(String input, String coordenadaTablero) {
        if (coordenadaTablero == null) {
            String[] coordenadas = input.split(",");
            if (coordenadas.length != 2) {
                throw new IllegalArgumentException("Formato incorrecto. Use 'coordenada,miniCoordenada'.");
            }
            validarFormatoCoordenada(coordenadas[0]);
            validarFormatoCoordenada(coordenadas[1]);
            return coordenadas;
        } else {
            if (input.contains(",")) {
                throw new IllegalArgumentException("Para jugadas subsiguientes, ingrese solo la miniCoordenada.");
            }
            validarFormatoCoordenada(input);
            return new String[] { input };
        }
    }

    private static void validarFormatoCoordenada(String coordenada) {
        if (!coordenada.matches("[A-C][1-3]")) {
            throw new IllegalArgumentException(
                    "Coordenada inválida: " + coordenada + ". Debe ser de la forma 'A1', 'B2', etc.");
        }
    }

    public static void guardarPartida(Partida partida) {
        historialPartidas.add(partida);
    }

    private static boolean aliasUnico(Jugador nuevoJugador) {
        for (Jugador jugador : jugadores) {
            if (jugador.equals(nuevoJugador)) {
                return false;
            }
        }
        return true;
    }

    public static String[][] calcularRanking() {
        String[][] ranking = new String[jugadores.size()][2];
        for (int i = 0; i < jugadores.size(); i++) {
            ranking[i][0] = jugadores.get(i).getAlias();
            ranking[i][1] = "0";
        }

        for (Partida partida : historialPartidas) {
            Jugador ganador = partida.getGanador();
            if (ganador != null) {
                for (int i = 0; i < ranking.length; i++) {
                    if (ranking[i][0].equals(ganador.getAlias())) {
                        int victorias = Integer.parseInt(ranking[i][1]);
                        ranking[i][1] = Integer.toString(victorias + 1);
                        break;
                    }
                }
            }
        }

        // Ordenar el ranking por victorias (de mayor a menor)
        for (int i = 0; i < ranking.length - 1; i++) {
            for (int j = 0; j < ranking.length - i - 1; j++) {
                if (Integer.parseInt(ranking[j][1]) < Integer.parseInt(ranking[j + 1][1])) {
                    String[] temp = ranking[j];
                    ranking[j] = ranking[j + 1];
                    ranking[j + 1] = temp;
                }
            }
        }

        return ranking;
    }

    private static Jugador seleccionarJugador() {
        return seleccionarJugadorGenerico(null);
    }

    private static Jugador seleccionarJugadorExcluyendo(Jugador excluido) {
        return seleccionarJugadorGenerico(excluido);
    }

    private static Jugador seleccionarJugadorGenerico(Jugador excluido) {
        System.out.println("Lista de jugadores:");
        for (int i = 1; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (excluido == null || !jugador.equals(excluido)) {
                System.out.println((i) + ". " + jugador.getAlias());
            }
        }

        while (true) {
            try {
                System.out.println("Selecciona un jugador ingresando el número correspondiente:");
                int seleccion = scanner.nextInt() - 1;
                scanner.nextLine(); // Limpiar el buffer

                Jugador jugadorSeleccionado = jugadores.get(seleccion);
                if (excluido == null || !jugadorSeleccionado.equals(excluido)) {
                    return jugadorSeleccionado;
                } else {
                    System.out.println("Error: El jugador seleccionado está excluido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Selección fuera de rango.");
            }
        }
    }

    private static void mostrarResultadoFinal(Partida partida) {
        String resultado = partida.getResultado();
        if (resultado.equals("Empate")) {
            System.out.println("La partida ha terminado en empate.");
        } else {
            Jugador ganador = partida.getGanador();
            System.out.println("El ganador es: " + ganador.getAlias() + " (" + resultado + ")");
        }
    }
}