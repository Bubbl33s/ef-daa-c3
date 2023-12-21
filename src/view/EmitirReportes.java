/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.*;
import controller.Producto;
import controller.Venta;
import controller.DetalleReporteInventario;
import controller.DetalleReporteVentas;   
import java.util.Arrays;

import java.util.Scanner;
import java.util.List;


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
        String idVendedor;
        
        String[] idVendedores = VendedorDB.obtenerIds();
        
        // Obtener codigos
        do {
            System.out.print("\nCódigo de vendedor: ");
            idVendedor = scanner.next().toUpperCase();
        }
        while (!Arrays.asList(idVendedores).contains(idVendedor));
        
        ReporteVentasDB.generarReporteVentas("V");
        
        String idReporteVentas = ReporteVentasDB.obtenerUltimoIdReporteVentas();
        
        List<Venta> ventasPorVendedor = VentaDB.obtenerVentasPorVendedor(idVendedor);
        
        for (Venta venta : ventasPorVendedor) {
            DetalleReporteVentas detalle = new DetalleReporteVentas(idReporteVentas, venta.getId());
            DetalleReporteVentasDB.insertarDetalleReporteVentas(detalle);
        }
        
        Reportes.reporteVentasVendedor(idVendedor, idReporteVentas);
    }

    private static void reporteInventario() {
        ReporteInventarioDB.generarReporteInventario();

        // Obtener el último ID del reporte de inventario
        String idReporteInventario = ReporteInventarioDB.obtenerUltimoIdReporteInventario();

        // Obtener productos y cantidades actuales
        List<Producto> productosConCantidades = ProductoDB.obtenerProductosYCantidades();

        // Insertar detalles en la tabla TB_DETALLE_REP_INVENTARIO
        for (Producto producto : productosConCantidades) {
            DetalleReporteInventario detalle = new DetalleReporteInventario(idReporteInventario,
                    producto.getId(), producto.getStock());

            DetalleReporteInventarioDB.insertarDetalleReporteInventario(detalle);
        }
        
        // Generar PDF
        Reportes.reporteInventario(idReporteInventario);
    }
}