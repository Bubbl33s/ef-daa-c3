/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.VentaDB;
import model.DetalleVentaDB;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author maste
 */
public class AnularComprobante {
    static Scanner scanner = new Scanner(System.in);

    public static void iniciar() {
        int opcion;

        do {
            System.out.println("\n********** ANULACIÓN DE COMPROBANTES **********");
            System.out.println("1. Mostrar Comprobantes");
            System.out.println("2. Anular Comprobante");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();
            
            System.out.println();

            switch (opcion) {
                case 1 -> mostrarComprobantes();
                case 2 -> anularComprobante();
                case 3 -> System.out.println("Saliendo de la anulación de comprobantes...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 3);
        
        System.out.println("Volviendo al menú principal...\n");
    }
    
    private static void mostrarComprobantes() {
        System.out.println("""
                           ID Comprobante | ID Cliente | ID Vendedor | Fecha | Sub Total | IGV | Descuento | Total
                           =======================================================================================
                           """);
        VentaDB.listar();
    }
    
    private static void anularComprobante() {
        String idComprobante;
        
        String[] idComprobantes = VentaDB.obtenerIds();
        
        do {
            System.out.print("ID del comprobante a anular: ");
            idComprobante = scanner.next().toUpperCase();
        }
        while (!Arrays.asList(idComprobantes).contains(idComprobante));
        
        DetalleVentaDB.eliminarDetalleVenta(idComprobante);
        VentaDB.eliminarVenta(idComprobante);
        System.out.println("\nCOMPROBANTE ANULADO EXITOSAMENTE\n");
    }
}
