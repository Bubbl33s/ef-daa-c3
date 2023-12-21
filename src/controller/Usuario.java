/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maste
 */
public abstract class Usuario {
    protected String user;
    protected String password;
    
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    abstract void verCredenciales();
}
