/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Personaje.Personaje;
import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface IHabilidades {

    /**
     *
     * @return
     */
    public String getObjetivo();

    /**
     *
     * @return
     */
    public String getNombre();

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre);

    /**
     *
     * @param objetivo
     */
    public void setObjetivo(String objetivo);

    /**
     *
     * @return
     */
    public int getDano();

    /**
     *
     * @param dano
     */
    public void setDano(int dano);

    /**
     *
     * @return
     */
    public int getMana();

    /**
     *
     * @param mana
     */
    public void setMana(int mana);

    /**
     *
     * @return
     */
    public boolean isAturdimiento();

    /**
     *
     * @param aturdimiento
     */
    public void setAturdimiento(boolean aturdimiento);

    /**
     *
     * @param dano
     * @param objetivo
     * @return
     */
    public String ejecutarAccion(int dano,ArrayList<Personaje> objetivo);

}
