/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.VendedorDB;

/**
 *
 * @author maste
 */
public class Login {
    static Scanner scanner = new Scanner(System.in);

    public static void ejecutarLogin() {
        String user, password;
        boolean valido = false;
        
        System.out.println("\nVENTAS EFDAA");
        System.out.println("INICIO DE SESIÓN VENDEDORES");
        System.out.println("===========================");

        do {
            System.out.print("\nUsuario: ");
            user = scanner.next();
            System.out.print("Contraseña: ");
            password = scanner.next();

            System.out.println();

            try {
                validarCredenciales(user, password);
                System.out.println("Credenciales válidas");
                System.out.println("====================");
                valido = true;
                break;
            } catch (CredencialesInvalidasException e) {
                System.out.println(e.getMessage());
                System.out.print("Ingrese 's' para volver a intentar: ");
                String respuesta = scanner.next().toLowerCase();
                if (!respuesta.equals("s")) {
                    System.out.println("Saliendo del inicio de sesión...");
                    break;
                }
            }

        } while (true);
        
        if (valido) {
            MenuPrincipal menuPrincipal = new MenuPrincipal(VendedorDB.obtenerVendedor(user));
            menuPrincipal.mostrarMenu();
        }
    }
    
    public static void validarCredenciales(String inputUser, String inputPassword) throws CredencialesInvalidasException {
        if (!VendedorDB.validarUsuario(inputUser)) {
            throw new CredencialesInvalidasException("Usuario incorrecto");
        }

        if (!inputPassword.equals(VendedorDB.obtenerContra(inputUser))) {
            throw new CredencialesInvalidasException("Contraseña incorrecta");
        }
    }
}
