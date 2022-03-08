/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface ObjetoFactory {

    /**
     *Crea un consumible aleatorio
     * @return el consumible creado
     */
    public Consumible crearConsumible();

    /**
     *Crea un equipamiento aleatorio
     * @return el equipamiento creado
     */
    public Equipamiento crearEquipable();
    
}
