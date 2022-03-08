/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Objetos.Consumible;
import Objetos.Equipamiento;
import java.util.ArrayList;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface Botin {

    /**
     *Genera consumibles y devuelve una lista con los consumibles generados
     * @return lista de consumibles
     */
    public ArrayList<Consumible> GenerarConsumibles();

    /**
     *Genera equipamiento y devuelve una lista con el equipamiento generados
     * @return lista de equipamiento
     */
    public ArrayList<Equipamiento> GenerarEquipamiento();
}
