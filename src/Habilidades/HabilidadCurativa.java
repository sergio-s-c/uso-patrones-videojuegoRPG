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
public class HabilidadCurativa extends Decorator {

    private int curacion;

    /**
     *Constructor
     * @param habilidad habilidad de la que extiende
     * @param curacion cuanto cura la habilidad
     */
    public HabilidadCurativa(IHabilidades habilidad, int curacion) {
        super(habilidad);
        this.curacion = curacion;

    }

    /**
     *
     * @return
     */
    public int getCuracion() {
        return curacion;
    }

    /**
     *
     * @param curacion
     */
    public void setCuracion(int curacion) {
        this.curacion = curacion;
    }

    /**
     *Cura al objetivo con la habiliadad
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivo el personaje al que va a curar
     * @return log del efecto de la habilidad
     */
    public String modificarObjetivo(int dano, Personaje objetivo) {
        int curacionFinal;
        Random rand = new Random();
        String frase = "";

        curacionFinal = this.curacion + rand.nextInt(11) - 5;
        if (curacionFinal < 1) {
            curacionFinal = 1;
        }
        objetivo.setSalud(objetivo.getSalud() + curacionFinal);
        frase = " sobre " + objetivo.getNombre() + " y le curó " + curacionFinal + " puntos de salud";

        return frase;
    }

}
