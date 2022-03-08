/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Random;
import javax.imageio.ImageIO;
import Habilidades.Estado;
import Habilidades.HabilidadEstado;
import Habilidades.HabilidadGeneral;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class EscuderoBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */

    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Escudero");
        personaje.setNombre("Gonzalo El Escudero");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(125 + rand.nextInt(40));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(35 + rand.nextInt(20));
        personaje.setMana(personaje.getManaMaximo());
        personaje.setDano(9 + rand.nextInt(4));
        personaje.setDefensa(13 + rand.nextInt(7));
        personaje.setVelocidad(3 + rand.nextInt(6));
        personaje.setResistencia(7 + rand.nextInt(5));
        personaje.setEvasion(2 + rand.nextInt(3));
        personaje.setControlable(true);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/escudero.png")));
        } catch (IOException ex) {

        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral alzar = new HabilidadGeneral(0, 4, "Alzar Escudo");
        HabilidadEstado alzarEscudo = new HabilidadEstado(alzar, new Estado("defensa", 3, 1));

        alzarEscudo.setObjetivo("individual");
        personaje.anadirHabilidad(alzarEscudo);
        HabilidadGeneral espazado = new HabilidadGeneral(12, 8, "Espadazo");
        personaje.anadirHabilidad(espazado);
        HabilidadGeneral porrazo = new HabilidadGeneral(3, 14, "Porrazo");
        porrazo.setAturdimiento(true);
        personaje.anadirHabilidad(porrazo);
        HabilidadGeneral tajoGiratorio = new HabilidadGeneral(4, 10, "Tajo Giratorio");
        tajoGiratorio.setObjetivo("multiple");
        personaje.anadirHabilidad(tajoGiratorio);

    }

}
