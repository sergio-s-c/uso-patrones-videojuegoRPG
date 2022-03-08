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
import Habilidades.Envenenado;
import Habilidades.HabilidadCurativa;
import Habilidades.HabilidadGeneral;
import Habilidades.HabilidadPerjuicios;
import Habilidades.Sangrado;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class DuendeChamanBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */

    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Duende Chaman");
        personaje.setNombre("Duende Chaman");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(48 + rand.nextInt(12));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(8 + rand.nextInt(2));
        personaje.setDefensa(3 + rand.nextInt(4));
        personaje.setVelocidad(6 + rand.nextInt(3));
        personaje.setResistencia(4 + rand.nextInt(3));
        personaje.setEvasion(3 + rand.nextInt(2));
        personaje.setControlable(false);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/DuendeChaman.png")));
        } catch (IOException ex) {

        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral polvos = new HabilidadGeneral(0, 0, "Polvos Arcanos");

        HabilidadCurativa polvosArcanos = new HabilidadCurativa(polvos, 13);

        personaje.anadirHabilidad(polvosArcanos);
        HabilidadGeneral polvosV = new HabilidadGeneral(1, 0, "Polvos Venenosos");
        HabilidadPerjuicios polvosVenenosos = new HabilidadPerjuicios(polvosV, new Envenenado(3));

        personaje.anadirHabilidad(polvosVenenosos);
    }
}
