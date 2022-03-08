/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.HashMap;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public abstract class Equipamiento {

    /**
     * el id del equipamiento
     */
    protected int id;

    /**
     * nombre del equipamiento
     */
    protected String nombre;

    /**
     *Los atributos que mejora el equipamiento y la cantidad en la que los mejora
     */
    protected HashMap<String, Integer> atributos;

    /**
     *Constructor
     * @param id
     * @param nombre
     */
    public Equipamiento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.atributos = new HashMap<String, Integer>();
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Integer> getAtributos() {
        return atributos;
    }

    /**
     *
     * @param atributos
     */
    public void setAtributos(HashMap<String, Integer> atributos) {
        this.atributos = atributos;

    }

    /**
     *Añade un atributo al objeto
     * @param atributo el atributo que se le añade:evasion,defensa,velocidad,dano o resistencia
     * @param cantidad cuanto le mejora el atributo
     */
    public void anadirAtributo(String atributo, int cantidad) {
        atributos.put(atributo, cantidad);

    }

}
