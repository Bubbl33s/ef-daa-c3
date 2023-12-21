/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DetalleReporteVentas;

/**
 *
 * @author maste
 */
public non-sealed class DetalleReporteVentasDB extends DBConn {
    public static void insertarDetalleReporteVentas(DetalleReporteVentas detalle) {
        try {
            String sql = "INSERT INTO TB_DETALLE_REP_VENTAS (id_reporte_ventas, id_venta) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, detalle.getIdReporteVentas());
                pstmt.setString(2, detalle.getIdVenta());
                pstmt.executeUpdate();
            }

        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    } 
}
