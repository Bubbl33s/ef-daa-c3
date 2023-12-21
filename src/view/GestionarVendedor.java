/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Validador;
import controller.Vendedor;
import model.VendedorDB;

import java.util.Arrays;
import java.util.Scanner;

public class GestionarVendedor {
    static Scanner scanner = new Scanner(System.in);

    public static void iniciar() {
        int opcion;

        do {
            System.out.println("\n********** GESTIÓN DE VENDEDORES **********");
            System.out.println("1. Mostrar Vendedores");
            System.out.println("2. Agregar Vendedor");
            System.out.println("3. Actualizar Vendedor");
            System.out.println("4. Eliminar Vendedor");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarVendedores();
                case 2 -> agregarVendedor();
                case 3 -> actualizarVendedor();
                case 4 -> eliminarVendedor();
                case 5 -> System.out.println("Saliendo de la gestión de vendedores...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public static void mostrarVendedores() {
        System.out.print("""
                           ID Vendedor | Nombre | Apellidos | Usuario | Contraseña
                           =======================================================
                           """);
        VendedorDB.listarVendedores();
    }

    private static void agregarVendedor() {
        System.out.println("\nAGREGAR NUEVO VENDEDOR");

        scanner.nextLine();

        String nombres;
        do {
            System.out.print("Nombres: ");
            nombres = scanner.nextLine();
        } while (nombres.isEmpty());

        String apellidos;
        do {
            System.out.print("Apellidos: ");
            apellidos = scanner.nextLine();
        } while (apellidos.isEmpty());

        String user;
        do {
            System.out.print("Usuario: ");
            user = scanner.nextLine();
        } while (user.isEmpty());

        String password;
        do {
            System.out.print("Contraseña: ");
            password = scanner.nextLine();
        } while (password.isEmpty());

        Vendedor nuevoVendedor = new Vendedor(user, password, null, apellidos, nombres);
        VendedorDB.insertarVendedor(nuevoVendedor);

        System.out.println("\nVendedor agregado exitosamente.");
    }

    private static void actualizarVendedor() {
        System.out.println("\nACTUALIZAR VENDEDOR");

        String idVendedor;
        do {
            System.out.print("ID del vendedor a actualizar: ");
            idVendedor = scanner.next().toUpperCase();
        } while (!Arrays.asList(VendedorDB.obtenerIds()).contains(idVendedor));

        scanner.nextLine();

        String nuevosNombres;
        do {
            System.out.print("Nuevos nombres: ");
            nuevosNombres = scanner.nextLine();
        } while (nuevosNombres.isEmpty());

        String nuevosApellidos;
        do {
            System.out.print("Nuevos apellidos: ");
            nuevosApellidos = scanner.nextLine();
        } while (nuevosApellidos.isEmpty());

        String nuevoUser;
        do {
            System.out.print("Nuevo usuario: ");
            nuevoUser = scanner.nextLine();
        } while (nuevoUser.isEmpty());

        String nuevaPassword;
        do {
            System.out.print("Nueva contraseña: ");
            nuevaPassword = scanner.nextLine();
        } while (nuevaPassword.isEmpty());

        Vendedor vendedorActualizado = new Vendedor(nuevoUser, nuevaPassword, idVendedor, nuevosApellidos, nuevosNombres);
        VendedorDB.actualizarVendedor(vendedorActualizado);

        System.out.println("\nVendedor actualizado exitosamente.");
    }

    private static void eliminarVendedor() {
        System.out.println("\nELIMINAR VENDEDOR");

        String idVendedor;
        do {
            System.out.print("ID del vendedor a eliminar: ");
            idVendedor = scanner.next().toUpperCase();
        } while (!Arrays.asList(VendedorDB.obtenerIds()).contains(idVendedor));

        VendedorDB.eliminarVendedor(idVendedor);

        System.out.println("\nVendedor eliminado exitosamente.");
    }
}
