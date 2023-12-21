/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maste
 */

// CLASE SOLO HECHA PARA EL POLIMORFISMO
public class Admin extends Usuario {
    private String id;
    private String apellidos;
    private String nombres;
    
    public Admin(String user, String password, String id, String apellidos, String nombres) {
        super(user, password);
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
    }
    
    @Override
    void verCredenciales() {
        System.out.println("\nCredenciales de Administrador:\n");
        System.out.printf("Usuario: %s\n", this.user);
        System.out.printf("Contraseña: %s\n", this.password);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    
}
