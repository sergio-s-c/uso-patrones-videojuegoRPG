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
public class Envenenado extends Perjuicios {

    /**
     *
     * @param duracion la duración del envenenado
     */
    public Envenenado(int duracion) {
        super(duracion);
        this.dano = 8;
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
     *Aplica el daño del envenenado
     * @param personaje a quien afecta el envenenado
     * @return log del envenenado
     */
    public String aplicarPerjuicio(Personaje personaje) {

        String frase = "";
        personaje.setSalud(personaje.getSalud() - this.dano);
        frase = personaje.getNombre() + " sufrió " + this.dano + " puntos de daño por envenamiento.";
        this.duracion--;

        this.dano = (int) (this.dano * 0.75);

        if (duracion == 0) {
            personaje.setEnvenenado(null);
        }
        return frase;

    }

}
