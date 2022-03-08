/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Personaje.Personaje;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Sangrado extends Perjuicios {

    /**
     *Constructor
     * @param duracion duración del sangrado
     */
    public Sangrado(int duracion) {
        super(duracion);
        this.dano = 4;

    }

    /**
     *
     * @return
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     *
     * @param duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     *
     * @return
     */
    public int getDano() {
        return dano;
    }

    /**
     *
     * @param dano
     */
    public void setDano(int dano) {
        this.dano = dano;
    }

    /**
     *Efectua el daño del sangrado en el objetivo
     * @param personaje personaje sobre el que actua el sangrado
     * @return log del efecto del sangrado
     */
    public String aplicarPerjuicio(Personaje personaje) {
        String frase = "";
        personaje.setSalud(personaje.getSalud() - this.dano);
        frase = personaje.getNombre() + " sufrió " + this.dano + " puntos de daño por sangrado."+ "\n";
        this.duracion--;

        this.dano = (int) (this.dano * 1.5);

        if (duracion == 0) {
            personaje.setSangrado(null);
        }
        return frase;
    }

}
