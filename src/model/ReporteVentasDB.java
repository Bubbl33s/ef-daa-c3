/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDate;
import static model.DBConn.stmt;

/**
 *
 * @author maste
 */
public class ReporteVentasDB extends DBConn {
    
    public static void generarReporteVentas(String tipoReporte) {
        try {
            LocalDate fechaActual = LocalDate.now();
            String sql = "INSERT INTO TB_REPORTE_VENTAS (tipo_reporte_ventas, fecha_reporte) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tipoReporte);
                pstmt.setDate(2, java.sql.Date.valueOf(fechaActual));
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            // Manejar la excepción según sea necesario
        }
    }

    public static String obtenerUltimoIdReporteVentas() {
        String ultimoId = null;

        try {
            String sql = "SELECT MAX(id_reporte_ventas) AS ultimo_id FROM TB_REPORTE_VENTAS";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    ultimoId = rs.getString("ultimo_id");
                }
            }

        } catch (SQLException sqle) {
            
        }

        return ultimoId;
    }
}
