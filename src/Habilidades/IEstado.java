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
public interface IEstado {

    /**
     *
     * @return
     */
    public String getAtributo();

    /**
     *
     * @param atributo
     */
    public void setAtributo(String atributo);

    /**
     *
     * @return
     */
    public int getDuracion();

    /**
     *
     * @param duracion
     */
    public void setDuracion(int duracion);

    /**
     *
     * @return
     */
    public int getTamano();

    /**
     *
     * @param tamano
     */
    public void setTamano(int tamano);
}
