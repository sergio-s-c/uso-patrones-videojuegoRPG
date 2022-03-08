/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.util.Random;
import Habilidades.HabilidadGeneral;
import Habilidades.IHabilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class EstrategiaCaudilloDuendeAgresiva implements Estrategia {
   
    /**
     *selecciona una habilidad del personaje
     * @param personaje el personaje que va a elegir la habilidad
     * @return la habilidad seleccionada
     */
    public IHabilidades seleccionarAtaque(Personaje personaje) {
        Random rand = new Random();
        IHabilidades habilidad = null;
        int aleatorio = rand.nextInt(100);
        if (aleatorio < 80) {
            habilidad = personaje.getHabilidades().get(1);
        } else {
            habilidad = personaje.getHabilidades().get(0);
        }
        return habilidad;
    }
    
}
