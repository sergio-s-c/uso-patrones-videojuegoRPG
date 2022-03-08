/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import Habilidades.HabilidadGeneral;
import Habilidades.Sangrado;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class DuendeAsesinoBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Duende Asesino");
        personaje.setNombre("Duende Asesino");

    }

    /**
     * le pone los atributos al personaje
     */

    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(48 + rand.nextInt(23));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(4 + rand.nextInt(3));
        personaje.setDefensa(5 + rand.nextInt(3));
        personaje.setVelocidad(13 + rand.nextInt(3));
        personaje.setResistencia(4 + rand.nextInt(3));
        personaje.setEvasion(5 + rand.nextInt(2));
        personaje.setControlable(false);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/GoblinAsesino.png")));
        } catch (IOException ex) {

        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral embestidaColerica = new HabilidadGeneral(5, 0, "Embestida colérica");
        embestidaColerica.setAturdimiento(true);
        personaje.anadirHabilidad(embestidaColerica);

    }
}
