/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import Ventanas.Partida;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Memento {

    private Partida partida = null;

    /**
     *La partida que va a guardar el memento
     * @param partida
     */
    public Memento(Partida partida) {
        this.partida = partida;
    }

    /**
     *
     * @return
     */
    public Partida getPartida() {
        return partida;
    }

    /**
     *
     * @param partida
     */
    public void setPartida(Partida partida) {
        this.partida = partida;
    }

}
