/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import java.awt.image.BufferedImage;

/**
 *
 * @author Sergio
 */
public abstract class Evento {

    /**
     *Contiene la imagen correspondiente al evento
     */
    protected  BufferedImage imagenEvento;

    /**
     *El nombre del evento
     */
    protected   String nombre;

    /**
     *devuelve el atributo imagenEvento
     * @return
     */
    public BufferedImage getImagenEvento() {
        return imagenEvento;
    }

    /**
     *devuelve el atributo nombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

}
