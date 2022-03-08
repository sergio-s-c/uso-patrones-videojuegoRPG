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
public class ComandoCambiarUsuario {
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
     * @return
     */
    public Conserje getConserje(){
        return conserje;
    }

    /**
     *
     * @param partida
     */
    public void setPartida(Partida partida){
        this.partida=partida;
    }
    
    /**
     *Crea un guardado del nombre de usuario
     * @param nombre
     */
    public void ejecutar(String nombre){
         Originador originador=new Originador();
         partida.setNombreUsusario(nombre);
        originador.setPartida(partida);
        
        try {
            this.conserje.guardarMememento(originador.crearGuardado());
        } catch (IOException ex) {
            
        }
        
    }
    
    /**
     *Borra el anterior nombre guardado y guarda el actual
     * @throws IOException
     */
    public void deshacer() throws IOException{
        Memento memento=conserje.obtenerMemento();
        if(memento!= null){
            Originador originador=new Originador();
            originador.setPartida(partida);
            conserje.guardarMememento(originador.crearGuardado());
            partida.setNombreUsusario(memento.getPartida().getNombreUsusario());
    }
    
    
}
}
