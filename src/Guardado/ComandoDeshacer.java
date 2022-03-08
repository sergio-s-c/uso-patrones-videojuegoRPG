/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import java.io.IOException;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public interface ComandoDeshacer extends Comando{

    /**
     *
     * @throws IOException
     */
    public void deshacer()throws IOException;
    
}
