/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.util.Random;
import Habilidades.Estado;
import Habilidades.HabilidadCurativa;
import Habilidades.HabilidadGeneral;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class MimicoBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Mimico");
        personaje.setNombre("Mimico");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(1000 + rand.nextInt(800));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(24 + rand.nextInt(9));
        personaje.setDefensa(15 + rand.nextInt(5));
        personaje.setVelocidad(2 + rand.nextInt(3));
        personaje.setResistencia(30 + rand.nextInt(5));
        personaje.setEvasion(8 + rand.nextInt(7));
        personaje.setControlable(false);

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral mordiscoFugaz = new HabilidadGeneral(25, 0, "Mordisco fugaz");

        personaje.anadirHabilidad(mordiscoFugaz);
        HabilidadGeneral desc = new HabilidadGeneral(0, 0, "Descanso");
        HabilidadCurativa descanso = new HabilidadCurativa(desc, 45);
        descanso.setObjetivo("individual");

        personaje.anadirHabilidad(descanso);
        HabilidadGeneral lluviaDeValijas = new HabilidadGeneral(16, 0, "Lluvia de valijas");
        lluviaDeValijas.setObjetivo("multiple");
        personaje.anadirHabilidad(lluviaDeValijas);

    }
}
