/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DetalleReporteInventario;

public class DetalleReporteInventarioDB extends DBConn {

    public static void insertarDetalleReporteInventario(DetalleReporteInventario detalle) {
        try {
            String sql = "INSERT INTO TB_DETALLE_REP_INVENTARIO (id_reporte_inventario, id_producto, cantidad_actual_producto) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, detalle.getIdReporteInventario());
                pstmt.setString(2, detalle.getIdProducto());
                pstmt.setInt(3, detalle.getCantidadActualProducto());
                pstmt.executeUpdate();
            }

        } catch (SQLException sqle) {
            //handleSQLException(sqle);
        }
    } 
}
