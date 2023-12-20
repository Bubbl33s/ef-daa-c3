/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.Venta;
import java.util.ArrayList;
import java.util.List;
import static model.DBConn.rs;
import static model.DBConn.stmt;

/**
 *
 * @author maste
 */
public class VentaDB extends DBConn{
    public static String obtenerUltimoId() {
        String ultimoIdVenta = null;

        try {
            // Consulta para obtener el último ID de la tabla tb_venta
            String sql = "SELECT MAX(id_venta) AS ultimo_id FROM tb_venta";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Ejecutar la consulta
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        ultimoIdVenta = rs.getString("ultimo_id");
                    }
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        return ultimoIdVenta;
    }

    public static void insertar(Venta venta) {
        try {
            // Consulta SQL para insertar una venta
            String sql = "INSERT INTO tb_venta (id_venta, id_cliente, id_vendedor, fecha_venta, sub_totaL_venta, igv_venta, descuento_venta, total_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            // Preparar la consulta
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecer los parámetros
                pstmt.setString(1, venta.getId());
                pstmt.setString(2, venta.getIdCliente());
                pstmt.setString(3, venta.getIdVendedor());
                pstmt.setDate(4, java.sql.Date.valueOf(venta.getFecha())); // Convierte LocalDate a java.sql.Date
                pstmt.setDouble(5, venta.getSubTotal());
                pstmt.setDouble(6, venta.getIgv());
                pstmt.setDouble(7, venta.getDescuento());
                pstmt.setDouble(8, venta.getTotal());

                // Ejecutar la consulta
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    public static void listar() {
        try {
            rs = stmt.executeQuery("SELECT * FROM tb_venta");

            while (rs.next()) {
                System.out.println(rs.getString("id_venta") + " - " +
                        rs.getString("id_cliente") + " - " +
                        rs.getString("id_vendedor") + " - " +
                        rs.getDate("fecha_venta") + " - S/. " +
                        rs.getDouble("sub_total_venta") + " - S/. " +
                        rs.getDouble("igv_venta") + " - S/. " +
                        rs.getDouble("descuento_venta") + "% - s/. " +
                        rs.getDouble("total_venta"));
            }

            rs.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    public static void eliminarVenta(String idVenta) {
        try {
            String sql = "DELETE FROM tb_venta WHERE id_venta = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idVenta);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    public static String[] obtenerIds() {
        List<String> idsProductos = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT id_venta FROM tb_venta");

            while (rs.next()) {
                idsProductos.add(rs.getString("id_venta"));
            }

            rs.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        // Convierte la lista a un array de String
        return idsProductos.toArray(String[]::new);
    }    
}
