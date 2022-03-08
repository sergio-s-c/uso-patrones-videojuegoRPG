/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Evento.Evento;
import Personaje.Personaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public abstract class EstadoMapa {

    /**
     *
     */
    protected int numEvento;

    /**
     *
     */
    protected Random rand = new Random();

    /**
     *
     */
    protected String nombre;

    /**
     *
     * Defiene el número de eventos a crear y llama a actualizar cuando corresponde
     * @param mapa el mapa al que afecta el estado
     * @return lista de eventos
     * @throws IOException
     */
    public abstract ArrayList<Evento> crearEventos(Mapa mapa) throws IOException;

    /**
     *Devuelve un evento aleatorio
     * @return el evento generado
     * @throws IOException
     */
    public abstract Evento generarEvento() throws IOException;

    /**
     *Genera un grupo de enemigos aleatorios 
     * @return lista de enemigos
     * @throws IOException
     */
    public abstract ArrayList<Personaje> generarEnemigos() throws IOException;

    /**
     *Genera un grupo de enemigos aleatorios de mayor dificultad
     * @return lista de enemigo
     * @throws IOException
     */
    public abstract ArrayList<Personaje> generarElites() throws IOException;

    /**
     *Determina mediante un random si actualiza el mapa
     * @param mapa el mapa al que hace afecta
     */
    public abstract void probabilidadActualizar(Mapa mapa);

    /**
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     *Constructor
     */
    public EstadoMapa() {
        numEvento = 0;

    }

}
