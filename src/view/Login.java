/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.VendedorDB;
import controller.Vendedor;

/**
 *
 * @author maste
 */
public class Login {
    static Scanner scanner = new Scanner(System.in);
    
    public static void ejecutarLogin() {
        String user, password;
        
        System.out.println("VENTAS EFDAA");
        System.out.println("INICIO DE SESIÓN VENDEDORES");
        System.out.println("============\n");
        
        System.out.print("Usuario: ");
        user = scanner.next();
        System.out.print("Contraseña: ");
        password = scanner.next();
        
        System.out.println();
        
        if (validarCredenciales(user, password)) {
            MenuPrincipal menuPrincipal = new MenuPrincipal(VendedorDB.obtenerVendedor(user));
            menuPrincipal.mostrarMenu();
        }
    }
    
    public static boolean validarCredenciales(String inputUser, String inputPassword) {
        if (VendedorDB.validarUsuario(inputUser)) {
            if (inputPassword.equals(VendedorDB.obtenerContra(inputUser))) {
                System.out.println("Credenciales válidas");
                System.out.println("====================");
                return true;
            }
            else {
                System.out.println("Contraseña incorrecta");
            }
        }
        else {
            System.out.println("Usuario incorrecto");
        }
        
        return false;
    }
}
