/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Personaje.Personaje;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class HabilidadGeneral implements IHabilidades {

    private String objetivo; //Pueden ser individual(afecta a un rival), ,equipo(afecta a todos los aliados) o múltiple(afecta a todo el equipo rival)
    private String nombre;
    private int dano;
    private int mana;

    private boolean aturdimiento;

    /**
     *Constructor
     * @param dano El daño que hace
     * @param mana El mana que cuesta
     * @param nombre El  nombre
     */
    public HabilidadGeneral(int dano, int mana, String nombre) {
        this.nombre = nombre;
        this.dano = dano;
        this.mana = mana;
        this.objetivo = "individual";
        this.aturdimiento = false;

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
    public String getObjetivo() {
        return objetivo;
    }

    /**
     *
     * @param objetivo
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     *
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     *
     * @return
     */
    public boolean isAturdimiento() {
        return aturdimiento;
    }

    /**
     *
     * @param aturdimiento
     */
    public void setAturdimiento(boolean aturdimiento) {
        this.aturdimiento = aturdimiento;
    }

    /**
     *
     * @return
     */
    public int getDano() {
        return dano;
    }

    /**
     *
     * @param dano
     */
    public void setDano(int dano) {
        this.dano = dano;
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

                frase = modificarObjetivo(dano, objetivos.get(i)) + frase;

            }
        } else {
            frase = modificarObjetivo(dano, objetivos.get(0));

        }
        return frase;
    }

    /**
     *Ataca al objetivo con la habiliadad
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivo objetivo el personaje al que va a golpear
     * @return log del efecto de la habilidad
     */
    public String modificarObjetivo(int dano, Personaje objetivo) {
        int danoFinal;
        Random rand = new Random();
        String frase = "";

        if (rand.nextInt(100) > (4 + objetivo.getEvasion() * 0.7)) {

            danoFinal = dano + this.dano - objetivo.getDefensa() + rand.nextInt(11) - 5;
            if (danoFinal < 1) {
                danoFinal = 1;
            }
            objetivo.setSalud(objetivo.getSalud() - danoFinal);
            frase = " usó la habildiad " + this.nombre + " sobre " + objetivo.getNombre() + " y le causó " + danoFinal + " puntos de daño";
            if (isAturdimiento() && rand.nextInt(100) > objetivo.getResistencia()) {
                objetivo.setAturdido(aturdimiento);
                frase += " y le aturdió";

            }

        }
        return frase;
    }

}
