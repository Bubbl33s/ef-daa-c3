/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReporteInventarioDB extends DBConn {

    public static void generarReporteInventario() {
        try {
            LocalDate fechaActual = LocalDate.now();

            // Insertar entrada en la tabla TB_REPORTE_INVENTARIO
            String sqlInsertReporte = "INSERT INTO TB_REPORTE_INVENTARIO (fecha_reporte_inventario) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertReporte)) {
                pstmt.setDate(1, java.sql.Date.valueOf(fechaActual));
                pstmt.executeUpdate();
            }

        } catch (SQLException sqle) {
            
        }
    }

    public static String obtenerUltimoIdReporteInventario() {
        String ultimoId = null;

        try {
            String sql = "SELECT MAX(id_reporte_inventario) AS ultimo_id FROM TB_REPORTE_INVENTARIO";
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

