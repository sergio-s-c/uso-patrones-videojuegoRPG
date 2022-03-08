/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class ObjetoComunFactory implements ObjetoFactory {

    /**
     *Crea un consumible común aleatorio
     * @return el consumible creado
     */
    @Override
    public Consumible crearConsumible() {
        ConsumibleComun consumible = null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(2);
        switch (aleatorio) {
            case 0:
                consumible = new ConsumibleComun(1, "poción de vida", "salud", 20);

            case 1:
                consumible = new ConsumibleComun(2, "poción de maná", "mana", 10);

        }
        return consumible;
    }

    /**
     *Crea un equipamiento común aleatorio
     * @return el equipamiento creado
     */
    @Override
    public Equipamiento crearEquipable() {
        EquipamientoComun equipamiento= null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(5);
        switch (aleatorio) {
            case 0:
                equipamiento = new EquipamientoComun(1, "boina de lana");
                equipamiento.anadirAtributo("evasion", 5);
                break;

            case 1:
                equipamiento = new EquipamientoComun(2, "yelmo de acero");
                equipamiento.anadirAtributo("defensa", 4);
                break;
            case 2:
                 equipamiento = new EquipamientoComun(3, "pantalones de lana");
                equipamiento.anadirAtributo("velocidad", 3);
                break;
            case 3:
                 equipamiento = new EquipamientoComun(4, "bastón relámpago");
                equipamiento.anadirAtributo("mana", 30);
                break;
            case 4:
                 equipamiento = new EquipamientoComun(5, "puñales retorcidos");
                equipamiento.anadirAtributo("dano", 5);
                break;
                
            
        }
        return equipamiento;

    }

}
