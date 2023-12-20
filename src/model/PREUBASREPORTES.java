/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import static model.DBConn.conn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author maste
 */
public class PREUBASREPORTES {
    public static void main(String[] args) {
        generarComprobante("V0000001");
    }
    
    public static void generarComprobante(String idVenta) {
        try {
            String pathReporte = "C:\\Users\\maste\\JaspersoftWorkspace\\MyReports\\ComprobantePago.jasper";
            HashMap param = new HashMap();
            param.put("paramIdVenta", idVenta);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    pathReporte, param, conn);
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);

        } catch (JRException jre) {
            System.out.println("Error al generar el comprobante: " + jre.getMessage());
        }
    }
}
