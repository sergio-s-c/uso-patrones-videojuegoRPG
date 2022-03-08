/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Evento.Evento;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Mapa {
    private EstadoMapa estado;
    private Evento evento;
    private int eventosRealizados;

    /**
     *Constructor
     */
    public Mapa() {
        this.setEstado(new EstadoInicial());
        this.evento = null;
        this.eventosRealizados = 0;
    }

    /**
     *
     * @return
     */
    public EstadoMapa getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(EstadoMapa estado) {
        this.estado = estado;
        
    }

    /**
     *
     * @return
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     *
     * @param evento
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     *
     * @return
     */
    public int getEventosRealizados() {
        return eventosRealizados;
    }

    /**
     *
     * @param eventosFinalizados
     */
    public void setEventosRealizados(int eventosFinalizados) {
        this.eventosRealizados = eventosFinalizados;
    }
    
 
    
}
