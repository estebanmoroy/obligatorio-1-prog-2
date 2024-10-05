/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author Esteban Moroy 338885, Facundo Martinez
 */
public class Jugador {

    // Atributos
    private String nombre;
    private int edad;
    private String alias;
    private String caracter;
    private boolean jugadaMagicaDisponible;

    // Constructor
    public Jugador(String nombre, int edad, String alias, String caracter, boolean jugadaMagicaDisponible) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.caracter = caracter;
        this.jugadaMagicaDisponible = jugadaMagicaDisponible;
    }

    // Métodos

    /** Retorna el nombre del jugador */
    public String getNombre() {
        return nombre;
    }

    /** Modifica el nombre del jugador */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Retorna la edad del jugador */
    public int getEdad() {
        return edad;
    }

    /** Modifica la edad del jugador */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** Retorna el alias del jugador */
    public String getAlias() {
        return alias;
    }

    /** Modifica el alias del jugador */
    public void setAlias(String alias) {
        this.alias = alias;
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

    /** Compara si el jugador actual es igual a otro jugador */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jugador) {
            Jugador jugador = (Jugador) obj;
            // Retorna true si ambos jugadores tienen el mismo alias
            return this.alias.equals(jugador.getAlias());
        } else {
            return false;
        }
    }
    //override al toString
    @Override
    public String toString(){
        return "nombre: " + this.getNombre() + ", edad: " + this.getEdad() + 
                ", alias: " + this.getAlias() ;
    }

}
