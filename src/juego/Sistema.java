/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez 342426
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class Sistema {
    // Atributos
    private static ArrayList<Partida> historialPartidas = new ArrayList<>();
    private static ArrayList<Jugador> jugadores = new ArrayList<>();

    // Métodos

    /** Getters */
    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static ArrayList<Partida> getHistorialPartidas() {
        return historialPartidas;
    }

    // menu
    /** Inicia menu */
    public static void iniciarMenu() {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            Interfaz.mostrarMenu();
            System.out.print("Elige una opción: ");
            try {
                opcion = teclado.nextInt();
                procesarOpcion(opcion);
            } catch (InputMismatchException e) {
                // Maneja el caso donde el usuario ingresa un valor que no es un número
                System.out.println("Favor digite opcion valida");
                teclado.nextLine(); // Limpia la entrada incorrecta
            }
        }

    }

    /** Procesa la opción seleccionada por el usuario */

    public static void procesarOpcion(int opcion) {
        if (opcion == 1) {
            System.out.println(" opcion 1:");
            Interfaz.TextoRegistrarJugador();

        }
        ;
        if (opcion == 2) {
            System.out.println(" opcion 2:");
            if (jugadores.isEmpty() || jugadores.size() < 2) {
                System.out.println("Error: debe tener al menos 2 jugadores registrados.");
            } else {
                Sistema.jugar();
            }

        }
        ;
        if (opcion == 3) {
            System.out.println(" opcion 3:");
            if (jugadores.isEmpty() || jugadores.size() < 1) {
                System.out.println("Error: debe tener al menos 1 jugador registrado.");
            } else {
                Sistema.jugarVsCPU();
            }

        }
        ;
        if (opcion == 4) {
            System.out.println(" opcion 4:");
            if (jugadores.isEmpty()) {
                System.out.println("No hay jugadores registrados");
            } else {
                Interfaz.mostrarRanking();
            }

        }
        ;
        if (opcion == 5) {
            System.out.println(" opcion 5:");
            // salir();

        }
    }

    // Método para seleccionar un jugador sin exclusión
    private static Jugador seleccionarJugadorGenerico(Jugador excluido) {
        /**
         * Procesa la opción seleccionada por el usuario
         * if (jugadores.isEmpty() || jugadores.size() < 1) {
         * System.out.println("No hays jugadores disponibles.");
         * return null;
         * }
         */
        System.out.println("Lista de jugadores:");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            // Si hay un jugador a excluir, no lo mostramos en la lista
            if (excluido == null || !jugador.equals(excluido)) {
                System.out.println((i + 1) + ". " + jugador.getAlias());
            }
        }

        Scanner teclado = new Scanner(System.in);
        Jugador jugadorSeleccionado = null;
        boolean seleccionValida = false;

        // Repetir hasta que la selección sea válida
        while (!seleccionValida) {
            try {
                System.out.println("Selecciona un jugador ingresando el número correspondiente:");
                int seleccion = teclado.nextInt() - 1; // Ajustar el índice

                jugadorSeleccionado = jugadores.get(seleccion);

                // Verificar que no se seleccionó al jugador excluido
                if (excluido == null || !jugadorSeleccionado.equals(excluido)) {
                    seleccionValida = true; // La selección es válida, salir del bucle
                } else {
                    System.out.println("Error: El jugador seleccionado está excluido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                teclado.next(); // Limpiar el buffer de entrada
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Seleccion fuera de rango.");
            }
        }

        return jugadorSeleccionado;
    }

    // Método para seleccionar un jugador sin exclusión
    public static Jugador seleccionarJugador() {
        return seleccionarJugadorGenerico(null); // Llamar al método sin excluir a ningún jugador
    }

    // Método para seleccionar un jugador excluyendo uno específico
    public static Jugador seleccionarJugadorExcluyendo(Jugador excluido) {
        return seleccionarJugadorGenerico(excluido); // Llamar al método excluyendo al jugador especificado
    }

    // juegos
    /** agregar jugador */
    public static void agregarJugador(Jugador nuevoJugador) {
        if (aliasUnico(nuevoJugador)) {
            jugadores.add(nuevoJugador); // Alias es único, se puede agregar el jugador
            System.out.println("Jugador:" + nuevoJugador.getAlias() + "registrado exitosamente.");
        } else {
            System.out.println("Error: Ya existe un jugador con el alias '" + nuevoJugador.getAlias());
        }

    }

    /** Inicia una nueva partida entre dos jugadores */
    public static void jugar() {

        Jugador jugador1 = seleccionarJugador();
        Jugador jugador2 = seleccionarJugadorExcluyendo(jugador1);

        Partida partida = new Partida(jugador1, jugador2);
        //partida.Partida(jugador1, jugador2);
        System.out.println("Jugador 1 es: " + jugador1.getAlias() + " (X)");
        System.out.println("Jugador 2 es: " + jugador2.getAlias() + " (O)");

        String coordenadaTablero = null;
        boolean esPrimerMovimiento = true;

        // Loop principal del juego
        while (!partida.isPartidaFinalizada()) {
            Jugador jugadorActual = partida.getTurnoActual();
            System.out.println("Turno de " + jugadorActual.getAlias() + " (" + jugadorActual.getCaracter() + ")");

            boolean jugadaValida = false;
            while (!jugadaValida) {
                /*
                 * Si es la primera jugada en la partida también solicita la coordenada del
                 * tablero a jugar
                 */
                String[] jugada;
                if (esPrimerMovimiento) {
                    jugada = solicitarJugada(null); // Solicita coordenada y mini-coordenada
                } else {
                    jugada = solicitarJugada(coordenadaTablero); // Solicita solo mini-coordenada
                }
                // Manejo de jugada de abandono
                if (jugada[0].equals("A")) {
                    System.out.println(jugadorActual.getAlias() + " ha decidido abandonar la partida.");
                    partida.abandonarPartida();
                    guardarPartida(partida);
                    return;
                }
                // Manejo de jugada mágica
                if (jugada[0].equalsIgnoreCase("M")) {
                    if (jugadorActual.isJugadaMagicaDisponible()) {
                        partida.jugadaMagica(jugadorActual, jugada[1]);
                        System.out.println(jugadorActual.getAlias() + " ha usado su jugada mágica en " + jugada[1]);
                        jugadaValida = true;
                    } else {
                        System.out.println("Error: " + jugadorActual.getAlias()
                                + " ya ha usado su jugada mágica en esta partida.");
                    }
                } else {
                    if (esPrimerMovimiento) {
                        jugadaValida = partida.registrarJugada(jugadorActual, jugada[0], jugada[1]);
                        if (jugadaValida) {
                            coordenadaTablero = jugada[1];
                            esPrimerMovimiento = false;
                        }
                    } else {
                        jugadaValida = partida.registrarJugada(jugadorActual, coordenadaTablero, jugada[0]);
                        if (jugadaValida) {
                            coordenadaTablero = jugada[0];
                        }
                    }

                    if (!jugadaValida) {
                        System.out.println("Jugada inválida. Intente de nuevo.");
                    }
                }
            }

            System.out.println("Estado actual del tablero:");
            System.out.println(partida.getTablero().toString());

            partida.verificarGanador();
        }

        // Mostrar resultado final
        String resultado = partida.getResultado();
        if (resultado.equals("Empate")) {
            System.out.println("La partida ha terminado en empate.");
        } else {
            Jugador ganador;
            if (resultado.equals("X")) {
                ganador = jugador1;
            } else {
                ganador = jugador2;
            }
            System.out.println("El ganador es: " + ganador.getAlias() + " (" + resultado + ")");
            guardarPartida(partida);

            
        }

        // Guardar la partida en el historial
        //guardarPartida(partida);
        //no se necesita actualizar
        // Actualizar el ranking
        //calcularRanking();

    }

    /**
     * Muestra mensaje de opciones, lee un input del teclado y retorna el output
     * correspondiente
     */
    private static String[] solicitarJugada(String coordenadaTablero) {
        Scanner scanner = new Scanner(System.in);
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

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("La entrada no puede estar vacía.");
                }

                if (input.equals("A")) {
                    return new String[] { "A" };
                }

                if (input.startsWith("M")) {
                    String[] parts = input.split(",");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Para la jugada mágica, use el formato 'M,coordenada'.");
                    }
                    validarFormatoCoordenada(parts[1]);
                    return new String[] { "M", parts[1] };
                }

                if (coordenadaTablero == null) {
                    String[] coordenadas = input.split(",");
                    if (coordenadas.length != 2) {
                        throw new IllegalArgumentException("Formato incorrecto. Use 'coordenada,miniCoordenada'.");
                    }
                    validarFormatoCoordenada(coordenadas[0]);
                    validarFormatoCoordenada(coordenadas[1]);
                    return coordenadas;
                } else {
                    // Para jugadas subsiguientes, aceptamos una sola coordenada
                    if (input.contains(",")) {
                        throw new IllegalArgumentException(
                                "Para jugadas subsiguientes, ingrese solo la miniCoordenada.");
                    }
                    validarFormatoCoordenada(input);
                    return new String[] { input };
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Por favor, intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage() + " Por favor, intente de nuevo.");
            }
        }
    }

    private static void validarFormatoCoordenada(String coordenada) {
        if (!coordenada.matches("[A-C][1-3]")) {
            throw new IllegalArgumentException(
                    "Coordenada inválida: " + coordenada + ". Debe ser de la forma 'A1', 'B2', etc.");
        }
    }

    /** Inicia una partida contra la CPU */
    public static void jugarVsCPU() {
        // Inicia una partida contra la computadora
        Jugador jugador1 = seleccionarJugador();
        JugadorCPU cpu = (JugadorCPU) jugadores.get(0); //cast para jugadorCPU
        //Partida partida = new Partida();
        //partida.Partida(jugador1, cpu);
        System.out.println("Jugador 1 es: " + jugador1.getAlias() + " (X)");
        System.out.println("Jugador 2 es: " + "CPU" + " (O)");

        String coordenadaTablero = null;
        boolean esPrimerMovimiento = true;

        
        // Loop principal del juego
        // while (!partida.isPartidaFinalizada()) {
        //     Jugador jugadorActual = partida.getTurnoActual();
        //     System.out.println("Turno de " + jugadorActual.getAlias() + " (" + jugadorActual.getCaracter() + ")");

        //     boolean jugadaValida = false;
        //     while (!jugadaValida) {
        //         /*
        //          * Si es la primera jugada en la partida también solicita la coordenada del
        //          * tablero a jugar
        //          */
        //         String[] jugada;
        //         if (esPrimerMovimiento) {
        //             jugada = solicitarJugada(null); // Solicita coordenada y mini-coordenada
        //         } else {
        //             jugada = solicitarJugada(coordenadaTablero); // Solicita solo mini-coordenada
        //         }
        //         // Manejo de jugada de abandono
        //         if (jugada[0].equals("A")) {
        //             System.out.println(jugadorActual.getAlias() + " ha decidido abandonar la partida.");
        //             partida.abandonarPartida();
        //             guardarPartida(partida);
        //             return;
        //         }
        //         // Manejo de jugada mágica
        //         if (jugada[0].equalsIgnoreCase("M")) {
        //             if (jugadorActual.isJugadaMagicaDisponible()) {
        //                 partida.jugadaMagica(jugadorActual, jugada[1]);
        //                 System.out.println(jugadorActual.getAlias() + " ha usado su jugada mágica en " + jugada[1]);
        //                 jugadaValida = true;
        //             } else {
        //                 System.out.println("Error: " + jugadorActual.getAlias()
        //                         + " ya ha usado su jugada mágica en esta partida.");
        //             }
        //         } else {
        //             if (esPrimerMovimiento) {
        //                 jugadaValida = partida.registrarJugada(jugadorActual, jugada[0], jugada[1]);
        //                 if (jugadaValida) {
        //                     coordenadaTablero = jugada[1];
        //                     esPrimerMovimiento = false;
        //                 }
        //             } else {
        //                 jugadaValida = partida.registrarJugada(jugadorActual, coordenadaTablero, jugada[0]);
        //                 if (jugadaValida) {
        //                     coordenadaTablero = jugada[0];
        //                 }
        //             }

        //             if (!jugadaValida) {
        //                 System.out.println("Jugada inválida. Intente de nuevo.");
        //             }
        //         }
        //     }

        //     System.out.println("Estado actual del tablero:");
        //     System.out.println(partida.getTablero().toString());

        //     partida.verificarGanador();
        // }

        // // Mostrar resultado final
        // String resultado = partida.getResultado();
        // if (resultado.equals("Empate")) {
        //     System.out.println("La partida ha terminado en empate.");
        // } else {
        //     Jugador ganador;
        //     if (resultado.equals("X")) {
        //         ganador = jugador1;
        //     } else {
        //         ganador = jugador2;
        //     }
        //     System.out.println("El ganador es: " + ganador.getAlias() + " (" + resultado + ")");
        // }
        
        
    }

    // partidas
    /** Guarda una partida finalizada en el historial */
    public static void guardarPartida(Partida partida) {
        historialPartidas.add(partida);
    }

    /** Carga una partida del historial según el ID */
    public static Partida cargarPartida(int id) {
        // Retorna una partida del historial
        return null;
    }

    /** verificar duplicado de alias */
    private static boolean aliasUnico(Jugador nuevoJugador) {
        boolean esUnico = true;
        for (Jugador jugador : jugadores) {
            if (jugador.equals(nuevoJugador)) {
                esUnico = false;
            }
        }

        return esUnico;
    }

    public static String[][] calcularRanking() {
        // Obtener la lista de jugadores
        ArrayList<Jugador> jugadores = Sistema.getJugadores();

        // Crear la matriz: Cada fila tiene 2 columnas [alias, victorias]
        String[][] ranking = new String[jugadores.size()][2];

        // Inicializar la matriz con los alias de los jugadores y 0 victorias
        for (int i = 0; i < jugadores.size(); i++) {
            ranking[i][0] = jugadores.get(i).getAlias();
            ranking[i][1] = "0"; // Inicializamos con 0 victorias
        }

        // Obtener el historial de partidas
        ArrayList<Partida> historial = Sistema.getHistorialPartidas();

        // Contar cuantas veces cada jugador gano
        for (Partida partida : historial) {
            Jugador ganador = partida.getGanador();
            if (ganador != null) {
                // Encontrar el alias del ganador en la matriz y aumentar el contador
                for (int i = 0; i < ranking.length; i++) {
                    if (ranking[i][0].equals(ganador.getAlias())) {
                        // Convertir la cantidad de victorias a entero, incrementar y volver a String
                        int victorias = Integer.parseInt(ranking[i][1]);
                        ranking[i][1] = Integer.toString(victorias + 1);
                    }
                }
            }
        }

        // Ordenar la matriz por la columna de victorias (de mayor a menor)
        Arrays.sort(ranking, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])); // Ordenar por victorias
                                                                                        // (columna 1)
            }
        });

        return ranking;
    }

}