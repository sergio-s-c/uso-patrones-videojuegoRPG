/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Sergio
 */
public class ConsumibleLegendario extends Consumible{

    /**
     *Constructor
     * @param id el id del consumible
     * @param nombre nombre del consumible
     * @param efecto el efecto del consumible: salud(recupera vida),mana(recupera mana),sangrado(cura sangrado y recupera vida) o veneno(cura envenenado y recupera vida).
     * @param recuperacion lo que recupera el consumible
     */
    public ConsumibleLegendario(int id, String nombre, String efecto, int recuperacion) {
        this.id=id;
        this.nombre=nombre;
        this.efecto=efecto;
        this.recuperacion=recuperacion;
    }
    
}
