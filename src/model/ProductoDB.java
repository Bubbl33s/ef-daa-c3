/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

import controller.Producto;

/**
 *
 * @author maste
 */
public non-sealed class ProductoDB extends DBConn {
    public static void listar() {
        try {
            rs = stmt.executeQuery("SELECT * FROM tb_producto");

            while (rs.next()) {
                System.out.println(rs.getString("id_producto") + " - " +
                        rs.getString("descripcion_producto") + " - S/. " +
                        rs.getDouble("precio_producto") + " - " +
                        rs.getInt("stock_producto"));
            }

            rs.close();
        }
        catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }
    
    public static String[] obtenerIds() {
        List<String> idsProductos = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT id_producto FROM tb_producto");

            while (rs.next()) {
                idsProductos.add(rs.getString("id_producto"));
            }

            rs.close();
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return idsProductos.toArray(String[]::new);
    }

    public static double obtenerPrecio(String idProducto) {
        double precio = 0.0;

        try {
            String sql = "SELECT precio_producto FROM tb_producto WHERE id_producto = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idProducto);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        precio = resultSet.getDouble("precio_producto");
                    }
                }
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return precio;
    }

    public static void insertarProducto(Producto producto) {
        try {
            String sql = "INSERT INTO tb_producto (descripcion_producto, precio_producto, stock_producto) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, producto.getDescripcion());
                pstmt.setDouble(2, producto.getPrecio());
                pstmt.setInt(3, producto.getStock());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    public static Producto obtenerProductoPorId(String idProducto) {
        Producto producto = null;
        try {
            String sql = "SELECT id_producto, descripcion_producto, precio_producto, stock_producto FROM tb_producto WHERE id_producto = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idProducto);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String id = rs.getString("id_producto");
                        String descripcion = rs.getString("descripcion_producto");
                        double precio = rs.getDouble("precio_producto");
                        int stock = rs.getInt("stock_producto");
                        producto = new Producto(id, descripcion, precio, stock);
                    }
                }
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
        return producto;
    }

    public static void actualizarProducto(Producto producto) {
        try {
            String sql = "UPDATE tb_producto SET descripcion_producto = ?, precio_producto = ?, stock_producto = ? WHERE id_producto = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, producto.getDescripcion());
                pstmt.setDouble(2, producto.getPrecio());
                pstmt.setInt(3, producto.getStock());
                pstmt.setString(4, producto.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static void eliminarProducto(String idProducto) {
        try {
            String sql = "DELETE FROM tb_producto WHERE id_producto = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idProducto);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }
    
    public static void actualizarStockProducto(String idProducto, int cantidadVendida) {
        try {
            String sql = "{CALL actualizar_stock_producto(?, ?)}";
            
            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setString(1, idProducto);
                cstmt.setInt(2, cantidadVendida);

                cstmt.execute();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }
    
    public static List<Producto> obtenerProductosYCantidades() {
        List<Producto> productosConCantidades = new ArrayList<>();

        try {
            String sql = "SELECT id_producto, descripcion_producto, precio_producto, stock_producto FROM TB_PRODUCTO";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String idProducto = rs.getString("id_producto");
                    String descripcion = rs.getString("descripcion_producto");
                    double precio = rs.getDouble("precio_producto");
                    int stock = rs.getInt("stock_producto");

                    Producto producto = new Producto(idProducto, descripcion, precio, stock);
                    productosConCantidades.add(producto);
                }
            }

        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return productosConCantidades;
    }
}
