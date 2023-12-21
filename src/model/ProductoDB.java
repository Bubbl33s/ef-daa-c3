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
public class ProductoDB extends DBConn {
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
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
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
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        // Convierte la lista a un array de String
        return idsProductos.toArray(String[]::new);
    }

    public static double obtenerPrecio(String idProducto) {
        double precio = 0.0;

        try {
            String sql = "SELECT precio_producto FROM tb_producto WHERE id_producto = ?";

            // Preparar la consulta
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecer el parámetro
                pstmt.setString(1, idProducto);

                // Ejecutar la consulta
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    // Verificar si hay resultados
                    if (resultSet.next()) {
                        // Obtener el precio del resultado
                        precio = resultSet.getDouble("precio_producto");
                    }
                }
            }
        } catch (SQLException sqle) {
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        return precio;
    }

    // Método para insertar un nuevo producto
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

    // Método para obtener un producto por su ID
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
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
        return producto;
    }

    // Método para actualizar un producto
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
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    // Método para eliminar un producto por su ID
    public static void eliminarProducto(String idProducto) {
        try {
            String sql = "DELETE FROM tb_producto WHERE id_producto = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idProducto);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }
    
    public static void actualizarStockProducto(String idProducto, int cantidadVendida) {
        try {
            // Llamar al procedimiento almacenado
            String sql = "{CALL actualizar_stock_producto(?, ?)}";
            
            // Preparar la llamada al procedimiento almacenado
            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                // Establecer los parámetros
                cstmt.setString(1, idProducto);
                cstmt.setInt(2, cantidadVendida);

                // Ejecutar el procedimiento almacenado
                cstmt.execute();
            }
        } catch (SQLException sqle) {
            // Manejar o relanzar la excepción según sea necesario
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }
}
