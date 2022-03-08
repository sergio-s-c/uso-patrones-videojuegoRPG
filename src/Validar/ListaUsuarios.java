/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class ListaUsuarios implements Serializable {

    private static HashMap<String, String> lista;

    /**
     *Constructor
     */
    public ListaUsuarios() {
        this.lista = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public static HashMap<String, String> getLista() {
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(HashMap<String, String> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ListaUsuarios{" + "lista=" + lista + '}';
    }

    /**
     *Almacena los datos de usuarios y contraseñas en un txt ListaUsaurios
     */
    public static void guardarDatos() {
        try {
            if (!lista.isEmpty()) {
                try (FileOutputStream fos = new FileOutputStream("ListaUsuarios.txt")) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(lista);
                }
            } else {
                System.out.println("Error, lista vacia");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        }
    }

    /**
     *Carga los usuarios y contraseñas del txt ListaUsuarios
     */
    public static void cargarDatos() {
        try {
            try (FileInputStream fis = new FileInputStream("ListaUsuarios.txt")) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                lista = (HashMap) ois.readObject();
            }
        } catch (IOException ioe) {
            System.out.println("Error de IO " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase" + cnfe.getMessage());
        }
    }

}
