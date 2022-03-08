/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Evento.CombateJefe;
import Evento.Evento;
import Personaje.AbstractPersonajeBuilder;
import Personaje.CaudilloDuendeBuilder;
import Personaje.CreadorPersonaje;
import Personaje.DuendeAsesinoBuilder;
import Personaje.DuendeChamanBuilder;
import Personaje.DuendeJabalineroBuilder;
import Personaje.DuendeGuerreroBuilder;
import Personaje.Personaje;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class EstadoFinal extends EstadoMapa {

    /**
     *Constructor
     */
    public EstadoFinal() {
        this.nombre = "Fase Final";

    }

    /**
     *
     * Defiene el número de eventos a crear y llama a actualizar cuando corresponde
     * @param mapa el mapa al que afecta el estado
     * @return lista de eventos
     * @throws IOException
     */
    public ArrayList<Evento> crearEventos(Mapa mapa) throws IOException {
        mapa.setEventosRealizados(mapa.getEventosRealizados() + 1);

        this.numEvento++;

        ArrayList<Evento> eventos = new ArrayList<Evento>();

        eventos.add(generarEvento());

        return eventos;

    }

    /**
     *Genera un grupo de enemigos aleatorios de mayor dificultad
     * @return lista de enemigo
     * @throws IOException
     */
    public ArrayList<Personaje> generarElites() throws IOException { //No diseñada actualmente
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        return personajes;
    }

    /**
     *Genera un grupo de enemigos aleatorios 
     * @return lista de enemigos
     * @throws IOException
     */
    public ArrayList<Personaje> generarEnemigos() throws IOException {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        int numEnemigos = 4;
        Personaje personaje;
        CreadorPersonaje creador = new CreadorPersonaje();
        for (int i = 0; i < numEnemigos; i++) {
            int tipoEnemigo = rand.nextInt(10);
            if (tipoEnemigo < 7) {

                AbstractPersonajeBuilder duendeAsesino = new DuendeAsesinoBuilder();
                creador.setPersonajeBuilder(duendeAsesino);

            } else {
                AbstractPersonajeBuilder duendeChaman = new DuendeChamanBuilder();
                creador.setPersonajeBuilder(duendeChaman);
            }
            creador.disenarPersonaje();
            personaje = creador.getPersonaje();
            personajes.add(personaje);
        }
        AbstractPersonajeBuilder caudilloDuende = new CaudilloDuendeBuilder();
        creador.setPersonajeBuilder(caudilloDuende);
        creador.disenarPersonaje();
        personaje = creador.getPersonaje();
        personajes.add(personaje);
        return personajes;

    }

    /**
     *Devuelve un evento aleatorio
     * @return el evento generado
     * @throws IOException
     */
    public Evento generarEvento() throws IOException {
        Evento evento = new CombateJefe();

        return evento;
    }

    /**
     *Determina mediante un random si actualiza el mapa
     * @param mapa el mapa al que hace afecta
     */
    public void probabilidadActualizar(Mapa mapa) {
        //No contiene nada porque actualmente no hay más zonas
    }

}
