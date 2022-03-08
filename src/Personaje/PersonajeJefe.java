/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Habilidades.IHabilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class PersonajeJefe extends Personaje {

    private Estrategia estrategia;

    /**
     *La estrategia del personaje
     * @param estrategia
     */
    public PersonajeJefe(Estrategia estrategia) {

        super();
        this.estrategia = estrategia;
    }

    /**
     *
     * @return
     */
    public Estrategia getEstrategia() {
        return estrategia;
    }

    /**
     *
     * @param estrategia
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    /**
     *Comprueba si debe cambiar de estrategia y luego llama a la estrategia para ejectuarla
     * @return
     */
    public IHabilidades ejecutarEstrategia() {
        if (super.getSalud() < (super.getVidaMaxima()/ 2)) {
            setEstrategia(new EstrategiaCaudilloDuendeAgresiva());

        }
        return this.estrategia.seleccionarAtaque(this);

    }

}
