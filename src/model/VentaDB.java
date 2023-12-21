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
import java.time.LocalDate;

import static model.DBConn.rs;
import static model.DBConn.stmt;

/**
 *
 * @author maste
 */
public non-sealed class VentaDB extends DBConn{
    public static String obtenerUltimoId() {
        String ultimoIdVenta = null;

        try {
            String sql = "SELECT MAX(id_venta) AS ultimo_id FROM tb_venta";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        ultimoIdVenta = rs.getString("ultimo_id");
                    }
                }
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return ultimoIdVenta;
    }

    public static void insertar(Venta venta) {
        try {
            String sql = "INSERT INTO tb_venta (id_venta, id_cliente, id_vendedor, fecha_venta, sub_totaL_venta, igv_venta, descuento_venta, total_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            handleSQLException(sqle);
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
            handleSQLException(sqle);
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
            handleSQLException(sqle);
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
            handleSQLException(sqle);
        }

        // Convierte la lista a un array de String
        return idsProductos.toArray(String[]::new);
    }    

    public static List<Venta> obtenerVentasPorVendedor(String idVendedor) {
        List<Venta> ventas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TB_VENTA WHERE id_vendedor = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idVendedor);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String idVenta = rs.getString("id_venta");
                        LocalDate fechaVenta = rs.getDate("fecha_venta").toLocalDate();
                        String idCliente = rs.getString("id_cliente");
                        double subTotal = rs.getDouble("sub_total_venta");
                        double igv = rs.getDouble("igv_venta");
                        double descuento = rs.getDouble("descuento_venta");
                        double total = rs.getDouble("total_venta");

                        // Crear una instancia de Venta con los datos obtenidos
                        Venta venta = new Venta(idVenta, fechaVenta, idCliente, idVendedor, subTotal, igv, descuento, total);
                        ventas.add(venta);
                    }
                }
            }

        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return ventas;
    }

    public static List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TB_VENTA";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String idVenta = rs.getString("id_venta");
                    LocalDate fechaVenta = rs.getObject("fecha_venta", LocalDate.class);
                    String idCliente = rs.getString("id_cliente");
                    String idVendedor = rs.getString("id_vendedor");
                    double subTotal = rs.getDouble("sub_total_venta");
                    double igv = rs.getDouble("igv_venta");
                    double descuento = rs.getDouble("descuento_venta");
                    double total = rs.getDouble("total_venta");

                    // Crear una instancia de Venta con los datos obtenidos
                    Venta venta = new Venta(idVenta, fechaVenta, idCliente, idVendedor, subTotal, igv, descuento, total);
                    ventas.add(venta);
                }
            }

        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return ventas;
    }
    
    public static List<Venta> obtenerVentasPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Venta> ventas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM TB_VENTA WHERE fecha_venta BETWEEN ? AND ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
                pstmt.setDate(2, java.sql.Date.valueOf(fechaFin));

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String idVenta = rs.getString("id_venta");
                        LocalDate fechaVenta = rs.getObject("fecha_venta", LocalDate.class);
                        String idCliente = rs.getString("id_cliente");
                        String idVendedor = rs.getString("id_vendedor");
                        double subTotal = rs.getDouble("sub_total_venta");
                        double igv = rs.getDouble("igv_venta");
                        double descuento = rs.getDouble("descuento_venta");
                        double total = rs.getDouble("total_venta");

                        // Crear una instancia de Venta con los datos obtenidos
                        Venta venta = new Venta(idVenta, fechaVenta, idCliente, idVendedor, subTotal, igv, descuento, total);
                        ventas.add(venta);
                    }
                }
            }

        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return ventas;
    }
}
