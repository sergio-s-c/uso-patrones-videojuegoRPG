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
public class HabilidadEstado extends Decorator {

   private IEstado estado;

    /**
     *Constructor
     * @param habilidad habilidad de la que extiende
     * @param estado el estado que provoca
     */
    public HabilidadEstado(IHabilidades habilidad, IEstado estado) {
        super(habilidad);
        this.estado = estado;

    }

    /**
     *
     * @return
     */
    public IEstado getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(IEstado estado) {
        this.estado = estado;
    }
    
    /**
     *Daña al objetivo con la habiliadad y le aplica estado alterado
     * @param dano el poder de ataque del usuario de la habilidad
     * @param objetivo el personaje al que va a golpear
     * @return log del efecto de la habilidad
     */
    public String modificarObjetivo(int dano,Personaje objetivo){
        int danoFinal;
        Random rand = new Random();
        String frase = "";

            if (dano < 1) {
                objetivo.anadirEstado(this.estado);
                frase = " usó la habildiad " + this.nombre + " sobre " + objetivo.getNombre() + " y le causó un estado alterado durante " + estado.getDuracion()+" turnos.";
            } else {
                if (rand.nextInt(100) > (4 + objetivo.getEvasion() * 0.7)) {

                    danoFinal = dano + this.dano - objetivo.getDefensa() + rand.nextInt(11) - 5;
                    if (danoFinal < 1) {
                        danoFinal = 1;
                    }
                    objetivo.setSalud(objetivo.getSalud() - danoFinal);
                    frase =  " sobre " + objetivo.getNombre() + " y le causó " + danoFinal + " puntos de daño";
                    objetivo.anadirEstado(this.estado);
                    frase += "y le causó un estado alterado durante " + estado.getDuracion();

                }

            }
            return frase;
        
    }

  

}
