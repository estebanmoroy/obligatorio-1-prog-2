/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 * Representa a un jugador en el juego Gran Tateti.
 * 
 * @author Esteban Moroy 338885, Facundo Martinez 342426
 */
public class Jugador {
    private final String nombre;
    private int edad;
    private final String alias;
    private String caracter;
    private boolean jugadaMagicaDisponible;

    /**
     * Constructor para crear un nuevo jugador.
     * 
     * @param nombre                 Nombre del jugador
     * @param edad                   Edad del jugador
     * @param alias                  Alias único del jugador
     * @param caracter               Carácter que representa al jugador en el
     *                               tablero (X u O)
     * @param jugadaMagicaDisponible Indica si el jugador puede usar la jugada
     *                               mágica
     */
    public Jugador(String nombre, int edad, String alias, String caracter, boolean jugadaMagicaDisponible) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.caracter = caracter;
        this.jugadaMagicaDisponible = jugadaMagicaDisponible;
    }

    /*
     * Getters y Setters
     * No se generan setters para los atributos Nombre y Alias ya que estos no
     * deberían cambiar luego de creado el jugador
     */

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return alias;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public boolean isJugadaMagicaDisponible() {
        return jugadaMagicaDisponible;
    }

    public void setJugadaMagicaDisponible(boolean jugadaMagicaDisponible) {
        this.jugadaMagicaDisponible = jugadaMagicaDisponible;
    }

    /**
     * Compara si el jugador actual es igual a otro jugador basándose en el alias.
     * 
     * @param obj Objeto a comparar
     * @return true si los alias son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        boolean retorno = false;
        if (obj instanceof Jugador) {
            Jugador jugador = (Jugador) obj;
            // Retorna true si ambos jugadores tienen el mismo alias
            retorno = this.alias.equals(jugador.getAlias());
        }
        return retorno;
    }

    /**
     * Override del método toString
     * 
     * @return String con nombre, edad y alias del jugador
     */
    @Override
    public String toString() {
        return "Nombre: " + this.getNombre() + ", Edad: " + this.getEdad() +
                ", Alias: " + this.getAlias();
    }
}
