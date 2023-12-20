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

/**
 *
 * @author maste
 */
public class ProductoDB extends DBConn {
    public static void listar() {
        try {
            rs = stmt.executeQuery("SELECT id_producto, descripcion_producto, precio_producto FROM tb_producto");

            while (rs.next()) {
                System.out.println(rs.getString("id_producto") + " - " + rs.getString("descripcion_producto") + " - S/. " + rs.getDouble("precio_producto"));
            }

            rs.close();
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("ProductoError: " + sqle.getErrorCode());
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
            System.out.println("ProductoError: " + sqle.getErrorCode());
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
            System.out.println("ProductoError: " + sqle.getErrorCode());
        }

        return precio;
    }
}
