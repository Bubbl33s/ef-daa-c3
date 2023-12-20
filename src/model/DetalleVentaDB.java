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
public class DetalleVentaDB extends DBConn {
    public static void insertar(DetalleVenta detalleVenta) {
        try {
            // Consulta SQL para insertar un detalle de venta
            String sql = "INSERT INTO tb_detalle_venta (id_venta, id_producto, precio_unitario_actual, cantidad_vendida, total_producto_venta) VALUES (?, ?, ?, ?, ?)";
            
            // Preparar la consulta
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecer los parámetros
                pstmt.setString(1, detalleVenta.getIdVenta());
                pstmt.setString(2, detalleVenta.getIdProducto());
                pstmt.setDouble(3, detalleVenta.getPrecioUnitarioActual());
                pstmt.setInt(4, detalleVenta.getCantidadVendida());
                pstmt.setDouble(5, detalleVenta.getTotalProducto());

                // Ejecutar la consulta
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }
}
