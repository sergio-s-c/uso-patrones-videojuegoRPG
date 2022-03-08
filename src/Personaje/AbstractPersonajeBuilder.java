/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public abstract class AbstractPersonajeBuilder {

    /**
     *Personaje que va a crear
     */
    protected Personaje personaje;

    /**
     *
     * @return
     */
    public Personaje getPersonaje() {
        return personaje;
    }

    /**
     *Comienza el proceso de hacer un nuevo personaje
     */
    public void diseñoNuevoPersonaje(){
        personaje=new Personaje();
    }

    /**
     *le pone la clase y nombre al personaje
     */
    public abstract void ponerClase();

    /**
     *le pone los atributos al personaje
     */
    public abstract void crearAtributos();

    /**
     *le pone las habilidades al personaje
     */
    public abstract void crearHabilidades();
    
    
}
