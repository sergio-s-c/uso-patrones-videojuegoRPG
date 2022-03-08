/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IteratorConsumible;

import Objetos.Consumible;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class IteradorConcreto implements Iterador {

    private AgregadoConcreto agregado;
    private  int indice;

    /**
     *cosntructor
     * @param agregado el Agregado concreto correspondiente
     */
    public IteradorConcreto(AgregadoConcreto agregado) {
        this.agregado = agregado;
        this.indice = 0;
    }

    /**
     *Devuelve el elemento actual
     * @return consumible actual
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Consumible elementoActual()throws IndexOutOfBoundsException {
       Consumible objeto = null;
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
     *Apunta al primer consumible de la lista
     * @return el primer consumbile
     */
    @Override
    public Consumible primero() {
       Consumible objeto = null;
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
    public Consumible siguiente() {
        Consumible objeto = null;
        if (indice < agregado.getElementos().size()) {
            objeto = agregado.getElementos().get(indice);
            indice++;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return objeto;
    }

}
