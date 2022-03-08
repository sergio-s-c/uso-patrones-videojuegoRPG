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
public class ObjetoRaroFactory implements ObjetoFactory {

    /**
     *Crea un consumible común aleatorio
     * @return el consumible creado
     */
    @Override
    public Consumible crearConsumible() {
        ConsumibleRaro consumible = null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(4);
        switch (aleatorio) {
            case 0:
                consumible = new ConsumibleRaro(3, "poción de vida grande", "salud", 35);
                break;

            case 1:
                consumible = new ConsumibleRaro(4, "poción de maná grande", "mana", 20);
                break;
            case 2:
                consumible = new ConsumibleRaro(5, "vendas", "sangrado", 15);
                break;
            case 3:
                consumible = new ConsumibleRaro(6, "antídoto", "veneno", 15);
                break;

        }
        return consumible;

    }

    /**
     *Crea un equipamiento raro aleatorio
     * @return el equipamiento creado
     */
    @Override
    public Equipamiento crearEquipable() {
        EquipamientoRaro equipamiento = null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(8);
        switch (aleatorio) {
            case 0:
                equipamiento = new EquipamientoRaro(6, "capucha de cuero");
                equipamiento.anadirAtributo("evasion", 4);
                equipamiento.anadirAtributo("mana", 15);
                break;

            case 1:
                equipamiento = new EquipamientoRaro(7, "coraza de acero");
                equipamiento.anadirAtributo("defensa", 3);
                equipamiento.anadirAtributo("vida", 20);
                break;
            case 2:
                equipamiento = new EquipamientoRaro(8, "túnica de hechicero");
                equipamiento.anadirAtributo("mana", 20);
                equipamiento.anadirAtributo("vida", 10);
                break;
            case 3:
                equipamiento = new EquipamientoRaro(9, "túnica de ladrón");
                equipamiento.anadirAtributo("velocidad", 2);
                equipamiento.anadirAtributo("vida", 15);
                break;
            case 4:
                equipamiento = new EquipamientoRaro(10, "pantalones de acero");
                equipamiento.anadirAtributo("resistencia", 5);
                equipamiento.anadirAtributo("vida", 15);
                break;
            case 5:
                equipamiento = new EquipamientoRaro(11, "musleras mágicas");
                equipamiento.anadirAtributo("mana", 10);
                equipamiento.anadirAtributo("velocidad", 2);
                break;
            case 6:
                equipamiento = new EquipamientoRaro(12, "espada y escudo");
                equipamiento.anadirAtributo("dano", 5);
                equipamiento.anadirAtributo("defensa", 4);
                break;
            case 7:
                equipamiento = new EquipamientoRaro(13, "creador de reyes");
                equipamiento.anadirAtributo("dano", 12);
                break;

        }
        return equipamiento;
    }

}
