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
import Habilidades.IHabilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class CaudilloDuendeBuilder extends AbstractPersonajeBuilder {

    private Estrategia estrategia;

    /**
     *le pone la clase y nombre al personaje
     */
    public void ponerClase() {
        personaje=new PersonajeJefe(new EstrategiaCaudilloDuendeConfiada());
        personaje.setClase("CaudilloDuende");
        personaje.setNombre("Gonzalo El Caudillo Duende");
        

    }

    /**
     *le pone los atributos al personaje
     */
    public void crearAtributos() {
        Random rand = new Random();
        personaje.setVidaMaxima(310 + rand.nextInt(90));
        personaje.setSalud(personaje.getVidaMaxima());
        personaje.setManaMaximo(0);
        personaje.setMana(personaje.getMana());
        personaje.setDano(16 + rand.nextInt(3));
        personaje.setDefensa(12 + rand.nextInt(4));
        personaje.setVelocidad(5 + rand.nextInt(3));
        personaje.setResistencia(8 + rand.nextInt(10));
        personaje.setEvasion(6 + rand.nextInt(4));
        personaje.setControlable(false);
               try {
              personaje.setImagenPersonaje(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/caudillo.png")));
          } catch (IOException ex) {
            
          }

    }

    /**
     *le pone las habilidades al personaje
     */
    public void crearHabilidades() {
        HabilidadGeneral pisotonMasivo = new HabilidadGeneral(7, 0, "Pisotón");
        pisotonMasivo.setAturdimiento(true);
        pisotonMasivo.setObjetivo("multiple");
        personaje.anadirHabilidad(pisotonMasivo);
        HabilidadGeneral porrazoColerico = new HabilidadGeneral(26, 0, "Porrazo Colérico");
        personaje.anadirHabilidad(porrazoColerico);
        HabilidadGeneral combo = new HabilidadGeneral(14, 0, "Combo Marcial");
        HabilidadEstado comboMarcial=new HabilidadEstado(combo,new Estado("defensa", 5,-2));
        
        personaje.anadirHabilidad(comboMarcial);
        HabilidadGeneral cabreo= new HabilidadGeneral(0, 0, "Cabreo Histórico");
        HabilidadEstado cabreoHistorico=new HabilidadEstado(cabreo,new Estado("dano", 4,2));
       
        personaje.anadirHabilidad(cabreoHistorico);

    }
}
