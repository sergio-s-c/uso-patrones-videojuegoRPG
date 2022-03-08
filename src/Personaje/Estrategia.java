/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Habilidades.HabilidadGeneral;
import Habilidades.IHabilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface Estrategia {

    /**
     *selecciona una habilidad del personaje
     * @param personaje el personaje que va a elegir la habilidad
     * @return la habilidad seleccionada
     */
    public IHabilidades seleccionarAtaque(Personaje personaje);
}
