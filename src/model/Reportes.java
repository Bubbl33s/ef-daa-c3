/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maste
 */
public class Reportes extends DBConn {
    public static void generarComprobante(String idVenta) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ComprobantePago.jasper";
            HashMap param = new HashMap();
            param.put("paramIdVenta", idVenta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            String pathPDF = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\REPORTES PDF\\Comprobante Pago\\Comprobante-" + idVenta + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF);
            System.out.println("\nComprobante de pago generado correctamente");
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el comprobante: " + jre.getMessage());
        }
    }
    
    public static void reporteVentasFecha(String idReporte, LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ReporteVentasFecha.jasper";
            HashMap param = new HashMap();
            param.put("paramIdReporte", idReporte);
            param.put("paramFechaInicio", fechaInicio);
            param.put("paramFechaFin", fechaFin);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            String pathPDF = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\REPORTES PDF\\Reporte Ventas Fecha\\ReporteVentasFecha-" + idReporte + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF);
            System.out.println("\nReporte de Ventas por Fecha generado correctamente");
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el Reporte: " + jre.getMessage());
        }
    }
    
    public static void reporteVentasGeneral(String idReporte) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ReporteVentasGeneral.jasper";
            HashMap param = new HashMap();
            param.put("paramIdReporte", idReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            String pathPDF = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\REPORTES PDF\\Reporte Ventas General\\ReporteVentasGeneral-" + idReporte + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF);
            System.out.println("\nReporte de Ventas General generado correctamente");
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el Reporte: " + jre.getMessage());
        }
    }
    
    public static void reporteVentasVendedor(String idVendedor, String idReporte) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ReporteVentasVendedor.jasper";
            HashMap param = new HashMap();
            param.put("paramIdVendedor", idVendedor);
            param.put("paramIdReporte", idReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            String pathPDF = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\REPORTES PDF\\Reporte Ventas Vendedor\\ReporteVentasVendedor-" + idReporte + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF);
            System.out.println("\nReporte de Ventas por Vendedor generado correctamente");
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el Reporte: " + jre.getMessage());
        }
    }
    
    public static void reporteInventario(String idReporte) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ReporteInventario.jasper";
            HashMap param = new HashMap();
            param.put("paramIdReporte", idReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            String pathPDF = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\REPORTES PDF\\Reporte Inventario\\ReporteInventario-" + idReporte + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF);
            System.out.println("\nReporte de Inventario generado correctamente");
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el Reporte: " + jre.getMessage());
        }
    }
}
