/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Personaje.Personaje;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public abstract class Consumible {

    /**
     *el id del consumible
     */
    protected int id;

    /**
     *nombre del consumible
     */
    protected String nombre;

    /**
     *el efecto del consumible: salud(recupera vida),mana(recupera mana),sangrado(cura sangrado y recupera vida) o veneno(cura envenenado y recupera vida).
     */
    protected String efecto; 

    /**
     *lo que recupera el consumible
     */
    protected int recuperacion;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public String getEfecto() {
        return efecto;
    }

    /**
     *
     * @return
     */
    public int getRecuperacion() {
        return recuperacion;
    }

    /**
     *Se aplica el efecto del consumible sobre el personaje
     * @param personaje
     */
    public void usar(Personaje personaje) {
        switch (efecto) {
            case "salud":
                personaje.setSalud(personaje.getSalud() + recuperacion);
                break;

            case "mana":
                personaje.setMana(personaje.getMana() + recuperacion);
                break;
            case "sangrado":
                personaje.curarSangrado();
                personaje.setSalud(personaje.getSalud() + recuperacion);
                break;
            case "veneno":
                personaje.curarEnvenamiento();
                personaje.setSalud(personaje.getSalud() + recuperacion);
                break;

        }
    }

}
