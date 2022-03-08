/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Conserje {
    private ArrayList<Memento> mementos=new ArrayList<>();

    /**
     *
     * @param memento
     */
    public void guardarMememento(Memento memento){
        mementos.add(memento);
    }

    /**
     *Devuelve el último memento
     * @return
     */
    public Memento obtenerMemento(){
        if(mementos.size()>0){
            int indice=mementos.size()-1;
            Memento mem=mementos.get(indice);
            mementos.remove(indice);
            return mem;
        }
        else{
            return null;
        }
    }

    /**
     *
     * @return
     */
    public int getNumMementos() {
        return this.mementos.size();
    }
    
}
