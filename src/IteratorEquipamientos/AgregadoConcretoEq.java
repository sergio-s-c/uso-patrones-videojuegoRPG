/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IteratorEquipamientos;

import IteratorConsumible.*;
import Objetos.Consumible;
import Objetos.Equipamiento;
import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class AgregadoConcretoEq implements AgregadoEq {

    private ArrayList<Equipamiento> elementos;

    /**
     *Constructor
     * @param elementos lista de equipamientos
     */
    public AgregadoConcretoEq(ArrayList<Equipamiento> elementos) {
        this.elementos = elementos;
    }

    /**
     *
     * @return
     */
    public ArrayList<Equipamiento> getElementos() {
        return elementos;
    }

    /**
     *
     * @param elementos
     */
    public void setElementos(ArrayList<Equipamiento> elementos) {
        this.elementos = elementos;
    }

    /**
     *Crea un iterador concreto
     * @return el iterador
     */
    public IteradorConcretoEq crearIterador() {
        IteradorConcretoEq iterador;
        iterador = new IteradorConcretoEq(this);
        return iterador;
    }

}
