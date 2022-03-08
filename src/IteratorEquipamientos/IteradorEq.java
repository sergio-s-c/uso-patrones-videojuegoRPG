/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IteratorEquipamientos;

import IteratorConsumible.*;
import Objetos.Consumible;
import Objetos.Equipamiento;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public interface IteradorEq {

    /**
     *
     * @return
     */
    public Equipamiento elementoActual();

    /**
     *
     * @return
     */
    public boolean hayMas();

    /**
     *
     * @return
     */
    public Equipamiento primero();

    /**
     *
     * @return
     */
    public Equipamiento siguiente();
  
    
}
