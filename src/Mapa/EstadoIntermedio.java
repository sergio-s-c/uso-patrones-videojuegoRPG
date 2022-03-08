/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Evento.Cofre;
import Evento.CombateElite;
import Evento.CombateNormal;
import Evento.Evento;
import Evento.ZonaDescanso;
import Evento.ZonaInvisible;
import Personaje.AbstractPersonajeBuilder;
import Personaje.CreadorPersonaje;
import Personaje.DuendeAsesinoBuilder;
import Personaje.DuendeChamanBuilder;
import Personaje.DuendeJabalineroBuilder;
import Personaje.DuendeGuerreroBuilder;
import Personaje.Personaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class EstadoIntermedio extends EstadoMapa{

    /**
     *Constructor
     */
    public EstadoIntermedio() {
     this.nombre="Fase Intermedio";
     this.numEvento=0;
       
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
        if (numEvento > 6) //numero mínimos de eventos inicales realizados para actualizar estado
        {
            probabilidadActualizar(mapa);
        }

        this.numEvento++;

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        for (int i = 0; i < 3; i++) {

            eventos.add(generarEvento());
        }
        return eventos;

    }

    /**
     *Devuelve un evento aleatorio
     * @return el evento generado
     * @throws IOException
     */
    public Evento generarEvento() throws IOException{
        Evento evento=null;
        int numRandom=rand.nextInt(100);
        if(numRandom<45){
           
                evento=new CombateNormal();
            
        }
        else if(numRandom<55){
            evento=new ZonaDescanso();
        }
        else if(numRandom<80){
            evento=new CombateElite();
        }
        else if(numRandom<85){
            evento=new Cofre();
        }
        else{
            evento=new ZonaInvisible();
        }
        
        return evento;
    }
   
    /**
     *Genera un grupo de enemigos aleatorios 
     * @return lista de enemigos
     * @throws IOException
     */
    public ArrayList<Personaje> generarEnemigos() throws IOException {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        int numEnemigos = 2 + rand.nextInt(2);
        Personaje personaje;
        CreadorPersonaje creador = new CreadorPersonaje();
        for (int i = 0; i < numEnemigos; i++) {
            int tipoEnemigo = rand.nextInt(10);
            if (tipoEnemigo < 4) {

                AbstractPersonajeBuilder duendeGuerrero = new DuendeGuerreroBuilder();
                creador.setPersonajeBuilder(duendeGuerrero);

            } else if (tipoEnemigo < 8){
                AbstractPersonajeBuilder duendeArquero = new DuendeJabalineroBuilder();
                creador.setPersonajeBuilder(duendeArquero);
            }
            else{
                 AbstractPersonajeBuilder duendeAsesino = new DuendeAsesinoBuilder();
                creador.setPersonajeBuilder(duendeAsesino );
            }
            creador.disenarPersonaje();
            personaje = creador.getPersonaje();
            personajes.add(personaje);
        }
        return personajes;

    }
    
    /**
     *Genera un grupo de enemigos aleatorios de mayor dificultad
     * @return lista de enemigo
     * @throws IOException
     */
    public ArrayList<Personaje> generarElites() throws IOException{
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        int numEnemigos = 4;
        Personaje personaje;
        CreadorPersonaje creador = new CreadorPersonaje();
        for (int i = 0; i < numEnemigos; i++) {
            int tipoEnemigo = rand.nextInt(10);
            if (tipoEnemigo < 6) {

                 AbstractPersonajeBuilder duendeAsesino = new DuendeAsesinoBuilder();
                creador.setPersonajeBuilder(duendeAsesino );

            } else if(tipoEnemigo < 9) {
                AbstractPersonajeBuilder duendeJabalinero = new DuendeJabalineroBuilder();
                creador.setPersonajeBuilder(duendeJabalinero);
            }
            else{
                AbstractPersonajeBuilder duendeChaman = new DuendeChamanBuilder();
                creador.setPersonajeBuilder(duendeChaman );
            }
            creador.disenarPersonaje();
            personaje = creador.getPersonaje();
            personajes.add(personaje);
        }
        return personajes;
    }
    
    /**
     *Determina mediante un random si actualiza el mapa
     * @param mapa el mapa al que hace afecta
     */
    public void probabilidadActualizar(Mapa mapa) {
        
        if (50 < (numEvento * 5 + rand.nextInt(20))) {
            mapa.setEstado(new EstadoAvanzado());
        }
    }
}
