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
public abstract class Perjuicios {

    /**
     * la duración del perjuicio
     */
    protected int duracion;

    /**
     *el daño del perjuicio
     */
    protected  int dano;

    /**
     *
     * @param duracion
     */
    public Perjuicios(int duracion) {
        this.duracion = duracion;
        
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
     *Efectua el daño del perjuicio en el objetivo
     * @param personaje personaje sobre el que actua el perjuicio
     * @return log del efecto del perjuicio
     */
    abstract public String aplicarPerjuicio(Personaje personaje);
}
