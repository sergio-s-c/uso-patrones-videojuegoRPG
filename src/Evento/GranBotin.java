/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Objetos.Consumible;
import Objetos.Equipamiento;
import Objetos.ObjetoComunFactory;
import Objetos.ObjetoLegendarioFactory;
import Objetos.ObjetoRaroFactory;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author  Sergio
 */
public class GranBotin implements Botin{

  private ObjetoLegendarioFactory factoriaLegendario;
   private  ObjetoRaroFactory factoriaRaro;
   private  Random rand;

    /**
     *Constructor de GranBotin
     */
    public GranBotin() {
        factoriaLegendario = new ObjetoLegendarioFactory();
        factoriaRaro = new ObjetoRaroFactory();
        rand = new Random();
    }

    /**
     *Genera consumibles y devuelve una lista con los consumibles generados
     * @return lista de consumibles
     */
    public ArrayList<Consumible> GenerarConsumibles() {
        ArrayList<Consumible> consumibles = new ArrayList<Consumible>();
        int rareza;
        int numObjetos = rand.nextInt(5) + 1;
        for (int i = 0; i < numObjetos; i++) {
            rareza = rand.nextInt(10);
            if (rareza < 8) {
                consumibles.add(factoriaRaro.crearConsumible());
            } else {
                consumibles.add(factoriaLegendario.crearConsumible());
            }

        }
        return consumibles;

    }

    /**
     *Genera equipamiento y devuelve una lista con el equipamiento generados
     * @return lista de equipamiento
     */
    public ArrayList<Equipamiento> GenerarEquipamiento() {
        ArrayList<Equipamiento> equipamiento = new ArrayList<Equipamiento>();
        int rareza;
        int numObjetos = rand.nextInt(4) + 1;
        for (int i = 0; i < numObjetos; i++) {
            rareza = rand.nextInt(10);
            if (rareza < 9) {
                equipamiento.add(factoriaRaro.crearEquipable());
            } else {
                equipamiento.add(factoriaLegendario.crearEquipable());
            }

        }
        return equipamiento;

    }
}
