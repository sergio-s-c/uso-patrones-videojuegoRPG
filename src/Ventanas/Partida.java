/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Guardado.Originador;
import IteratorEquipamientos.AgregadoConcretoEq;
import IteratorEquipamientos.AgregadoEq;
import IteratorEquipamientos.IteradorEq;
import IteratorConsumible.Agregado;
import IteratorConsumible.AgregadoConcreto;
import IteratorConsumible.Iterador;
import Evento.Evento;
import Mapa.Mapa;
import Objetos.Consumible;
import Objetos.Equipamiento;
import Personaje.Personaje;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Partida extends javax.swing.JFrame {

    /**
     * Creates new form Partida
     */
    private boolean inventarioVisible = false;
    private boolean equipamientoVisible = false;
    private ArrayList<Personaje> equipoEnemigo;
    private ArrayList<Personaje> equipo;
    private ArrayList<Consumible> inventario;
    private ArrayList<Equipamiento> listaEquipamiento;
    private Mapa mapa;
    private ArrayList<Evento> listaEventos;
    private ImageIcon image;
    private Icon icono;
    private ArrayList<JLabel> listaLabelEventos;
    private ArrayList<JLabel> listaLabelRetrato;
    private VentanaCombate combate;
    private Random rand;
    private Originador originador;
    private Personaje objetivo;
    private ArrayList<JLabel> listaLabelVida;
    private ArrayList<JLabel> listaLabelMana;
    private Border bordeRojo = BorderFactory.createLineBorder(Color.red);
    private boolean victoria = false;
    private MainMenu menu;
    private String nombreUsuario;

    /**
     *
     * @param menu los datos del MainMenu
     * @param nombre el nombre del usuario
     * @param equi el equipo
     * @throws IOException
     */
    public Partida(MainMenu menu, String nombre, ArrayList<Personaje> equi) throws IOException {
        initComponents();
        listaLabelVida = new ArrayList<>(List.of(jLabelSalud1, jLabelSalud2, jLabelSalud3));
        listaLabelMana = new ArrayList<>(List.of(jLabelMana1, jLabelMana2, jLabelMana3));
        jComboBoxEquipamiento.setVisible(inventarioVisible);
        jComboBoxConsumible.setVisible(equipamientoVisible);
        this.menu = menu;
        this.nombreUsuario = nombre;
        rand = new Random();
        mapa = new Mapa();
        equipoEnemigo = new ArrayList<Personaje>();

        inventario = new ArrayList<Consumible>();
        listaEquipamiento = new ArrayList<Equipamiento>();
        this.equipo = new ArrayList<Personaje>();
        this.equipo = equi;
        this.objetivo = equipo.get(0);
        jLabelRetrato1.setBorder(bordeRojo);

        listaLabelRetrato = new ArrayList<JLabel>(List.of(jLabelRetrato1, jLabelRetrato2, jLabelRetrato3));
        listaLabelEventos = new ArrayList<JLabel>(List.of(jLabelEvento1, jLabelEvento2, jLabelEvento3));
        jTextFieldNombreUsuario.setText(nombre);
        actualizar();

    }

    /**
     * carga una partida a partir de los datos de una partida guardada
     *
     * @param p el estado la partida cargada
     * @throws IOException
     */
    public Partida(Partida p) throws IOException {
        initComponents();
        jComboBoxEquipamiento.setVisible(inventarioVisible);
        listaLabelVida = new ArrayList<>(List.of(jLabelSalud1, jLabelSalud2, jLabelSalud3));
        listaLabelMana = new ArrayList<>(List.of(jLabelMana1, jLabelMana2, jLabelMana3));
        this.menu = p.getMenu();
        rand = new Random();
        this.mapa = p.mapa;
        equipoEnemigo = new ArrayList<Personaje>();
        this.inventario = p.getInventario();
        this.listaEquipamiento = p.getListaEquipamiento();
        this.equipo = p.equipo;
        this.objetivo = p.objetivo;
        this.nombreUsuario = p.nombreUsuario;
        listaLabelRetrato = new ArrayList<JLabel>(List.of(jLabelRetrato1, jLabelRetrato2, jLabelRetrato3));
        listaLabelEventos = new ArrayList<JLabel>(List.of(jLabelEvento1, jLabelEvento2, jLabelEvento3));
        this.listaEventos = p.listaEventos;
        jTextFieldNumEvento.setText("Has superado " + mapa.getEventosRealizados() + " eventos" + "---" + mapa.getEstado().getNombre());

        actualizarImagenes();

    }

    /**
     *
     * @return
     */
    public ArrayList<Equipamiento> getListaEquipamiento() {
        return listaEquipamiento;
    }

    /**
     *
     * @param listaEquipamiento
     */
    public void setListaEquipamiento(ArrayList<Equipamiento> listaEquipamiento) {
        this.listaEquipamiento = listaEquipamiento;
    }

    /**
     *
     * @return
     */
    public String getNombreUsusario() {
        return nombreUsuario;
    }

    /**
     *
     * @param nombreUsusario
     */
    public void setNombreUsusario(String nombreUsusario) {
        this.nombreUsuario = nombreUsusario;
    }

    /**
     *
     * @param equipo
     */
    public void setEquipo(ArrayList<Personaje> equipo) {
        this.equipo = equipo;
    }

    /**
     *
     * @return
     */
    public ArrayList<Personaje> getEquipo() {
        return equipo;
    }

    /**
     *
     * @param mapa
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     *
     * @return
     */
    public MainMenu getMenu() {
        return this.menu;
    }

    /**
     *
     * @return
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     *
     * @return
     */
    public ArrayList<Consumible> getInventario() {
        return inventario;
    }

    /**
     *
     * @param inventario
     */
    public void setInventario(ArrayList<Consumible> inventario) {
        this.inventario = inventario;
    }

    /**
     * Actualiza el titulo y crea los nuevos eventos y llama a actualizar
     * imágenes
     *
     * @throws IOException
     */
    public void actualizar() throws IOException {

        jTextFieldNumEvento.setText("Has superado " + mapa.getEventosRealizados() + " eventos" + "---" + mapa.getEstado().getNombre());
        listaEventos = mapa.getEstado().crearEventos(mapa);
        actualizarImagenes();

    }

    /**
     * Actualiza las imágenes de la ventana y llama a actualizar inventario y
     * salud
     *
     * @throws IOException
     */

    private void actualizarImagenes() throws IOException {
        for (int i = 0; i < listaEventos.size(); i++) {
            image = new ImageIcon(listaEventos.get(i).getImagenEvento());

            icono = new ImageIcon(image.getImage().getScaledInstance(listaLabelEventos.get(i).getWidth(), listaLabelEventos.get(i).getHeight(), Image.SCALE_DEFAULT));
            listaLabelEventos.get(i).setIcon(icono);
        }
        for (int i = 0; i < equipo.size(); i++) {
            image = new ImageIcon(equipo.get(i).getImagenPersonaje());

            icono = new ImageIcon(image.getImage().getScaledInstance(listaLabelRetrato.get(i).getWidth(), listaLabelRetrato.get(i).getHeight(), Image.SCALE_DEFAULT));
            listaLabelRetrato.get(i).setIcon(icono);
            if (equipo.size() < listaLabelRetrato.size()) {
                image = new ImageIcon("../TierraDesolada/imagenes/personajes/Calavera.png");

                icono = new ImageIcon(image.getImage().getScaledInstance(listaLabelRetrato.get(i).getWidth(), listaLabelRetrato.get(i).getHeight(), Image.SCALE_DEFAULT));

                listaLabelRetrato.get(2).setIcon(icono);
                listaLabelMana.get(2).setText(null);
                listaLabelVida.get(2).setText(null);
            }
            if (equipo.size() < listaLabelRetrato.size() - 1) {
                image = new ImageIcon("../TierraDesolada/imagenes/personajes/Calavera.png");

                icono = new ImageIcon(image.getImage().getScaledInstance(listaLabelRetrato.get(i).getWidth(), listaLabelRetrato.get(i).getHeight(), Image.SCALE_DEFAULT));

                listaLabelRetrato.get(1).setIcon(icono);
                listaLabelMana.get(1).setText(null);
                listaLabelVida.get(1).setText(null);
            }
        }
        actualizarInventario();
        actualizarSalud();
    }

    /**
     * Actualiza los jcomboBox de consumibles y equipamiento
     *
     * @throws IOException
     */

    private void actualizarInventario() {
        jComboBoxEquipamiento.removeAllItems();
        jComboBoxConsumible.removeAllItems();
        AgregadoEq agregadoEq = new AgregadoConcretoEq(listaEquipamiento);

        IteradorEq iteradorEq = agregadoEq.crearIterador();

        // Recorre la lista con el iterador.
        while (iteradorEq.hayMas()) {

            Equipamiento eq = (Equipamiento) iteradorEq.elementoActual();
            jComboBoxEquipamiento.addItem(eq.getNombre());

            iteradorEq.siguiente();

        }

        Agregado agregado = new AgregadoConcreto(inventario);

        Iterador iterador = agregado.crearIterador();

        // Recorre la lista con el iterador.
        while (iterador.hayMas()) {

            Consumible cons = (Consumible) iterador.elementoActual();
            jComboBoxConsumible.addItem(cons.getNombre());

            iterador.siguiente();

        }
    }
/**
     *Actualiza la salud de los personajes
   
     */
    private void actualizarSalud() {
        for (int i = 0; i < equipo.size(); i++) {
            listaLabelVida.get(i).setText(Integer.toString(equipo.get(i).getSalud()) + "/" + Integer.toString(equipo.get(i).getVidaMaxima()));
            listaLabelMana.get(i).setText(Integer.toString(equipo.get(i).getMana()) + "/" + Integer.toString(equipo.get(i).getManaMaximo()));
        }
    }
/**
     *Llama al evento seleccionado
     */
    private void llamarEvento(String nombre) {
        if (victoria) {

        }
        if (nombre == "Zona invisible") {

            int num = rand.nextInt(4);

            switch (num) {
                case 0:
                    nombre = "Combate normal";
                    break;
                case 1:
                    nombre = "Zona de Descanso";
                    break;
                case 2:
                    nombre = "Combate Élite";
                    break;
                case 3:
                    nombre = "Cofre";
                    break;
            }

        }
        if (nombre == "Cofre") {
            VentanaCofre cofre = new VentanaCofre(inventario, listaEquipamiento, equipo, this);
            this.setVisible(false);
            cofre.setVisible(true);
        } else if (nombre == "Combate normal") {

            try {
                equipoEnemigo = mapa.getEstado().generarEnemigos();
            } catch (IOException ex) {

            }

            combate = new VentanaCombate(mapa, equipo, equipoEnemigo, this);
            this.setVisible(false);
            combate.setVisible(true);
        } else if (nombre == "Zona de Descanso") {

            VentanaZonaDescanso descanso = new VentanaZonaDescanso(equipo, this);
            this.setVisible(false);
            descanso.setVisible(true);
        } else if (nombre == "Combate élite") {
            try {
                equipoEnemigo = mapa.getEstado().generarElites();
            } catch (IOException ex) {

            }

            combate = new VentanaCombate(mapa, equipo, equipoEnemigo, this);
            this.setVisible(false);
            combate.setVisible(true);
        } else if (nombre == "Combate jefe") {
            try {
                equipoEnemigo = mapa.getEstado().generarEnemigos();
            } catch (IOException ex) {

            }
            victoria = true;
            combate = new VentanaCombate(mapa, equipo, equipoEnemigo, this);
            this.setVisible(false);
            combate.setVisible(true);

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

        jLabelEvento1 = new javax.swing.JLabel();
        jLabelEvento2 = new javax.swing.JLabel();
        jLabelEvento3 = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonInventario = new javax.swing.JButton();
        jButtonRendirse = new javax.swing.JButton();
        jLabelRetrato1 = new javax.swing.JLabel();
        jLabelRetrato3 = new javax.swing.JLabel();
        jLabelRetrato2 = new javax.swing.JLabel();
        jLabelVida1 = new javax.swing.JLabel();
        jLabelVida2 = new javax.swing.JLabel();
        jLabelVida3 = new javax.swing.JLabel();
        jTextFieldNumEvento = new javax.swing.JTextField();
        jButtonVolver = new javax.swing.JButton();
        jButtonCargar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextFieldNombreUsuario = new javax.swing.JTextField();
        jButtonCambiarNombre = new javax.swing.JButton();
        jButtonReestablecerNombre = new javax.swing.JButton();
        jComboBoxEquipamiento = new javax.swing.JComboBox<>();
        jButtonEquipar = new javax.swing.JButton();
        jLabelSalud1 = new javax.swing.JLabel();
        jLabelSalud2 = new javax.swing.JLabel();
        jLabelSalud3 = new javax.swing.JLabel();
        jLabelMana1 = new javax.swing.JLabel();
        jLabelMana2 = new javax.swing.JLabel();
        jLabelMana3 = new javax.swing.JLabel();
        jButtonInventarioEquipamiento = new javax.swing.JButton();
        jButtonUsarConsumible = new javax.swing.JButton();
        jComboBoxConsumible = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelEvento1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jLabelEvento1ComponentAdded(evt);
            }
        });
        jLabelEvento1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEvento1MouseClicked(evt);
            }
        });

        jLabelEvento2.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jLabelEvento2ComponentAdded(evt);
            }
        });
        jLabelEvento2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEvento2MouseClicked(evt);
            }
        });

        jLabelEvento3.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jLabelEvento3ComponentAdded(evt);
            }
        });
        jLabelEvento3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEvento3MouseClicked(evt);
            }
        });

        jButtonGuardar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseClicked(evt);
            }
        });
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonInventario.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonInventario.setText("Ver/Ocultar Consumibles");
        jButtonInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInventarioActionPerformed(evt);
            }
        });

        jButtonRendirse.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonRendirse.setText("Rendirse");
        jButtonRendirse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRendirseMouseClicked(evt);
            }
        });
        jButtonRendirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRendirseActionPerformed(evt);
            }
        });

        jLabelRetrato1.setPreferredSize(new java.awt.Dimension(20, 20));
        jLabelRetrato1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRetrato1MouseClicked(evt);
            }
        });

        jLabelRetrato3.setPreferredSize(new java.awt.Dimension(20, 20));
        jLabelRetrato3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRetrato3MouseClicked(evt);
            }
        });

        jLabelRetrato2.setPreferredSize(new java.awt.Dimension(20, 20));
        jLabelRetrato2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRetrato2MouseClicked(evt);
            }
        });

        jTextFieldNumEvento.setEditable(false);
        jTextFieldNumEvento.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N

        jButtonVolver.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonVolver.setText("Volver");
        jButtonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVolverMouseClicked(evt);
            }
        });

        jButtonCargar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonCargar.setText("Sobreescribir último guardado");
        jButtonCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCargarMouseClicked(evt);
            }
        });
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jTextField1.setText("Usuario:");

        jTextFieldNombreUsuario.setEditable(false);
        jTextFieldNombreUsuario.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N

        jButtonCambiarNombre.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonCambiarNombre.setText("Cambiar nombre usuario");
        jButtonCambiarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCambiarNombreActionPerformed(evt);
            }
        });

        jButtonReestablecerNombre.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonReestablecerNombre.setText("Recuperar antiguo nombre de usuario");
        jButtonReestablecerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReestablecerNombreActionPerformed(evt);
            }
        });

        jComboBoxEquipamiento.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jComboBoxEquipamiento.setMaximumRowCount(50);
        jComboBoxEquipamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBoxEquipamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEquipamientoActionPerformed(evt);
            }
        });

        jButtonEquipar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonEquipar.setText("Equipar");
        jButtonEquipar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEquiparActionPerformed(evt);
            }
        });

        jLabelSalud1.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelSalud1.setForeground(new java.awt.Color(204, 0, 51));

        jLabelSalud2.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelSalud2.setForeground(new java.awt.Color(204, 0, 51));

        jLabelSalud3.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelSalud3.setForeground(new java.awt.Color(204, 0, 51));

        jLabelMana1.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelMana1.setForeground(new java.awt.Color(0, 0, 255));

        jLabelMana2.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelMana2.setForeground(new java.awt.Color(0, 0, 255));

        jLabelMana3.setFont(new java.awt.Font("Rockwell", 1, 11)); // NOI18N
        jLabelMana3.setForeground(new java.awt.Color(0, 0, 255));

        jButtonInventarioEquipamiento.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonInventarioEquipamiento.setText("Ver/Ocultar Equipamiento");
        jButtonInventarioEquipamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInventarioEquipamientoActionPerformed(evt);
            }
        });

        jButtonUsarConsumible.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jButtonUsarConsumible.setText("Usar Consumible");
        jButtonUsarConsumible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsarConsumibleActionPerformed(evt);
            }
        });

        jComboBoxConsumible.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jComboBoxConsumible.setMaximumRowCount(50);
        jComboBoxConsumible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBoxConsumible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsumibleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelVida1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelRetrato1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelSalud1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jLabelMana1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelRetrato2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelMana2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabelSalud2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelRetrato3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelSalud3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(jLabelMana3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(208, 208, 208))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelVida2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabelVida3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabelEvento1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabelEvento2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabelEvento3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNumEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEquipar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonInventarioEquipamiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButtonUsarConsumible)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxConsumible, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButtonInventario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRendirse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCambiarNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonReestablecerNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addComponent(jButtonCargar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVolver, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRendirse, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEvento3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEvento2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEvento1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCambiarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReestablecerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonUsarConsumible, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxConsumible, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonInventarioEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEquipar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEquipamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRetrato1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRetrato3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRetrato2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSalud2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabelMana2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSalud1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabelMana1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSalud3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabelMana3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVida1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVida2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVida3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelEvento1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jLabelEvento1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEvento1ComponentAdded

    private void jLabelEvento2ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jLabelEvento2ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEvento2ComponentAdded

    private void jLabelEvento3ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jLabelEvento3ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEvento3ComponentAdded

    private void jLabelEvento1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEvento1MouseClicked
        // TODO add your handling code here:
//Llama al evento en la casilla 0
        String nombre = listaEventos.get(0).getNombre();
        llamarEvento(nombre);


    }//GEN-LAST:event_jLabelEvento1MouseClicked

    private void jLabelEvento2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEvento2MouseClicked
       //Llama al evento en la casilla 0
        if (listaEventos.size() > 1) {
            String nombre = listaEventos.get(1).getNombre();

            llamarEvento(nombre);
        }
    }//GEN-LAST:event_jLabelEvento2MouseClicked

    private void jLabelEvento3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEvento3MouseClicked
        // TODO add your handling code here:
        if (listaEventos.size() > 2) {
            String nombre = listaEventos.get(2).getNombre();

            llamarEvento(nombre);
        }
    }//GEN-LAST:event_jLabelEvento3MouseClicked

    private void jButtonRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRendirseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRendirseActionPerformed

    private void jButtonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseClicked
        //guarda el estado de la partida 
        menu.getGuardar().setPartida(this);
        menu.getGuardar().ejecutar();

        //originador= new Originador();
        //originador.setPartida(this);
        // try {
        //Memento guardado=originador.crearGuardado();
        //  } catch (IOException ex) {
        //    Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        //  }

    }//GEN-LAST:event_jButtonGuardarMouseClicked

    private void jButtonVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVolverMouseClicked
        //te manda al menú
        this.setVisible(false);

        menu.setVisible(true);

    }//GEN-LAST:event_jButtonVolverMouseClicked

    private void jButtonCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargarMouseClicked
        //Te carga el último guardado de partida
        try {
            // TODO add your handling code here:
            menu.getGuardar().deshacer();
        } catch (IOException ex) {
            Logger.getLogger(Partida.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);


    }//GEN-LAST:event_jButtonCargarMouseClicked

    private void jButtonCambiarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCambiarNombreActionPerformed
        //Te permite actualizar el nombre de usuario y te guarda el anterior nombre
        menu.getUsuario().setPartida(this);

        menu.getUsuario().ejecutar(this.nombreUsuario);
        JFrame jFrame = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jFrame, "Introduce tu nuevo usuario");
        nombreUsuario = getMessage;
        jTextFieldNombreUsuario.setText(nombreUsuario);


    }//GEN-LAST:event_jButtonCambiarNombreActionPerformed

    private void jButtonReestablecerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReestablecerNombreActionPerformed
        // Te carga el anterior nombre de usuario
        if (menu.getUsuario().getConserje().getNumMementos() > 0) {
            jTextFieldNombreUsuario.setText(menu.getUsuario().getConserje().obtenerMemento().getPartida().getNombreUsusario());

        }


    }//GEN-LAST:event_jButtonReestablecerNombreActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventarioActionPerformed
        // Te muestra el jcombobox de consumibles
        inventarioVisible = !inventarioVisible;
        jComboBoxConsumible.setVisible(inventarioVisible);


    }//GEN-LAST:event_jButtonInventarioActionPerformed

    private void jComboBoxEquipamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEquipamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEquipamientoActionPerformed

    private void jLabelRetrato1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRetrato1MouseClicked
        //Marca al personaje en la primera posición
        objetivo = equipo.get(0);
        for (int i = 0; i < listaLabelRetrato.size(); i++) {
            listaLabelRetrato.get(i).setBorder(null);
        }
        listaLabelRetrato.get(0).setBorder(bordeRojo);
    }//GEN-LAST:event_jLabelRetrato1MouseClicked

    private void jLabelRetrato2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRetrato2MouseClicked
         //Marca al personaje en la segunda posición
        if (equipo.size() > 1) {
            objetivo = equipo.get(1);
            for (int i = 0; i < listaLabelRetrato.size(); i++) {
                listaLabelRetrato.get(i).setBorder(null);
            }
            listaLabelRetrato.get(1).setBorder(bordeRojo);
        }

    }//GEN-LAST:event_jLabelRetrato2MouseClicked

    private void jLabelRetrato3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRetrato3MouseClicked
         //Marca al personaje en la tercera posición
        if (equipo.size() > 2) {
            objetivo = equipo.get(2);
            for (int i = 0; i < listaLabelRetrato.size(); i++) {
                listaLabelRetrato.get(i).setBorder(null);
            }
            listaLabelRetrato.get(2).setBorder(bordeRojo);
        }
    }//GEN-LAST:event_jLabelRetrato3MouseClicked

    private void jButtonEquiparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEquiparActionPerformed
        //Equipa el objeto marcado en la combobox al personaje seleccionado
        if (listaEquipamiento.size() > 0) {
            int numEq = jComboBoxEquipamiento.getSelectedIndex();
            if (numEq >= 0) {
                objetivo.anadirEquipamiento(listaEquipamiento.get(numEq));

                jComboBoxEquipamiento.removeItemAt(numEq);

                JOptionPane.showMessageDialog(this, "Se le ha equipado: " + listaEquipamiento.get(numEq).getNombre() + " al personaje: " + objetivo.getNombre());
                listaEquipamiento.remove(numEq);
                actualizarSalud();
            }
        }


    }//GEN-LAST:event_jButtonEquiparActionPerformed

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonRendirseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRendirseMouseClicked
        // Te lleva a la ventana de derrota
        Derrota derrota = new Derrota();
        this.setVisible(false);
        derrota.setVisible(true);
    }//GEN-LAST:event_jButtonRendirseMouseClicked

    private void jButtonInventarioEquipamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventarioEquipamientoActionPerformed
        // Te muestra el jcombobox con el equipamiento
        equipamientoVisible = !equipamientoVisible;
        jComboBoxEquipamiento.setVisible(equipamientoVisible);


    }//GEN-LAST:event_jButtonInventarioEquipamientoActionPerformed

    private void jButtonUsarConsumibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsarConsumibleActionPerformed
        //Equipa el equipamiento seleccionado al personaje marcado
        if (inventario.size() > 0) {
            int numCons = jComboBoxConsumible.getSelectedIndex();
            if (numCons >= 0) {
                inventario.get(numCons).usar(objetivo);
                jComboBoxConsumible.removeItemAt(numCons);

                JOptionPane.showMessageDialog(this, "Se ha usado el objeto " + inventario.get(numCons).getNombre() + " sobre " + objetivo.getNombre());
                inventario.remove(numCons);
                actualizarSalud();
            }
        }
    }//GEN-LAST:event_jButtonUsarConsumibleActionPerformed

    private void jComboBoxConsumibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsumibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxConsumibleActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCambiarNombre;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JButton jButtonEquipar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonInventario;
    private javax.swing.JButton jButtonInventarioEquipamiento;
    private javax.swing.JButton jButtonReestablecerNombre;
    private javax.swing.JButton jButtonRendirse;
    private javax.swing.JButton jButtonUsarConsumible;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxConsumible;
    private javax.swing.JComboBox<String> jComboBoxEquipamiento;
    private javax.swing.JLabel jLabelEvento1;
    private javax.swing.JLabel jLabelEvento2;
    private javax.swing.JLabel jLabelEvento3;
    private javax.swing.JLabel jLabelMana1;
    private javax.swing.JLabel jLabelMana2;
    private javax.swing.JLabel jLabelMana3;
    private javax.swing.JLabel jLabelRetrato1;
    private javax.swing.JLabel jLabelRetrato2;
    private javax.swing.JLabel jLabelRetrato3;
    private javax.swing.JLabel jLabelSalud1;
    private javax.swing.JLabel jLabelSalud2;
    private javax.swing.JLabel jLabelSalud3;
    private javax.swing.JLabel jLabelVida1;
    private javax.swing.JLabel jLabelVida2;
    private javax.swing.JLabel jLabelVida3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    private javax.swing.JTextField jTextFieldNumEvento;
    // End of variables declaration//GEN-END:variables
}
