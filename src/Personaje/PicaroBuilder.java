/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Habilidades.Estado;
import Habilidades.HabilidadEstado;
import Habilidades.HabilidadGeneral;
import Habilidades.HabilidadPerjuicios;
import Habilidades.Sangrado;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class PicaroBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Pícaro");
        personaje.setNombre("Jenny La Pícara");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(65 + rand.nextInt(20));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(35 + rand.nextInt(20));
        personaje.setMana(personaje.getManaMaximo());
        personaje.setDano(13 + rand.nextInt(3));
        personaje.setDefensa(3 + rand.nextInt(6));
        personaje.setVelocidad(14 + rand.nextInt(5));
        personaje.setResistencia(9 + rand.nextInt(3));
        personaje.setEvasion(14 + rand.nextInt(10));
        personaje.setControlable(true);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/picaro.png")));
        } catch (IOException ex) {
            Logger.getLogger(PicaroBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral punal = new HabilidadGeneral(4, 17, "Puñalada");

        HabilidadPerjuicios punalada = new HabilidadPerjuicios(punal, new Sangrado(3));
        punalada.setPerjuicios(new Sangrado(3));
        personaje.anadirHabilidad(punalada);
        HabilidadGeneral bomba = new HabilidadGeneral(0, 20, "Bomba de humo");

        HabilidadEstado bombaDeHumo = new HabilidadEstado(bomba, new Estado("evasión", 4, 2));
        bombaDeHumo.setObjetivo("individual");

        personaje.anadirHabilidad(bombaDeHumo);
        HabilidadGeneral punto = new HabilidadGeneral(4, 19, "Punto débil");

        HabilidadEstado puntoDebil = new HabilidadEstado(punto, new Estado("defensa", 3, -1));

        personaje.anadirHabilidad(puntoDebil);
        HabilidadGeneral navajazoSorpresa = new HabilidadGeneral(9, 5, "Navajazo sorpresa");
        personaje.anadirHabilidad(navajazoSorpresa);

    }
}
