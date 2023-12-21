/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.ClienteDB;
import controller.Cliente;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author maste
 */

public class GestionarCliente {
    static Scanner scanner = new Scanner(System.in);

    public static void iniciar() {
        int opcion;

        do {
            System.out.println("\n********** GESTIÓN DE CLIENTES **********");
            System.out.println("1. Mostrar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarClientes();
                case 2 -> agregarCliente();
                case 3 -> actualizarCliente();
                case 4 -> eliminarCliente();
                case 5 -> System.out.println("Saliendo de la gestión de clientes...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public static void mostrarClientes() {
        System.out.println("""
                           ID Cliente | Nombre | Apellidos | Teléfono | Email
                           ==========================================
                           """);
        ClienteDB.listar();
    }

    private static void agregarCliente() {
        System.out.println("\nAGREGAR NUEVO CLIENTE");

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

        String telefono;
        do {
            System.out.print("Teléfono: ");
            telefono = scanner.nextLine();
        } while (telefono.isEmpty());

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
        } while (email.isEmpty());

        Cliente nuevoCliente = new Cliente(apellidos, nombres, telefono, email);
        ClienteDB.insertarCliente(nuevoCliente);

        System.out.println("\nCliente agregado exitosamente.");
    }

    private static void actualizarCliente() {
        System.out.println("\nACTUALIZAR CLIENTE");

        String idCliente;
        do {
            System.out.print("ID del cliente a actualizar: ");
            idCliente = scanner.next().toUpperCase();
        } while (!Arrays.asList(ClienteDB.obtenerIds()).contains(idCliente));

        scanner.nextLine();
 
        String nuevosApellidos;
        do {
            System.out.print("Nuevos apellidos: ");
            nuevosApellidos = scanner.nextLine();
        } while (nuevosApellidos.isEmpty());

        
        String nuevosNombres;
        do {
            System.out.print("Nuevos nombres: ");
            nuevosNombres = scanner.nextLine();
        } while (nuevosNombres.isEmpty());

        String nuevoTelefono;
        do {
            System.out.print("Nuevo teléfono: ");
            nuevoTelefono = scanner.nextLine();
        } while (nuevoTelefono.isEmpty());

        String nuevoEmail;
        do {
            System.out.print("Nuevo email: ");
            nuevoEmail = scanner.nextLine();
        } while (nuevoEmail.isEmpty());

        Cliente clienteActualizado = new Cliente(nuevosApellidos, nuevosNombres, nuevoTelefono, nuevoEmail);
        ClienteDB.actualizarCliente(clienteActualizado);

        System.out.println("\nCliente actualizado exitosamente.");
    }

    private static void eliminarCliente() {
        System.out.println("\nELIMINAR CLIENTE");

        String idCliente;
        do {
            System.out.print("ID del cliente a eliminar: ");
            idCliente = scanner.next().toUpperCase();
        } while (!Arrays.asList(ClienteDB.obtenerIds()).contains(idCliente));

        ClienteDB.eliminarCliente(idCliente);

        System.out.println("\nCliente eliminado exitosamente.");
    }
}
