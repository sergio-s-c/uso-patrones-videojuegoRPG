/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validar;

/**
 *
 * @author Sergio Sánchez y David Ramos
 */
public class Usuario implements InterfaceUsuario {

    private String contrasena;
    private String nombre;

    /**
     *
     * @param contra la contraseña
     * @param nombre el nombre de usuario
     */
    public Usuario(String contra, String nombre) {
        this.contrasena = contra;
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
     *Verifica si los datos introucidos son correctos
     * @return devuelve true si son correctos
     */
    public boolean accederJuego() {
        ListaUsuarios lu = new ListaUsuarios();
        lu.cargarDatos();

        return (lu.getLista().containsKey(this.contrasena) && (lu.getLista().get(nombre)).equals(contrasena));
    }

}
