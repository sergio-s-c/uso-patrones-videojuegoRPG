/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Objetos.Consumible;
import Objetos.Equipamiento;
import Objetos.ObjetoComunFactory;
import Objetos.ObjetoRaroFactory;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class BotinComun implements Botin {

   private ObjetoComunFactory factoriaComun;
   private ObjetoRaroFactory factoriaRaro;
   private Random rand;

    /**
     *Constructor de BotinComun 
     */
    public BotinComun() {
        factoriaComun = new ObjetoComunFactory();
        factoriaRaro = new ObjetoRaroFactory();
        rand = new Random();
    }

    /**
     *Genera consumibles y devuelve una lista con los consumibles generados
     * @return lista de consumibles
     */
    @Override
    public ArrayList<Consumible> GenerarConsumibles() {
        ArrayList<Consumible> consumibles = new ArrayList<Consumible>();
        int rareza;
        int numObjetos = rand.nextInt(3) + 1;
        for (int i = 0; i < numObjetos; i++) {
            rareza=rand.nextInt(10);
            if(rareza<7){
                consumibles.add(factoriaComun.crearConsumible());
            }
            else{
                consumibles.add(factoriaRaro.crearConsumible());
            }

        }
        return consumibles;

    }

    /**
     *Genera equipamiento y devuelve una lista con el equipamiento generados
     * @return lista de equipamiento
     */
    @Override
    public ArrayList<Equipamiento> GenerarEquipamiento() {
         ArrayList<Equipamiento> equipamiento = new ArrayList<Equipamiento>();
        int rareza;
        int numObjetos = 1+rand.nextInt(1);
        for (int i = 0; i < numObjetos; i++) {
            rareza=rand.nextInt(10);
            if(rareza<8){
                equipamiento.add(factoriaComun.crearEquipable());
            }
            else{
                equipamiento.add(factoriaRaro.crearEquipable());
            }

        }
        return equipamiento;

    }
    

}
