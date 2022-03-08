/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import java.io.IOException;
import Ventanas.Partida;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Originador {

    private Partida partida;
    private Memento memento;

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

    /**
     *
     * @param memento
     */
    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    /**
     *Crea un guardado del estado de la partida
     * @return el memento que contiene el estado de la partida
     * @throws IOException
     */
    public Memento crearGuardado() throws IOException {
        return new Memento(new Partida(partida));

    }

}
