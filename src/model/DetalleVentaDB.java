/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DetalleVenta;

/**
 *
 * @author maste
 */
public non-sealed class DetalleVentaDB extends DBConn {
    public static void insertar(DetalleVenta detalleVenta) {
        try {
            String sql = "INSERT INTO tb_detalle_venta (id_venta, id_producto, precio_unitario_actual, cantidad_vendida, total_producto_venta) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, detalleVenta.getIdVenta());
                pstmt.setString(2, detalleVenta.getIdProducto());
                pstmt.setDouble(3, detalleVenta.getPrecioUnitarioActual());
                pstmt.setInt(4, detalleVenta.getCantidadVendida());
                pstmt.setDouble(5, detalleVenta.getTotalProducto());

                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }
    
    public static void eliminarDetalleVenta(String idVenta) {
        try {
            String sql = "DELETE FROM tb_detalle_venta WHERE id_venta = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idVenta);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }  
}
