/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IteratorConsumible;

import Objetos.Consumible;
import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface Iterador {

    /**
     *Devuelve el elemento actual
     * @return consumible actual
     */
    public Consumible elementoActual();

    /**
     *Comprueva si hay más elementos en la lista
     * @return true si hay más elementos
     */
    public boolean hayMas();

    /**
     *Apunta al primer consumible de la lista
     * @return el primer consumbile
     */
    public Consumible primero();

    /**
     *Devuelve el siguiente consumible de la lista
     * @return el siguiente consumible
     */
    public Consumible siguiente();
  
    
}
