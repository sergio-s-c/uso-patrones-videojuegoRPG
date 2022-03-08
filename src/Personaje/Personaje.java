/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Objetos.Equipamiento;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Habilidades.Envenenado;
import Habilidades.Estado;
import Habilidades.HabilidadGeneral;
import Habilidades.IEstado;
import Habilidades.IHabilidades;
import Habilidades.Sangrado;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Personaje {

    private String nombre;
    private String clase;
    private int salud;
    private int vidaMaxima;
    private int mana;
    private int manaMaximo;
    private int dano;
    private int defensa;
    private int velocidad;
    private int resistencia;
    private int evasion;
    private boolean aturdido;
    private boolean vivo;
    private boolean controlable;
    private ArrayList<IEstado> estados;
    private Sangrado sangrado;
    private Envenenado envenenado;
    private ArrayList<Equipamiento> equipamiento;
    private ArrayList<IHabilidades> habilidades;
    private BufferedImage imagenPersonaje;

    /**
     *Inicializa atributos del personaje
     */
    public Personaje() {
        sangrado = null;
        envenenado = null;
        equipamiento = new ArrayList<Equipamiento>();
        estados = new ArrayList<IEstado>();
        this.aturdido = false;
        this.vivo = true;
        habilidades = new ArrayList<IHabilidades>();
    }

    /**
     *
     * @return
     */
    public BufferedImage getImagenPersonaje() {
        return imagenPersonaje;
    }

    /**
     *
     * @param imagenPersonaje
     */
    public void setImagenPersonaje(BufferedImage imagenPersonaje) {
        this.imagenPersonaje = imagenPersonaje;
    }

    /**
     *Añade una habilidad al personaje
     * @param habilidad
     */
    public void anadirHabilidad(IHabilidades habilidad) {
        habilidades.add(habilidad);
    }

    /**
     *
     * @return
     */
    public int getSalud() {
        return salud;
    }

    /**
     *
     * @return
     */
    public boolean isControlable() {
        return controlable;
    }

    /**
     *
     * @param controlable
     */
    public void setControlable(boolean controlable) {
        this.controlable = controlable;
    }

    /**
     *
     * @return
     */
    public boolean isVivo() {
        return vivo;
    }

    /**
     *
     * @param vivo
     */
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    /**
     *Comprueba que la salud no puede superar la vida maxima
     * @param salud
     */
    public void setSalud(int salud) {
        this.salud = salud;
        if (this.salud > vidaMaxima) {
            this.salud = vidaMaxima;
        }
        if (this.salud <= 0) {
            this.vivo = false;
        }

    }

    /**
     *
     * @return
     */
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    /**
     *
     * @return
     */
    public String getClase() {
        return clase;
    }

    /**
     *
     * @param clase
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     *La vida maxima puede ser 1 como mínimo
     * @param vidaMaxima
     */
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
        if (this.vidaMaxima < 1) {
            this.vidaMaxima = 1;
        }
    }

    /**
     *
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     *
     * @param mana
     */
    public void setMana(int mana) {
        this.mana = mana;

        if (this.mana > manaMaximo) {
            this.mana = manaMaximo;
        }

    }

    /**
     *
     * @return
     */
    public int getManaMaximo() {
        return manaMaximo;
    }

    /**
     *Comprueba que ek mana no puede superar el mana maximo
     * @param manaMaximo
     */
    public void setManaMaximo(int manaMaximo) {
        this.manaMaximo = manaMaximo;
        if (this.manaMaximo < 1) {
            this.manaMaximo = 1;
        }
    }

    /**
     *
     * @return
     */
    public int getDano() {
        return dano;
    }

    /**
     *
     * @param dano
     */
    public void setDano(int dano) {
        this.dano = dano;
        if (this.dano < 1) {
            this.dano = 1;
        }
    }

    /**
     *
     * @return
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     *
     * @param defensa
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
        if (this.defensa < 0) {
            this.defensa = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     *
     * @param velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        if (this.velocidad < 0) {
            this.velocidad = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     *
     * @param resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
        if (this.resistencia < 0) {
            this.resistencia = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getEvasion() {
        return evasion;
    }

    /**
     *
     * @param evasion
     */
    public void setEvasion(int evasion) {
        this.evasion = evasion;
        if (this.evasion < 0) {
            this.evasion = 0;
        }
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public boolean isAturdido() {
        return aturdido;
    }

    /**
     *
     * @param aturdido
     */
    public void setAturdido(boolean aturdido) {
        this.aturdido = aturdido;
    }

    /**
     *
     * @return
     */
    public ArrayList<IEstado> getEstados() {
        return estados;
    }

    /**
     *
     * @param estados
     */
    public void setEstados(ArrayList<IEstado> estados) {
        this.estados = estados;
    }

    /**
     *
     * @return
     */
    public Sangrado getSangrado() {
        return sangrado;
    }

    /**
     *
     * @param sangrado
     */
    public void setSangrado(Sangrado sangrado) {
        this.sangrado = sangrado;
    }

    /**
     *
     * @return
     */
    public Envenenado getEnvenenado() {
        return envenenado;
    }

    /**
     *
     * @param envenenado
     */
    public void setEnvenenado(Envenenado envenenado) {
        this.envenenado = envenenado;
    }

    /**
     *
     * @return
     */
    public ArrayList<Equipamiento> getEquipamiento() {
        return equipamiento;
    }

    /**
     *
     * @param equipamiento
     */
    public void setEquipamiento(ArrayList<Equipamiento> equipamiento) {
        this.equipamiento = equipamiento;
    }

    /**
     *
     * @return
     */
    public ArrayList<IHabilidades> getHabilidades() {
        return habilidades;
    }

    /**
     *
     * @param habilidades
     */
    public void setHabilidades(ArrayList<IHabilidades> habilidades) {
        this.habilidades = habilidades;
    }

    /**
     *Elimina el sangrado
     */
    public void curarSangrado() {
        this.sangrado = null;
    }

    /**
     *Elimina el envenenamiento
     */
    public void curarEnvenamiento() {
        this.envenenado = null;
    }

    /**
     *Se le añade un equipamiento al personaje
     * @param equipamiento el equipamiento que se añade
     */
    public void anadirEquipamiento(Equipamiento equipamiento) {
        this.equipamiento.add(equipamiento);
        sumarAtributos(equipamiento.getAtributos());
    }

    /**
     *se le suma los atributos del equipamiento añadido
     * @param atributos los atributos del equipamiento
     */
    public void sumarAtributos(HashMap<String, Integer> atributos) {
        if (atributos.containsKey("evasion")) {
            this.evasion += atributos.get("evasion");
        }
        if (atributos.containsKey("defensa")) {
            this.defensa += atributos.get("defensa");
        }
        if (atributos.containsKey("velocidad")) {
            this.velocidad += atributos.get("velocidad");
        }
        if (atributos.containsKey("mana")) {
            this.manaMaximo += atributos.get("mana");
        }
        if (atributos.containsKey("dano")) {
            this.dano += atributos.get("dano");
        }
        if (atributos.containsKey("resistencia")) {
            this.resistencia += atributos.get("resistencia");
        }
        if (atributos.containsKey("vida")) {
            this.vidaMaxima += atributos.get("vida");
        }

    }

    /**
     *Actualiza los atributos del personaje para aplicar los efectos alterados, cada subida o bajado de atributo afecta en 1.3
     */
    public void actualizarEstados() {
        double efecto = 1.3;

        for (int i = 0; i < this.estados.size(); i++) {
            switch (this.estados.get(i).getAtributo()) {

                case "evasion":
                    evasion = (int) (evasion * Math.pow(efecto, (this.estados.get(i).getTamano())));

                case "defensa":
                    defensa = (int) (defensa * Math.pow(efecto, (this.estados.get(i).getTamano())));
                case "velocidad":
                    velocidad = (int) (velocidad * Math.pow(efecto, (this.estados.get(i).getTamano())));
                case "dano":
                    dano = (int) (dano * Math.pow(efecto, (this.estados.get(i).getTamano())));
                case "resistencia":
                    resistencia = (int) (resistencia * Math.pow(efecto, (this.estados.get(i).getTamano())));
            }
            this.estados.get(i).setDuracion(this.estados.get(i).getDuracion() - 1); //actualizamos la duracion

            if (this.estados.get(i).getDuracion() < 1) {//eliminamos si llega su duracion a 0
                this.estados.remove(i);
                i -= 1;
            }
        }
    }

    /**
     *le afectan los perjuicios al personaje
     * @return el log del perjuicio
     */
    public String aplicarPerjuicios() {
        String frase = "";
        if (sangrado != null) {
            frase = sangrado.aplicarPerjuicio(this);
        }
        if (envenenado != null) {
            frase += envenenado.aplicarPerjuicio(this);
        }
        return frase;
    }

    /**
     *se le añade un estado alterado
     * @param estado
     */
    public void anadirEstado(IEstado estado) {
        this.estados.add(estado);

    }

    /**
     *Comprueba si el personaje puede efectuar su ataque y si es así llama a la habilidad para efecutar su efecto
     * @param habilidad la habilidad a usar
     * @param objetivo el personaje sobre el que va a efectuar la habilidad
     * @return el log de lo sucedido en el ataque
     */
    public String atacar(IHabilidades habilidad, ArrayList<Personaje> objetivo) {
        String frase;
        if (this.aturdido) {
            frase = " está aturdido";
            this.aturdido = false;
        } else {
            if (this.getMana() >= habilidad.getMana()) {
                this.setMana(this.getMana() - habilidad.getMana());

                frase = habilidad.ejecutarAccion(this.dano, objetivo);

            } else {
                frase = " no tiene suficiente mana";

            }
        }
        frase = this.getNombre() + frase;
        return frase;

    }

}
