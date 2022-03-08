/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Ventanas.Partida;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class ComandoGuardarPartida implements ComandoDeshacer {
    private Partida partida;
    private Conserje conserje=new Conserje();
    
    /**
     *
     * @return
     */
    public Partida getPartida(){
        return this.partida;
    }

    /**
     *
     * @param partida
     */
    public void setPartida(Partida partida){
        this.partida=partida;
    }
    
    /**
     *
     * @return
     */
    public Conserje getConserje(){
        return conserje;
    }

    /**
     *Guarda el estado de la partida actual
     */
    public void ejecutar(){
        Originador originador=new Originador();
        originador.setPartida(partida);
        try {
            conserje.guardarMememento(originador.crearGuardado());
        } catch (IOException ex) {
            
        }
        
        
    }
    
    /**
     *Elimina el último guardao y crea un nuevo guardado
     * @throws IOException
     */
    public void deshacer() throws IOException{
        Memento memento=conserje.obtenerMemento();
        if(memento!= null){
            Originador originador=new Originador();
            originador.setPartida(partida);
            conserje.guardarMememento(originador.crearGuardado());
            partida= new Partida(memento.getPartida());
            
        }
    }
}
