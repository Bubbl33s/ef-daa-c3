/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.ProductoDB;
import model.ClienteDB;
import model.VentaDB;
import model.DetalleVentaDB;

import controller.DetalleVenta;
import controller.Venta;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author maste
 */
public class VenderProductos {
    static Scanner scanner = new Scanner(System.in);
    static String idVendedor;
    static String idCliente;
    static double subTotal;
    static double descuento;
    // Lista para almacenar los detalles de venta
    static List<DetalleVenta> detallesVenta = new ArrayList<>();
    
    public static void iniciarVenta(String idVen) {
        subTotal = 0;
        descuento = 0;
        idVendedor = idVen;
        mostrarClientes();
        mostrarProductos();
    }
    
    public static void mostrarClientes() {
        System.out.println("\nSELECCIONE UN CLIENTE:\n");
        ClienteDB.listar();
        
        // Obtener codigos
        System.out.print("\nCódigo del cliente: ");
        idCliente = scanner.next().toUpperCase();
        // TODO: ERROR HANDLING
    }
            
    public static void mostrarProductos() {
        // TODO: ELEGIR CLIENTE
        
        System.out.println("\nPRODUCTOS DISPONIBLES:\n");
        ProductoDB.listar();
        
        Character continuar;
        
        String[] idProductos = ProductoDB.obtenerIds();
        
        // TODO: INSERTAR PRODUCTOS
        do {
            String idProducto;
            double precio;
            int cantidad;
            double total;
            
            do {
                System.out.print("\nCódigo del producto: ");
                idProducto = scanner.next().toUpperCase();
            }
            while (!Arrays.asList(idProductos).contains(idProducto));
            
            System.out.print("Cantidad: ");
            cantidad = scanner.nextInt();
            
            precio = ProductoDB.obtenerPrecio(idProducto);
            total = cantidad * precio;
            subTotal += total;
            
            DetalleVenta detalle = new DetalleVenta("", idProducto, precio, cantidad, total);
            detallesVenta.add(detalle);
            
            System.out.printf("%s agregado\n\n", idProducto);
            System.out.print("Ingrese 's' para continuar: ");
            continuar = scanner.next().toLowerCase().charAt(0);
        }
        while (continuar == 's'); 
        
        LocalDate fecha = LocalDate.now();
        
        // TODO: GENERAR VENTA
        Venta venta = new Venta(fecha, idCliente, idVendedor, subTotal, descuento);
        VentaDB.insertar(venta);
        String idVenta = VentaDB.obtenerUltimoId();
        
        // TODO: GENERAR DETALLE VENTA (COMPROBANTE DE PAGO)
        // Insertar detalles de venta en la base de datos
        for (DetalleVenta detalle : detallesVenta) {
            detalle.setIdVenta(idVenta);
            DetalleVentaDB.insertar(detalle);
        }
        
        System.out.println("\nGRACIAS POR SU COMPRA!\n");
    }
}
