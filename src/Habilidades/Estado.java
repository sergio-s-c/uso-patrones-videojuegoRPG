/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Estado implements IEstado{
   private String atributo;
   private  int duracion;
   private  int tamano;//tamño del estado, +1 o +2 para bufos, -1 o -2 para debufos

    /**
     *
     * @param atributo Atributo al que afecta 
     * @param duracion duración del estado
     * @param tamano cuanto afecta el estado, puede ser: -2,-1,1 o 2
     */
    public Estado(String atributo, int duracion,int tamano) {
        this.atributo = atributo;
        this.duracion = duracion;
        this.tamano=tamano;
    }

    /**
     *
     * @return
     */
    public int getTamano() {
        return tamano;
    }
    
    /**
     *
     * @param tamano
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     *
     * @return
     */
    public String getAtributo() {
        return atributo;
    }

    /**
     *
     * @param atributo
     */
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    /**
     *
     * @return
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     *
     * @param duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    
}
