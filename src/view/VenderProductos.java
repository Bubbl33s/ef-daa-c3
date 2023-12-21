/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.*;

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
    static final double IGVPORCENTAJE = 0.18;
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
        
        String[] idClientes = ClienteDB.obtenerIds();
        
        do {
            System.out.print("\nCódigo del cliente: ");
            idCliente = scanner.next().toUpperCase();
        }
        while (!Arrays.asList(idClientes).contains(idCliente));
    }
            
    public static void mostrarProductos() {
        System.out.println("\nPRODUCTOS DISPONIBLES:\n");
        ProductoDB.listar();
        
        Character continuar;
        
        String[] idProductos = ProductoDB.obtenerIds();
        
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
            
            // TODO: VALIDAR STOCK DISPONIBLE
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
        
        Character desc;
        System.out.print("Ingrese 's' para aplicar descuento: ");
        desc = scanner.next().toLowerCase().charAt(0);
        
        if (desc == 's') {
            System.out.print("Ingrese descuento (%): ");
            descuento = scanner.nextDouble();
        }
        
        double igv = subTotal*IGVPORCENTAJE;
        
        System.out.println("\nDETALLES DE LA COMPRA\n");
        System.out.printf("Sub total: S/ %.2f\n", subTotal);
        System.out.printf("IGV (18 %%): S/. %.2f\n", igv);
        System.out.printf("Descuento: %.2f%%\n", descuento);
        System.out.printf("TOTAL: S/. %.2f\n", subTotal + igv - descuento);
        
        Character finalizar;
        
        System.out.print("\n's' para finalizar venta, otra opción la cancelará: ");
        finalizar = scanner.next().toLowerCase().charAt(0);
        
        if (finalizar == 's') {
            generarVenta();
        }
        else {
            System.out.println("\nVENTA CANCELADA\n");
        }
    }
    
    private static void generarVenta() {
        LocalDate fecha = LocalDate.now();

        Venta venta = new Venta(fecha, idCliente, idVendedor, subTotal, descuento);
        VentaDB.insertar(venta);
        String idVenta = VentaDB.obtenerUltimoId();

        for (DetalleVenta detalle : detallesVenta) {
            detalle.setIdVenta(idVenta);
            DetalleVentaDB.insertar(detalle);
            ProductoDB.actualizarStockProducto(detalle.getIdProducto(), detalle.getCantidadVendida());
        }
        
        Reportes.generarComprobante(idVenta);
        
        System.out.println("\nGRACIAS POR SU COMPRA!\n");
    }
}
