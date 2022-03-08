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
public class AgregadoConcreto implements Agregado {

    private ArrayList<Consumible> elementos;

    /**
     *Constructor
     * @param elementos lista de consumibles
     */
    public AgregadoConcreto(ArrayList<Consumible> elementos) {
        this.elementos = elementos;
    }

    /**
     *
     * @return
     */
    public ArrayList<Consumible> getElementos() {
        return elementos;
    }

    /**
     *
     * @param elementos
     */
    public void setElementos(ArrayList<Consumible> elementos) {
        this.elementos = elementos;
    }

    /**
     *Crea un iterador concreto
     * @return el iterador
     */
    public IteradorConcreto crearIterador() {
        IteradorConcreto iterador;
        iterador = new IteradorConcreto(this);
        return iterador;
    }

}
