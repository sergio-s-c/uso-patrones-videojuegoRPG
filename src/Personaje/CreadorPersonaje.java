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
public class CreadorPersonaje {
    private AbstractPersonajeBuilder personajeBuilder;
    
    /**
     *Establece el constructor del personaje
     * @param pb El constructor del personaje
     */
    public void setPersonajeBuilder(AbstractPersonajeBuilder pb){
        personajeBuilder=pb;
    }

    /**
     *
     * @return
     */
    public Personaje getPersonaje(){
        return personajeBuilder.getPersonaje();
        
    }

    /**
     *proceso de creación de un nuevo personaje
     */
    public void disenarPersonaje(){
        personajeBuilder.ponerClase();
        personajeBuilder.crearAtributos();
        personajeBuilder.crearHabilidades();
    }
}
