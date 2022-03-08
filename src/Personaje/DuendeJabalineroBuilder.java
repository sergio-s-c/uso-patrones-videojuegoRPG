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
import Habilidades.HabilidadGeneral;
import Habilidades.HabilidadPerjuicios;
import Habilidades.Sangrado;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class DuendeJabalineroBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */

    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Duende Jabalinero");
        personaje.setNombre("Duende Jabalinero");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(28 + rand.nextInt(20));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(7 + rand.nextInt(4));
        personaje.setDefensa(3 + rand.nextInt(2));
        personaje.setVelocidad(8 + rand.nextInt(4));
        personaje.setResistencia(3 + rand.nextInt(3));
        personaje.setEvasion(4 + rand.nextInt(2));
        personaje.setControlable(false);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/GoblinJabalinero.png")));
        } catch (IOException ex) {
            Logger.getLogger(PicaroBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral lanzamiento = new HabilidadGeneral(6, 0, "Lanzamiento de jabalina");
        HabilidadPerjuicios lanzamientoJabalina = new HabilidadPerjuicios(lanzamiento, (new Sangrado(2)));

        personaje.anadirHabilidad(lanzamientoJabalina);

    }

}
