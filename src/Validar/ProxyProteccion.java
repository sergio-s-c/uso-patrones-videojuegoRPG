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
public class ProxyProteccion implements InterfaceUsuario{

    private Usuario usuario;
    private ListaUsuarios lu;

    /**
     *constructor
     * @param contra la contraseña
     * @param nombre el nombre de usuario
     */
    public ProxyProteccion(String contra,String nombre) {
        this.usuario= new Usuario(contra,nombre);
        lu = new ListaUsuarios();
    }

    /**
     *Verifica si los datos introucidos son correctos
     * @return devuelve true si son correctos
     */
    public boolean accederJuego() {
        
        lu.cargarDatos();

   
        return ( lu.getLista().containsKey(this.usuario.getNombre()) &&(lu.getLista().get(this.usuario.getNombre())).equals(this.usuario.getContrasena())); 
            
        
    }
}
