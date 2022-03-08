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
import Habilidades.Estado;
import Habilidades.HabilidadCurativa;
import Habilidades.HabilidadEstado;
import Habilidades.HabilidadGeneral;
import Habilidades.HabilidadPerjuicios;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class CuranderoBuilder extends AbstractPersonajeBuilder {

    /**
     * le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje = new Personaje();
        personaje.setClase("Curandero");
        personaje.setNombre("Conchita La Curandera");

    }

    /**
     * le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(86 + rand.nextInt(32));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(100 + rand.nextInt(80));
        personaje.setMana(personaje.getManaMaximo());
        personaje.setDano(7 + rand.nextInt(2));
        personaje.setDefensa(7 + rand.nextInt(5));
        personaje.setVelocidad(6 + rand.nextInt(11));
        personaje.setResistencia(8 + rand.nextInt(3));
        personaje.setEvasion(7 + rand.nextInt(5));
        personaje.setControlable(true);
        try {
            personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/curandero.png")));
        } catch (IOException ex) {

        }

    }

    /**
     * le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral vendaje = new HabilidadGeneral(0, 24, "Vendaje presuroso");
        HabilidadCurativa vendajePresuroso = new HabilidadCurativa(vendaje, 10);

        vendajePresuroso.setObjetivo("individual");
        personaje.anadirHabilidad(vendajePresuroso);
        HabilidadGeneral bisturi = new HabilidadGeneral(3, 14, "Bisturí Envenenado");
        HabilidadPerjuicios bisturiEnvenenado = new HabilidadPerjuicios(bisturi, new Envenenado(2));

        personaje.anadirHabilidad(bisturiEnvenenado);
        HabilidadGeneral vapores = new HabilidadGeneral(0, 31, "Vapores Medicinales");
        HabilidadCurativa vaporesMedicinales = new HabilidadCurativa(vapores, 5);
        vaporesMedicinales.setObjetivo("equipo");
        personaje.anadirHabilidad(vaporesMedicinales);

        HabilidadGeneral bomba = new HabilidadGeneral(0, 7, "Bomba pegajosa");

        HabilidadEstado bombaPegajosa = new HabilidadEstado(bomba, new Estado("velocidad", 3, -1));

        personaje.anadirHabilidad(bombaPegajosa);
    }

}
