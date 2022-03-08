/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Mapa.Mapa;
import Personaje.Personaje;
import Personaje.PersonajeJefe;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import Habilidades.IHabilidades;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class VentanaCombate extends javax.swing.JFrame {

    /**
     * Creates new form JFrameCombate
     */
    private ArrayList<Personaje> equipo;
    private VentanaCofre cofre;
    private ArrayList<Personaje> equipoEnemigo;
    private Partida partida;
    private Random rand = new Random();
    private ImageIcon image;
    private Icon icono;
    private ArrayList<JLabel> listaLabelEquipo;
    private ArrayList<JLabel> listaLabelEnemigo;
    private ArrayList<JLabel> listaLabelVidaEnemigo;
    private ArrayList<JLabel> listaLabelManaEquipo;
    private ArrayList<JLabel> listaLabelVidaEquipo;
    private ArrayList<JButton> botonesHabilidades;
    private int indice;
    private ArrayList<Personaje> orden;
    private boolean turno;
    private ArrayList<Personaje> objetivo;
    private ArrayList<Personaje> aux;
    private int numHab;
    private Mapa mapa;
    private Derrota derrota;

    private Border bordeRojo = BorderFactory.createLineBorder(Color.red);

    /**
     * Constructor
     *
     * @param mapa los datos del mapa
     * @param equipo el equipo del usuario
     * @param equipoEnemigo el equipo enemigo contra el que combate
     * @param partida los datos de la partida
     */
    public VentanaCombate(Mapa mapa, ArrayList<Personaje> equipo, ArrayList<Personaje> equipoEnemigo, Partida partida) {

        this.mapa = mapa;
        numHab = 0;
        objetivo = new ArrayList<Personaje>();
        indice = 0;
        initComponents();
        turno = false;
        aux = new ArrayList<Personaje>();
        this.equipoEnemigo = equipoEnemigo;
        botonesHabilidades = new ArrayList<JButton>(List.of(jButtonHab1, jButtonHab2, jButtonHab3, jButtonHab4));
        listaLabelEquipo = new ArrayList<JLabel>(List.of(jLabelEquipo1, jLabelEquipo2, jLabelEquipo3));
        listaLabelEnemigo = new ArrayList<JLabel>(List.of(jLabelEnemigo1, jLabelEnemigo2, jLabelEnemigo3, jLabelEnemigo4, jLabelEnemigo5));
        listaLabelVidaEnemigo = new ArrayList<JLabel>(List.of(jLabelVidaEnemigo1, jLabelVidaEnemigo2, jLabelVidaEnemigo3, jLabelVidaEnemigo4, jLabelVidaEnemigo5));
        listaLabelVidaEquipo = new ArrayList<JLabel>(List.of(jLabelVidaEquipo1, jLabelVidaEquipo2, jLabelVidaEquipo3));
        listaLabelManaEquipo = new ArrayList<JLabel>(List.of(jLabelManaEquipo1, jLabelManaEquipo2, jLabelManaEquipo3));
        this.equipo = equipo;
        this.partida = partida;
        objetivo.add(equipoEnemigo.get(0));//Para que si no se selecciona objetivo pegue por defecto al primer enemigo
        jLabelEnemigo1.setBorder(bordeRojo);
        ImageIcon image = new ImageIcon("../TierraDesolada/imagenes/partida/flecha.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(jLabelFlecha.getWidth(), jLabelFlecha.getHeight(), Image.SCALE_DEFAULT));
        jLabelFlecha.setIcon(icono);

        try {
            combate();
        } catch (InterruptedException ex) {
            Logger.getLogger(VentanaCombate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Inicia el proceso del combat
     *
     */
    private void combate() throws InterruptedException {
        indice = 0;
        orden = establecerOrden();
        actualizarImagenes();

        recargaMana();

        jButtonHab.setBackground(Color.blue);

        if (orden.get(indice).isControlable()) {
            for (int j = 0; j < orden.get(indice).getHabilidades().size(); j++) {
                botonesHabilidades.get(j).setText(orden.get(indice).getHabilidades().get(j).getNombre());
            }
        }

    }
    /**
     *Se calcula el orden de actuación de los personajes según su velocidad
     *@return devuelve una lista con los personajes ordenados por velocidad
     */
    private ArrayList<Personaje> establecerOrden() {
        ArrayList<Personaje> orden = new ArrayList<Personaje>(equipo);
        orden.addAll(equipoEnemigo);
        Collections.sort(orden, new Comparator<Personaje>() {
            @Override
            public int compare(Personaje p1, Personaje p2) {
                return new Integer(p2.getVelocidad()).compareTo(p1.getVelocidad());

            }

        });

        return orden;
    }
    /**
     * Actualiza las imágenes de la ventana
     *
     */
    private void actualizarImagenes() {

        //Muestra una mini imagen del siguiente en atacar.
        if (indice < orden.size()) {
            image = new ImageIcon(orden.get(indice).getImagenPersonaje());

            icono = new ImageIcon(image.getImage().getScaledInstance(jLabelImagenTurno1.getWidth(), jLabelImagenTurno1.getHeight(), Image.SCALE_DEFAULT));
            jLabelImagenTurno1.setIcon(icono);
        } else {
            jLabelImagenTurno1.setIcon(null);
        }

        if (indice + 1 < orden.size()) {
            image = new ImageIcon(orden.get(indice + 1).getImagenPersonaje());

            icono = new ImageIcon(image.getImage().getScaledInstance(jLabelImagenTurno2.getWidth(), jLabelImagenTurno2.getHeight(), Image.SCALE_DEFAULT));
            jLabelImagenTurno2.setIcon(icono);
        } else {
            jLabelImagenTurno2.setIcon(null);
        }

        for (int i = 0; i < equipo.size(); i++) {
            image = new ImageIcon(equipo.get(i).getImagenPersonaje());

            icono = new ImageIcon(image.getImage().getScaledInstance(jLabelEquipo1.getWidth(), jLabelEquipo1.getHeight(), Image.SCALE_DEFAULT));
            listaLabelEquipo.get(i).setIcon(icono);

        }

        for (int j = 0; j < equipoEnemigo.size(); j++) {
            image = new ImageIcon(equipoEnemigo.get(j).getImagenPersonaje());

            icono = new ImageIcon(image.getImage().getScaledInstance(jLabelEquipo1.getWidth(), jLabelEquipo1.getHeight(), Image.SCALE_DEFAULT));
            listaLabelEnemigo.get(j).setIcon(icono);
        }

        for (int i = 0; i < equipoEnemigo.size(); i++) {
            listaLabelEnemigo.get(i).setBorder(null);
            listaLabelVidaEnemigo.get(i).setText(Integer.toString(equipoEnemigo.get(i).getSalud()) + "/" + Integer.toString(equipoEnemigo.get(i).getVidaMaxima()));
        }
        for (int i = 0; i < equipo.size(); i++) {
            listaLabelEquipo.get(i).setBorder(null);
            listaLabelVidaEquipo.get(i).setText(Integer.toString(equipo.get(i).getSalud()) + "/" + Integer.toString(equipo.get(i).getVidaMaxima()));
            listaLabelManaEquipo.get(i).setText(Integer.toString(equipo.get(i).getMana()) + "/" + Integer.toString(equipo.get(i).getManaMaximo()));
        }

        for (int i = 0; i < botonesHabilidades.size(); i++) {
            botonesHabilidades.get(i).setBackground(Color.white);
        }

    }
    /**
     * Cada turno recarga un poco de mana los personajes
     *
     */
    private void recargaMana() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>(equipo);
        personajes.addAll(equipoEnemigo);
        for (int i = 0; i < personajes.size(); i++) {
            personajes.get(i).setMana(personajes.get(i).getMana() + 4);

        }
    }
    /**
     * Comprueba el siguiente personaje en atacar, le aplica los estados, coge la habilidad y el objetivo y la ejecuta
     *
     */
    private void turnos() {
        IHabilidades hab = null;
        int aleatorio;
        String texto = "";

        if (indice < orden.size()) {

            orden.get(indice).actualizarEstados();//al principio del turno se actualizan los estados alterados del personaje
            if (orden.get(indice).isControlable()) {

                hab = seleccionarHabilidad(orden.get(indice));
                objetivo = seleccionarObjetivo(hab, objetivo);
                texto = orden.get(indice).atacar(hab, objetivo);

            } else {
                if (orden.get(indice) instanceof PersonajeJefe) {
                    PersonajeJefe jefe = (PersonajeJefe) orden.get(indice);
                    hab = jefe.ejecutarEstrategia();

                } else {
                    aleatorio = rand.nextInt(orden.get(indice).getHabilidades().size());

                    hab = orden.get(indice).getHabilidades().get(aleatorio);
                }
                objetivo = seleccionarObjetivo(hab);
                texto = orden.get(indice).atacar(hab, objetivo);

            }

            jTextAreaLog.append(texto + "\n");
            jTextAreaLog.append(orden.get(indice).aplicarPerjuicios() + "\n");
            comprobarMuertos(orden);
            indice += 1;
            actualizarImagenes();

            if (indice < orden.size()) {

                if (orden.get(indice).isControlable()) {
                    for (int j = 0; j < orden.get(indice).getHabilidades().size(); j++) {
                        botonesHabilidades.get(j).setText(orden.get(indice).getHabilidades().get(j).getNombre());
                    }
                } else {

                    actualizarImagenes();
                    turnos();

                }

            } else {

                try {
                    combate();
                } catch (InterruptedException ex) {
                    Logger.getLogger(VentanaCombate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    /**
     *Se selecciona el objetivo para el equipo aliado
     *
     */
    private ArrayList<Personaje> seleccionarObjetivo(IHabilidades hab, ArrayList<Personaje> objetivo) {
        //Se selecciona el objetivo para el equipo aliado
        if (hab.getObjetivo() == "multiple") {
            objetivo = equipoEnemigo;

        } else if (hab.getObjetivo() == "equipo") {
            objetivo = equipo;
        }

        return objetivo;

    }
/**
     *Se selecciona la habilidad para el enemigo
     *
     */
    private IHabilidades seleccionarHabilidad(Personaje personaje) {
        //Se selecciona la habilidad para el enemigo
        IHabilidades hab = null;

        hab = personaje.getHabilidades().get(numHab);

        return hab;
    }
    /**
     * Se selecciona el objetivo para el enemigo
     *
     */
    private ArrayList<Personaje> seleccionarObjetivo(IHabilidades hab) {
        //Se selecciona el objetivo para el enemigo
        ArrayList<Personaje> objetivo = new ArrayList<Personaje>();
        if (hab.getObjetivo() == "multiple") {
            objetivo = equipoEnemigo;

        } else if (hab.getObjetivo() == "equipo") {
            objetivo = equipo;
        } else {
            Random rand = new Random();
            objetivo.add(equipo.get(rand.nextInt(equipo.size())));
        }
        return objetivo;
    }
/**
     * Se comprueba si hay personajes muertos y si es así se eliminan sus imágenes
     *@param personajes la lista con todos los personajes del combate
     */
    private void comprobarMuertos(ArrayList<Personaje> personajes) {
        for (int i = 0; i < personajes.size(); i++) {
            if (!personajes.get(i).isVivo()) {
                jTextAreaLog.append(personajes.get(i).getNombre() + " ha sido eliminado." + "\n");
                if (indice > i) {//Para evitar que se salte el turno del siguiente personaje al eliminar un personaje y que ataque el muerto
                    indice--;
                }

                if (personajes.get(i).isControlable()) {
                    equipo.remove(personajes.get(i));
                    orden.remove(personajes.get(i));

                    listaLabelVidaEquipo.get(equipo.size()).setText(null);
                    listaLabelManaEquipo.get(equipo.size()).setText(null);

                    listaLabelEquipo.get(equipo.size()).setIcon(null);

                } else {
                    equipoEnemigo.remove(personajes.get(i));
                    orden.remove(personajes.get(i));

                    listaLabelVidaEnemigo.get(equipoEnemigo.size()).setText(null);

                    listaLabelEnemigo.get(equipoEnemigo.size()).setIcon(null);
                }

            }
        }
        if (equipo.size() < 1) {
            //gameOver
            if (derrota != null) {
                derrota.dispose();
            }
            this.dispose();
            this.setVisible(false);
            derrota = new Derrota();
            derrota.setVisible(true);

        } else if (equipoEnemigo.size() < 1) {
            recargaMana();

            cofre = new VentanaCofre(partida.getInventario(), partida.getListaEquipamiento(), equipo, partida);

            this.setVisible(false);
            cofre.setVisible(true);
            this.dispose();

        }

    }
/**
     * Se marca al personajes sobre el que se ha hecho clic
     *@param equip la lista con todos los personajes del equipo o equipo rival
       *@param posicion el numero de la ventan clicada
     */
    private void clicarObjetivo(ArrayList<Personaje> equip, int posicion) {
        if (equip.size() > posicion) {
            aux.add(equip.get(posicion));
            objetivo = new ArrayList<Personaje>();
            objetivo.add(aux.get(0));
            aux.remove(0);
            jTextAreaLog.append("Se ha seleccionado como objetivo " + objetivo.get(0).getNombre() + "\n");

            for (int i = 0; i < equipoEnemigo.size(); i++) {
                listaLabelEnemigo.get(i).setBorder(null);
            }

            for (int i = 0; i < equipo.size(); i++) {
                listaLabelEquipo.get(i).setBorder(null);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelEquipo1 = new javax.swing.JLabel();
        jLabelEquipo3 = new javax.swing.JLabel();
        jLabelEquipo2 = new javax.swing.JLabel();
        jLabelEnemigo2 = new javax.swing.JLabel();
        jLabelEnemigo3 = new javax.swing.JLabel();
        jLabelEnemigo1 = new javax.swing.JLabel();
        jButtonHab = new javax.swing.JButton();
        jButtonHab1 = new javax.swing.JButton();
        jButtonHab2 = new javax.swing.JButton();
        jButtonHab3 = new javax.swing.JButton();
        jButtonHab4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
        jLabelVidaEquipo1 = new javax.swing.JLabel();
        jLabelManaEquipo1 = new javax.swing.JLabel();
        jLabelVidaEnemigo1 = new javax.swing.JLabel();
        jLabelVidaEnemigo4 = new javax.swing.JLabel();
        jLabelVidaEnemigo3 = new javax.swing.JLabel();
        jLabelVidaEquipo2 = new javax.swing.JLabel();
        jLabelManaEquipo2 = new javax.swing.JLabel();
        jLabelVidaEquipo3 = new javax.swing.JLabel();
        jLabelManaEquipo3 = new javax.swing.JLabel();
        jLabelEnemigo4 = new javax.swing.JLabel();
        jLabelVidaEnemigo2 = new javax.swing.JLabel();
        jLabelEnemigo5 = new javax.swing.JLabel();
        jLabelVidaEnemigo5 = new javax.swing.JLabel();
        jLabelImagenTurno2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabelImagenTurno1 = new javax.swing.JLabel();
        jLabelFlecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelEquipo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEquipo1MouseClicked(evt);
            }
        });

        jLabelEquipo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEquipo3MouseClicked(evt);
            }
        });

        jLabelEquipo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEquipo2MouseClicked(evt);
            }
        });

        jLabelEnemigo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEnemigo2MouseClicked(evt);
            }
        });

        jLabelEnemigo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEnemigo3MouseClicked(evt);
            }
        });

        jLabelEnemigo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEnemigo1MouseClicked(evt);
            }
        });

        jButtonHab.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButtonHab.setText("Atacar");
        jButtonHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHabActionPerformed(evt);
            }
        });

        jButtonHab1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButtonHab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHab1ActionPerformed(evt);
            }
        });

        jButtonHab2.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButtonHab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHab2ActionPerformed(evt);
            }
        });

        jButtonHab3.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButtonHab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHab3ActionPerformed(evt);
            }
        });

        jButtonHab4.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButtonHab4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHab4ActionPerformed(evt);
            }
        });

        jTextAreaLog.setEditable(false);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jScrollPane1.setViewportView(jTextAreaLog);

        jLabelVidaEquipo1.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEquipo1.setForeground(new java.awt.Color(204, 0, 51));

        jLabelManaEquipo1.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelManaEquipo1.setForeground(new java.awt.Color(0, 0, 255));

        jLabelVidaEnemigo1.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEnemigo1.setForeground(new java.awt.Color(204, 0, 51));

        jLabelVidaEnemigo4.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEnemigo4.setForeground(new java.awt.Color(204, 0, 51));

        jLabelVidaEnemigo3.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEnemigo3.setForeground(new java.awt.Color(204, 0, 51));

        jLabelVidaEquipo2.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEquipo2.setForeground(new java.awt.Color(204, 0, 51));

        jLabelManaEquipo2.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelManaEquipo2.setForeground(new java.awt.Color(0, 0, 255));

        jLabelVidaEquipo3.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEquipo3.setForeground(new java.awt.Color(204, 0, 51));

        jLabelManaEquipo3.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelManaEquipo3.setForeground(new java.awt.Color(0, 0, 255));

        jLabelEnemigo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEnemigo4MouseClicked(evt);
            }
        });

        jLabelVidaEnemigo2.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEnemigo2.setForeground(new java.awt.Color(204, 0, 51));

        jLabelEnemigo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEnemigo5MouseClicked(evt);
            }
        });

        jLabelVidaEnemigo5.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelVidaEnemigo5.setForeground(new java.awt.Color(204, 0, 51));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Orden de ataques:");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelVidaEquipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelManaEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelVidaEquipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelManaEquipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelEquipo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelEquipo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelVidaEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelManaEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEnemigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelVidaEnemigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelVidaEnemigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelEnemigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEnemigo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(272, 272, 272)
                                .addComponent(jLabelVidaEnemigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelVidaEnemigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27))
                                    .addComponent(jLabelEnemigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEnemigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelVidaEnemigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonHab2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonHab4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonHab1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonHab3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonHab, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelImagenTurno1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFlecha, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelImagenTurno2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabelEnemigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelVidaEnemigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEnemigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEnemigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelVidaEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelManaEquipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelVidaEnemigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabelEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVidaEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelManaEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabelEquipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabelEnemigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelVidaEnemigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelEnemigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelVidaEnemigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVidaEquipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelManaEquipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVidaEnemigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonHab3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonHab1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonHab2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jButtonHab4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonHab, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelImagenTurno2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(jLabelImagenTurno1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelFlecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHabActionPerformed
        // TODO add your handling code here:
        turnos();
    }//GEN-LAST:event_jButtonHabActionPerformed

    private void jLabelEquipo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEquipo1MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipo, 0);
        listaLabelEquipo.get(0).setBorder(bordeRojo);


    }//GEN-LAST:event_jLabelEquipo1MouseClicked

    private void jLabelEquipo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEquipo2MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipo, 1);
        listaLabelEquipo.get(1).setBorder(bordeRojo);


    }//GEN-LAST:event_jLabelEquipo2MouseClicked

    private void jLabelEquipo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEquipo3MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipo, 2);
        listaLabelEquipo.get(2).setBorder(bordeRojo);


    }//GEN-LAST:event_jLabelEquipo3MouseClicked

    private void jLabelEnemigo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEnemigo1MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipoEnemigo, 0);
        listaLabelEnemigo.get(0).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelEnemigo1MouseClicked

    private void jLabelEnemigo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEnemigo2MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipoEnemigo, 1);
        listaLabelEnemigo.get(1).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelEnemigo2MouseClicked

    private void jLabelEnemigo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEnemigo3MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipoEnemigo, 2);
        listaLabelEnemigo.get(2).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelEnemigo3MouseClicked

    private void jButtonHab4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHab4ActionPerformed
        // TODO add your handling code here:
        numHab = 3;
        for (int i = 0; i < botonesHabilidades.size(); i++) {
            botonesHabilidades.get(i).setBackground(Color.white);
        }
        botonesHabilidades.get(numHab).setBackground(Color.red);
    }//GEN-LAST:event_jButtonHab4ActionPerformed

    private void jButtonHab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHab1ActionPerformed
        // TODO add your handling code here:
        numHab = 0;
        for (int i = 0; i < botonesHabilidades.size(); i++) {
            botonesHabilidades.get(i).setBackground(Color.white);
        }
        botonesHabilidades.get(numHab).setBackground(Color.red);
    }//GEN-LAST:event_jButtonHab1ActionPerformed

    private void jButtonHab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHab3ActionPerformed
        // TODO add your handling code here:
        numHab = 2;
        for (int i = 0; i < botonesHabilidades.size(); i++) {
            botonesHabilidades.get(i).setBackground(Color.white);
        }
        botonesHabilidades.get(numHab).setBackground(Color.red);

    }//GEN-LAST:event_jButtonHab3ActionPerformed

    private void jButtonHab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHab2ActionPerformed
        // TODO add your handling code here:
        numHab = 1;
        for (int i = 0; i < botonesHabilidades.size(); i++) {
            botonesHabilidades.get(i).setBackground(Color.white);
        }
        botonesHabilidades.get(numHab).setBackground(Color.red);
    }//GEN-LAST:event_jButtonHab2ActionPerformed

    private void jLabelEnemigo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEnemigo4MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipoEnemigo, 3);
        listaLabelEnemigo.get(3).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelEnemigo4MouseClicked

    private void jLabelEnemigo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEnemigo5MouseClicked
        // TODO add your handling code here:
        clicarObjetivo(equipoEnemigo, 4);
        listaLabelEnemigo.get(4).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelEnemigo5MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHab;
    private javax.swing.JButton jButtonHab1;
    private javax.swing.JButton jButtonHab2;
    private javax.swing.JButton jButtonHab3;
    private javax.swing.JButton jButtonHab4;
    private javax.swing.JLabel jLabelEnemigo1;
    private javax.swing.JLabel jLabelEnemigo2;
    private javax.swing.JLabel jLabelEnemigo3;
    private javax.swing.JLabel jLabelEnemigo4;
    private javax.swing.JLabel jLabelEnemigo5;
    private javax.swing.JLabel jLabelEquipo1;
    private javax.swing.JLabel jLabelEquipo2;
    private javax.swing.JLabel jLabelEquipo3;
    private javax.swing.JLabel jLabelFlecha;
    private javax.swing.JLabel jLabelImagenTurno1;
    private javax.swing.JLabel jLabelImagenTurno2;
    private javax.swing.JLabel jLabelManaEquipo1;
    private javax.swing.JLabel jLabelManaEquipo2;
    private javax.swing.JLabel jLabelManaEquipo3;
    private javax.swing.JLabel jLabelVidaEnemigo1;
    private javax.swing.JLabel jLabelVidaEnemigo2;
    private javax.swing.JLabel jLabelVidaEnemigo3;
    private javax.swing.JLabel jLabelVidaEnemigo4;
    private javax.swing.JLabel jLabelVidaEnemigo5;
    private javax.swing.JLabel jLabelVidaEquipo1;
    private javax.swing.JLabel jLabelVidaEquipo2;
    private javax.swing.JLabel jLabelVidaEquipo3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
