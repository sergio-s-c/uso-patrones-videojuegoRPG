/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Personaje.AbstractPersonajeBuilder;
import Personaje.CreadorPersonaje;
import Personaje.CuranderoBuilder;
import Personaje.EscuderoBuilder;
import Personaje.HechiceroBuilder;
import Personaje.Personaje;
import Personaje.PicaroBuilder;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class SelectorPersonajes extends javax.swing.JFrame {

    /**
     * Creates new form SelectorPersonajes
     */
    private MainMenu menu;
    private String nombreUsuario;
    private Random rand = new Random();
    private ArrayList<String> personajesPosibles;
    private ArrayList<JLabel> listaLabelPersonajes;
    private ArrayList<JTextField> listaNombresPersonajes;
    private ArrayList<String> personajesAleatorios;
    private ArrayList<Personaje> equipo;
    private ImageIcon image;
    private Icon icono;

    private Personaje personaje;
    private CreadorPersonaje creador = new CreadorPersonaje();
    private int tamanoEquipoMax = 3;

    /**
     * Constructor
     *
     * @param menu los datos del anterior menu
     * @param nombre el nombre del usuario
     * @throws IOException
     */
    public SelectorPersonajes(MainMenu menu, String nombre) throws IOException {
        initComponents();
        this.menu = menu;
        this.nombreUsuario = nombre;
        listaLabelPersonajes = new ArrayList<>(List.of(jLabelImagen1, jLabelImagen2));

        listaNombresPersonajes = new ArrayList<>(List.of(jTextFieldNombre1, jTextFieldNombre2));

        personajesPosibles = new ArrayList<>(List.of("Curandero", "Escudero", "Hechicero", "Picaro"));

        equipo = new ArrayList<>();

        actualizarImagenes();

    }

    /**
     * Actualiza las imágenes de la ventana
     *
     * @throws IOException
     */
    private void actualizarImagenes() throws IOException {
        personajesAleatorios = new ArrayList<String>();
        for (int i = 0; i < listaLabelPersonajes.size(); i++) {

            int aleatorio = rand.nextInt(personajesPosibles.size());

            switch (personajesPosibles.get(aleatorio)) {
                case ("Curandero"):
                    image = new ImageIcon(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/curandero.png")));
                    break;

                case ("Escudero"):
                    image = new ImageIcon(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/escudero.png")));
                    break;
                case ("Hechicero"):
                    image = new ImageIcon(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/mago.png")));
                    break;

                case ("Picaro"):
                    image = new ImageIcon(ImageIO.read(new File("../TierraDesolada/imagenes/personajes/picaro.png")));
                    break;

            }
            icono = new ImageIcon(image.getImage().getScaledInstance(jLabelImagen1.getWidth(), jLabelImagen1.getHeight(), Image.SCALE_DEFAULT));

            listaLabelPersonajes.get(i).setIcon(icono);
            listaNombresPersonajes.get(i).setText(personajesPosibles.get(aleatorio));
            personajesAleatorios.add(personajesPosibles.get(aleatorio));

        }

    }

    /**
     * Constructor crea un curandero
     */
    private void anadirCurandero() {
        AbstractPersonajeBuilder curandero = new CuranderoBuilder();
        creador.setPersonajeBuilder(curandero);
        creador.disenarPersonaje();
        personaje = creador.getPersonaje();
        equipo.add(personaje);
        JOptionPane.showMessageDialog(this, "Se ha añadido un curandero a tu equipo");
    }

    /**
     * Constructor crea un escudero
     */
    private void anadirEscudero() {
        AbstractPersonajeBuilder escudero = new EscuderoBuilder();
        creador.setPersonajeBuilder(escudero);
        creador.disenarPersonaje();
        personaje = creador.getPersonaje();
        equipo.add(personaje);
        JOptionPane.showMessageDialog(this, "Se ha añadido un escudero a tu equipo");
    }

    /**
     * Constructor crea un hechicero
     */
    private void anadirHechicero() {
        AbstractPersonajeBuilder hechicero = new HechiceroBuilder();
        creador.setPersonajeBuilder(hechicero);
        creador.disenarPersonaje();
        personaje = creador.getPersonaje();
        equipo.add(personaje);
        JOptionPane.showMessageDialog(this, "Se ha añadido un hechicero a tu equipo");
    }

    /**
     * Constructor crea un picaro
     */
    private void anadirPicaro() {
        AbstractPersonajeBuilder picaro = new PicaroBuilder();
        creador.setPersonajeBuilder(picaro);
        creador.disenarPersonaje();
        personaje = creador.getPersonaje();
        equipo.add(personaje);
        JOptionPane.showMessageDialog(this, "Se ha añadido un picaro a tu equipo");
    }

    /**
     * Se añade el personaje al equipo
     *
     * @param i la casilla seleccionada en la ventana
     */

    private void anadirPersonaje(int i) throws IOException {
        switch (personajesAleatorios.get(i)) {
            case ("Curandero"):
                anadirCurandero();
                break;

            case ("Escudero"):
                anadirEscudero();
                break;

            case ("Hechicero"):
                anadirHechicero();
                break;

            case ("Picaro"):
                anadirPicaro();
                break;

        }
        if (this.equipo.size() < tamanoEquipoMax) {
            actualizarImagenes();
        } else {
            JOptionPane.showMessageDialog(this, "Tu equipo ha sido creado correctamente");
            Partida partida = new Partida(menu, nombreUsuario, equipo);
            this.dispose();
            this.setVisible(false);
            partida.setVisible(true);
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

        jLabelImagen1 = new javax.swing.JLabel();
        jLabelImagen2 = new javax.swing.JLabel();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jTextFieldNombre2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelImagen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImagen1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelImagen1MousePressed(evt);
            }
        });

        jLabelImagen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImagen2MouseClicked(evt);
            }
        });

        jTextFieldNombre1.setEditable(false);
        jTextFieldNombre1.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jTextFieldNombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldNombre2.setEditable(false);
        jTextFieldNombre2.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jTextFieldNombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elige los 3  personajes que conformarán tu equipo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jTextFieldNombre1))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelImagen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImagen1MouseClicked
        //llama a añadir personaje de la casilla 0
        try {
            // TODO add your handling code here:
            anadirPersonaje(0);

        } catch (IOException ex) {

        }
    }//GEN-LAST:event_jLabelImagen1MouseClicked

    private void jLabelImagen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImagen2MouseClicked
        //llama a añadir personaje de la casilla 1
        try {
            // TODO add your handling code here:
            anadirPersonaje(1);

        } catch (IOException ex) {
            Logger.getLogger(SelectorPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabelImagen2MouseClicked

    private void jLabelImagen1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImagen1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelImagen1MousePressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImagen1;
    private javax.swing.JLabel jLabelImagen2;
    private javax.swing.JTextField jTextFieldNombre1;
    private javax.swing.JTextField jTextFieldNombre2;
    // End of variables declaration//GEN-END:variables
}
