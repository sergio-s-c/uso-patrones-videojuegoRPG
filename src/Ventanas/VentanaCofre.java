/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Evento.Botin;
import Evento.BotinComun;
import Evento.GranBotin;
import Objetos.Consumible;
import Objetos.Equipamiento;
import Personaje.Personaje;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class VentanaCofre extends javax.swing.JFrame {

    /**
     * Creates new form JFrameCofre
     */
    private ArrayList<Consumible> inventario;
    private ArrayList<Equipamiento> equipamiento;
    private ArrayList<Personaje> equipo;
    private Partida partida;
    private int numClic;
    private String texto;

    /**
     * Constructor
     *
     * @param inventario el inventario del jugador
     * @param listaEquipamiento el equipamiento que tiene el jugador
     * @param equipo el equipo del usuario
     * @param partida los datos de la partida
     */
    public VentanaCofre(ArrayList<Consumible> inventario, ArrayList<Equipamiento> listaEquipamiento, ArrayList<Personaje> equipo, Partida partida) {
        initComponents();
        this.inventario = inventario;
        this.equipamiento = listaEquipamiento;
        this.equipo = equipo;
        this.partida = partida;
        numClic = 0;

        ImageIcon image = new ImageIcon("../TierraDesolada/imagenes/eventos/cofreCerrado.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(jLabelCofre.getWidth(), jLabelCofre.getHeight(), Image.SCALE_DEFAULT));
        jLabelCofre.setIcon(icono);
        texto = "";
    }

    /**
     * Devuelve si el botin es un botín comun o un gran botín
     *
     * @return el botin generado
     */
    private Botin generarBotin() {
        Random rand = new Random();
        Botin botin;
        if (rand.nextInt(100) < 90) {
            botin = new BotinComun();
            texto = "Es un botín común. " + "\n";
        } else {
            botin = new GranBotin();
            texto = "Es un gran botín. " + "\n";
        }
        return botin;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCofre = new javax.swing.JLabel();
        jLabelTexto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelCofre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCofreMouseClicked(evt);
            }
        });

        jLabelTexto.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabelTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabelCofre, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCofre, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCofreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCofreMouseClicked
        // Con 1 clic crea los consumibles y equipamiento y los añade al inventario y lista de equipamiento, con el segundo clic te lleva a partida

        if (numClic == 0) {
            ImageIcon image = new ImageIcon("../TierraDesolada/imagenes/eventos/cofreAbierto.png");
            Icon icono = new ImageIcon(image.getImage().getScaledInstance(jLabelCofre.getWidth(), jLabelCofre.getHeight(), Image.SCALE_DEFAULT));
            jLabelCofre.setIcon(icono);
            Botin botin = generarBotin();
            ArrayList<Consumible> consumibles;
            ArrayList<Equipamiento> equipamientos;
            consumibles = botin.GenerarConsumibles();
            equipamientos = botin.GenerarEquipamiento();
            //("Has conseguido: " );

            texto += "Has conseguido consumibles: ";
            for (int i = 0; i < consumibles.size(); i++) {
                inventario.add(consumibles.get(i));
                texto += consumibles.get(i).getNombre() + ", ";

            }
            texto += "\n" + " y equipamiento: ";
            for (int i = 0; i < equipamientos.size(); i++) {
                Random rand = new Random();
                equipamiento.add(equipamientos.get(i));
                texto += equipamientos.get(i).getNombre() + " ";

            }
            jLabelTexto.setText(texto);
            numClic++;
        } else {

            this.setVisible(false);
            partida.getMapa().setEventosRealizados(partida.getMapa().getEventosRealizados() + 1);
            try {
                partida.actualizar();

            } catch (IOException ex) {
                Logger.getLogger(VentanaCofre.class.getName()).log(Level.SEVERE, null, ex);
            }

            partida.setVisible(true);
            this.dispose();
        }


    }//GEN-LAST:event_jLabelCofreMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCofre;
    private javax.swing.JLabel jLabelTexto;
    // End of variables declaration//GEN-END:variables
}
