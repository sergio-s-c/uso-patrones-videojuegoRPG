/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Personaje.Personaje;
import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class HabilidadPerjuicios extends Decorator {

   private Perjuicios perjuicios;

    /**
     *Constructor 
     * @param habilidad habilidad de la que extiende
     * @param perjuicios perjuicio que provoca
     */
    public HabilidadPerjuicios(IHabilidades habilidad, Perjuicios perjuicios) {
        super(habilidad);
        this.perjuicios = perjuicios;

    }

    /**
     *
     * @return
     */
    public Perjuicios getPerjuicios() {
        return perjuicios;
    }

    /**
     *
     * @param perjuicios
     */
    public void setPerjuicios(Perjuicios perjuicios) {
        this.perjuicios = perjuicios;
    }
    
    /**
     *Daña al objetivo con la habiliadad y le aplica perjuicio
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivo  el personaje al que va a golpear
     * @return log del efecto de la habilidad
     */
    public String modificarObjetivo(int dano,Personaje objetivo){
         int danoFinal;
        Random rand = new Random();
        String frase = "";
         
         if (rand.nextInt(100) > (4 + objetivo.getEvasion() * 0.7)) {

            danoFinal = dano + this.dano - objetivo.getDefensa() + rand.nextInt(11) - 5;
            if (danoFinal < 1) {
                danoFinal = 1;
            }
            objetivo.setSalud(objetivo.getSalud() - danoFinal);
            frase =  " sobre " + objetivo.getNombre() + " y le causó " + danoFinal + " puntos de daño";
            if (rand.nextInt(100) > objetivo.getResistencia()) {
                if (this.perjuicios instanceof Sangrado) {
                    objetivo.setSangrado((Sangrado) perjuicios);
                    frase += " y le aplicó sangrado";
                } else if (this.perjuicios instanceof Envenenado) {
                    objetivo.setEnvenenado((Envenenado) perjuicios);
                    frase += " y le aplicó envenenamiento";
                }

            }

        }
         else{
             frase=" falló la habilidad";
         }
         return frase;
     }


}
