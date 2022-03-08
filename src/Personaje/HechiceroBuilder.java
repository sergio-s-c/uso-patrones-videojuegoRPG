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
import Habilidades.Estado;
import Habilidades.HabilidadEstado;
import Habilidades.HabilidadGeneral;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class HechiceroBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Hechicero");
        personaje.setNombre("José Luis El Hechicero");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(72 + rand.nextInt(16));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(70 + rand.nextInt(10));
        personaje.setMana(personaje.getManaMaximo());
        personaje.setDano(14 + rand.nextInt(3));
        personaje.setDefensa(2 + rand.nextInt(5));
        personaje.setVelocidad(7 + rand.nextInt(4));
        personaje.setResistencia(9 + rand.nextInt(4));
        personaje.setEvasion(8 + rand.nextInt(2));
        personaje.setControlable(true);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/mago.png")));
        } catch (IOException ex) {

        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral vortice = new HabilidadGeneral(7, 9, "Vórtice subcionador");
        HabilidadEstado vorticeSubcionador = new HabilidadEstado(vortice, new Estado("resistencia", 3, 1));

        personaje.anadirHabilidad(vorticeSubcionador);
        HabilidadGeneral misilesMágicos = new HabilidadGeneral(10, 20, "Misiles mágicos");
        misilesMágicos.setObjetivo("multiple");
        personaje.anadirHabilidad(misilesMágicos);
        HabilidadGeneral hecatombeIgnea = new HabilidadGeneral(30, 30, "Hecatombe Ígnea");
        personaje.anadirHabilidad(hecatombeIgnea);
        HabilidadGeneral pacto = new HabilidadGeneral(0, 6, "Pacto con el Diablo");
        HabilidadEstado pactoConElDiablo = new HabilidadEstado(pacto, new Estado("dano", 3, 2));
        pactoConElDiablo.setObjetivo("individual");

        personaje.anadirHabilidad(pactoConElDiablo);
    }

}
