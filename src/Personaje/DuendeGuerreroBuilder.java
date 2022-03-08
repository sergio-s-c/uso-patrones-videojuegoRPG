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
import Habilidades.HabilidadGeneral;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class DuendeGuerreroBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */

    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Duende Guerrero");
        personaje.setNombre("Duende Guerrero");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(60 + rand.nextInt(10));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(4 + rand.nextInt(3));
        personaje.setDefensa(7 + rand.nextInt(2));
        personaje.setVelocidad(2 + rand.nextInt(2));
        personaje.setResistencia(4 + rand.nextInt(4));
        personaje.setEvasion(2 + rand.nextInt(2));
        personaje.setControlable(false);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/DuendeGuerrero.png")));
        } catch (IOException ex) {
            Logger.getLogger(PicaroBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral estocadaLigera = new HabilidadGeneral(4, 0, "Estocada Ligera");

        personaje.anadirHabilidad(estocadaLigera);

    }

}
