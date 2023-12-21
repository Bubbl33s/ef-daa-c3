/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Reportes;

import java.util.Scanner;

public class EmitirReportes {
    static Scanner scanner = new Scanner(System.in);

    public static void iniciar() {
        int opcion;

        do {
            System.out.println("\n********** EMISIÓN DE REPORTES **********");
            System.out.println("1. Reporte de Ventas por Fechas");
            System.out.println("2. Reporte General de Ventas");
            System.out.println("3. Reporte de Ventas por Vendedor");
            System.out.println("4. Reporte de Inventario");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> reporteVentasPorFechas();
                case 2 -> reporteGeneralVentas();
                case 3 -> reporteVentasPorVendedor();
                case 4 -> reporteInventario();
                case 5 -> System.out.println("Saliendo de la emisión de reportes...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }

    private static void reporteVentasPorFechas() {
        // Lógica para generar reporte de ventas por fechas
    }

    private static void reporteGeneralVentas() {
        // Lógica para generar reporte general de ventas
    }

    private static void reporteVentasPorVendedor() {
        // Lógica para generar reporte de ventas por vendedor
    }

    private static void reporteInventario() {
        // Lógica para generar reporte de inventario
    }
}