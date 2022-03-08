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
public class ObjetoLegendarioFactory implements ObjetoFactory{

    /**
     *Crea un consumible legendario aleatorio
     * @return el consumible creado
     */
    @Override
    public Consumible crearConsumible() {
        ConsumibleLegendario consumible = null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(2);
        switch (aleatorio) {
            case 0:
                consumible = new ConsumibleLegendario(7, "poción de vida gigante", "salud", 80);
                break;

            case 1:
                consumible = new ConsumibleLegendario(8, "poción de maná gigante", "mana", 50);
                break;
     

        }
        return consumible;
    }

    /**
     *Crea un equipamiento legendario aleatorio
     * @return el equipamiento creado
     */
    @Override
    public Equipamiento crearEquipable() {
         EquipamientoLegendario equipamiento = null;
        Random rand = new Random();
        int aleatorio = rand.nextInt(8);
        switch (aleatorio) {
            case 0:
                equipamiento = new EquipamientoLegendario(14, "casco alado");
                equipamiento.anadirAtributo("defensa", 5);
                equipamiento.anadirAtributo("velocidad", 8);
                break;

            case 1:
                equipamiento = new EquipamientoLegendario(15, "caparazón de lapislazuli");
                equipamiento.anadirAtributo("defensa", 7);
                equipamiento.anadirAtributo("resistencia", 20);
                break;
            case 2:
                equipamiento = new EquipamientoLegendario(16, "calzas de pies de tigres");
                equipamiento.anadirAtributo("evasion", 15);
                equipamiento.anadirAtributo("resistencia", 10);
                break;
            case 3:
                equipamiento = new EquipamientoLegendario(17, "báculo de la desdicha");
                equipamiento.anadirAtributo("dano", 22);
                equipamiento.anadirAtributo("resistencia", 10);
                equipamiento.anadirAtributo("defensa",-6);
                break;
            case 4:
                equipamiento = new EquipamientoLegendario(18, "hoja maldita");
                equipamiento.anadirAtributo("dano", 20);
                equipamiento.anadirAtributo("vida", -15);
                break;
            case 5:
                equipamiento = new EquipamientoLegendario(19, "aguja de los lamentos");
                equipamiento.anadirAtributo("mana", 100);
                equipamiento.anadirAtributo("evasion",15);
                equipamiento.anadirAtributo("dano", -5);
                break;
            
        }
        return equipamiento;
    
 
    
    
}
}
