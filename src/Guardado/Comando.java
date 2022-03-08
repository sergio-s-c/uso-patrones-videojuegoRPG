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
public interface Comando {

    /**
     *
     * @param partida El objeto partida sobre el que se va a ejecutar el comando
     */
    public void setPartida(Partida partida);

    /**
     *
     * @return
     */
    public Partida getPartida();

    /**
     *Ejectua la acción correspondiente al comando
     */
    public void ejecutar();
    
}
