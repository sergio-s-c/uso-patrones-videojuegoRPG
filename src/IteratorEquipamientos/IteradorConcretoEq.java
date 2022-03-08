/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IteratorEquipamientos;

import IteratorConsumible.*;
import Objetos.Consumible;
import Objetos.Equipamiento;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class IteradorConcretoEq implements IteradorEq {

    private AgregadoConcretoEq agregado;
    private  int indice;

    /**
     *cosntructor
     * @param agregado el Agregado concreto correspondiente
     */
    public IteradorConcretoEq(AgregadoConcretoEq agregado) {
        this.agregado = agregado;
        this.indice = 0;
    }

    /**
     *Devuelve el elemento actual
     * @return equipamiento actual
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Equipamiento elementoActual()throws IndexOutOfBoundsException {
       Equipamiento objeto = null;
        if (indice < agregado.getElementos().size()) {
            objeto = agregado.getElementos().get(indice);
        } else {
            throw new IndexOutOfBoundsException();
        }
        return objeto;
    }

    /**
     *Comprueva si hay más elementos en la lista
     * @return true si hay más elementos
     */
    @Override
    public boolean hayMas() {
        boolean resultado = true;
        if (agregado.getElementos().isEmpty() || indice == agregado.getElementos().size()) {
            resultado = false;
        }
        return resultado;
    }

    /**
     *Apunta al primer equipamiento de la lista
     * @return el primer equipamiento
     */
    @Override
    public Equipamiento primero() {
       Equipamiento objeto = null;
        if (!agregado.getElementos().isEmpty()) {
            indice = 0;
            objeto = agregado.getElementos().get(0);
        } else {
            throw new IndexOutOfBoundsException();
        }
        return objeto;
    }

    /**
     *Devuelve el siguiente consumible de la lista
     * @return el siguiente consumible
     */
    @Override
    public Equipamiento siguiente() {
        Equipamiento objeto = null;
        if (indice < agregado.getElementos().size()) {
            objeto = agregado.getElementos().get(indice);
            indice++;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return objeto;
    }

}
