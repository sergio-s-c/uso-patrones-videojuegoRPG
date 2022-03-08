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
public abstract class Decorator implements IHabilidades {

    /**
     *El objetivo de la habilidad, puede ser individual, equipo o múltiple
     */
    protected String objetivo;

    /**
     *El  nombre
     */
    protected String nombre;

    /**
     *El daño que hace
     */
    protected int dano;

    /**
     *El mana que cuesta
     */
    protected int mana;

    /**
     *Si aturde
     */
    protected boolean aturdimiento;

    /**
     *Interfaz 
     */
    protected IHabilidades habilidad;

    /**
     *
     * @param habilidad
     */
    public Decorator(IHabilidades habilidad) {
        this.habilidad = habilidad;

    }

    /**
     *
     * @return
     */
    public IHabilidades getHabilidad() {
        return habilidad;
    }

    /**
     *
     * @param habilidad
     */
    public void setHabilidad(IHabilidades habilidad) {
        this.habilidad = habilidad;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return habilidad.getNombre();
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        habilidad.setNombre(nombre);
    }

    /**
     *
     * @return
     */
    public String getObjetivo() {
        return habilidad.getObjetivo();
    }

    /**
     *
     * @param objetivo
     */
    public void setObjetivo(String objetivo) {
        habilidad.setObjetivo(objetivo);
    }

    /**
     *
     * @return
     */
    public int getMana() {
        return habilidad.getMana();
    }

    /**
     *
     * @param mana
     */
    public void setMana(int mana) {
        habilidad.setMana(mana);
    }

    /**
     *
     * @return
     */
    public boolean isAturdimiento() {
        return habilidad.isAturdimiento();
    }

    /**
     *
     * @param aturdimiento
     */
    public void setAturdimiento(boolean aturdimiento) {
        habilidad.setAturdimiento(aturdimiento);
    }

    /**
     *
     * @return
     */
    public int getDano() {
        return habilidad.getDano();
    }

    /**
     *
     * @param dano
     */
    public void setDano(int dano) {
        habilidad.setDano(dano);
    }

    /**
     *Establece el objetivo a atacar el tipo de objetivo de la habilidad
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivos el personaje al que va a golpear
     * @return log del efecto de la habilidad
     */
    public String ejecutarAccion(int dano, ArrayList<Personaje> objetivos) {

        String frase = "";

        if (this.objetivo == "multiple" || this.objetivo == "equipo") {

            for (int i = 0; i < objetivos.size(); i++) {
                frase = modificarObjetivo(dano, objetivos.get(i));

            }
        } else {
            frase = modificarObjetivo(dano, objetivos.get(0));
        }

        return " usó la habilidad " + habilidad.getNombre() + frase;
    }

    /**
     * Ataca al objetivo con la habiliadad
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivo el personaje al que va a golpear
     * @return log del efecto de la habilidad
     */
    public abstract String modificarObjetivo(int dano, Personaje objetivo);

}
