/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class CombateJefe extends Evento{

    /**
     *Constructor de CombateJefe
     * @throws IOException
     */
    public CombateJefe() throws IOException {
        imagenEvento=ImageIO.read(new File("../TierraDesolada/imagenes/eventos/combateJefe.png"));
        nombre="Combate jefe";
    }
    
}
