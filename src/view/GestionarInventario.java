/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.ProductoDB;
import controller.Producto;

import java.util.Arrays;
import java.util.Scanner;

import controller.Validador;

/**
 *
 * @author maste
 */
public class GestionarInventario {
    static Scanner scanner = new Scanner(System.in);
    static Validador validador = new Validador(scanner);
    
    public static void iniciar() {
        int opcion;

        do {
            System.out.println("\n********** GESTIÓN DE INVENTARIO **********");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Agregar Producto");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarInventario();
                case 2 -> agregarProducto();
                case 3 -> actualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> System.out.println("Saliendo de la gestión de inventario...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    public static void mostrarInventario() {
        System.out.println("""
                           ID Producto | Descripción | Precio | Stock
                           ==========================================
                           """);
        ProductoDB.listar();
    }

    private static void agregarProducto() {
        System.out.println("\nAGREGAR NUEVO PRODUCTO");

        scanner.nextLine();

        String descripcion;
        do {
            System.out.print("Descripción: ");
            descripcion = scanner.nextLine();
        } while (descripcion.isEmpty());

        double precio = validador.validarDouble("Precio: ");
        int stock = validador.validarEntero("Stock: ");

        Producto nuevoProducto = new Producto(descripcion, precio, stock);
        ProductoDB.insertarProducto(nuevoProducto);

        System.out.println("\nProducto agregado exitosamente.");
    }

    private static void actualizarProducto() {
        System.out.println("\nACTUALIZAR PRODUCTO");

        String idProducto;
        do {
            System.out.print("ID del producto a actualizar: ");
            idProducto = scanner.next().toUpperCase();
        } while (!Arrays.asList(ProductoDB.obtenerIds()).contains(idProducto));

        scanner.nextLine();

        String nuevaDescripcion;
        do {
            System.out.print("Nueva descripción: ");
            nuevaDescripcion = scanner.nextLine();
        } while (nuevaDescripcion.isEmpty());

        double nuevoPrecio = validador.validarDouble("Nuevo precio: ");
        int nuevoStock = validador.validarEntero("Nuevo stock: ");

        Producto productoActualizado = new Producto(idProducto, nuevaDescripcion, nuevoPrecio, nuevoStock);
        ProductoDB.actualizarProducto(productoActualizado);

        System.out.println("\nProducto actualizado exitosamente.");
    }

    private static void eliminarProducto() {
        System.out.println("\nELIMINAR PRODUCTO");

        String idProducto;
        do {
            System.out.print("ID del producto a eliminar: ");
            idProducto = scanner.next().toUpperCase();
        } while (!Arrays.asList(ProductoDB.obtenerIds()).contains(idProducto));

        ProductoDB.eliminarProducto(idProducto);

        System.out.println("\nProducto eliminado exitosamente.");
    }
}
