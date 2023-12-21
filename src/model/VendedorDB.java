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

import controller.Vendedor;

/**
 *
 * @author maste
 */
public class VendedorDB extends DBConn {
    public static boolean validarUsuario(String user) {
        boolean result = false;

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT 1 FROM tb_vendedor WHERE user_vendedor = ?")) {
            preparedStatement.setString(1, user);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                result = resultSet.next();
            }
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        return result;
    }

    public static String obtenerContra(String user) {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT password_vendedor FROM tb_vendedor WHERE user_vendedor = ?")) {
            pstmt.setString(1, user);
            
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password_vendedor");
                }
            }
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
        
        return "";
    }

    public static Vendedor obtenerVendedor(String user) {
        Vendedor vendedor = null;

        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_vendedor WHERE user_vendedor = ?")) {
            pstmt.setString(1, user);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String userVendedor = resultSet.getString("user_vendedor");
                    String passwordVendedor = resultSet.getString("password_vendedor");
                    String idVendedor = resultSet.getString("id_vendedor");
                    String apellidosVendedor = resultSet.getString("apellidos_vendedor");
                    String nombresVendedor = resultSet.getString("nombres_vendedor");

                    vendedor = new Vendedor(userVendedor, passwordVendedor, idVendedor, apellidosVendedor, nombresVendedor);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

        return vendedor;
    }
    
    public static void listarVendedores() {
        try {
            rs = stmt.executeQuery("SELECT * FROM tb_vendedor");

            while (rs.next()) {
                System.out.println(rs.getString("id_vendedor") + " - " +
                        rs.getString("nombres_vendedor") + " - " +
                        rs.getString("apellidos_vendedor") + " - " +
                        rs.getString("user_vendedor") + " - " +
                        rs.getString("password_vendedor"));
            }

            rs.close();
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static String[] obtenerIds() {
        List<String> idsVendedores = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT id_vendedor FROM tb_vendedor");

            while (rs.next()) {
                idsVendedores.add(rs.getString("id_vendedor"));
            }

            rs.close();
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        // Convierte la lista a un array de String
        return idsVendedores.toArray(String[]::new);
    }

    public static void insertarVendedor(Vendedor vendedor) {
        try {
            String sql = "INSERT INTO tb_vendedor (nombres_vendedor, apellidos_vendedor, user_vendedor, password_vendedor) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vendedor.getNombres());
                pstmt.setString(2, vendedor.getApellidos());
                pstmt.setString(3, vendedor.getUser());
                pstmt.setString(4, vendedor.getPassword());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static Vendedor obtenerVendedorPorId(String idVendedor) {
        Vendedor vendedor = null;
        try {
            String sql = "SELECT id_vendedor, nombres_vendedor, apellidos_vendedor, user_vendedor, password_vendedor FROM tb_vendedor WHERE id_vendedor = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idVendedor);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String id = rs.getString("id_vendedor");
                        String nombres = rs.getString("nombres_vendedor");
                        String apellidos = rs.getString("apellidos_vendedor");
                        String user = rs.getString("user_vendedor");
                        String password = rs.getString("password_vendedor");
                        vendedor = new Vendedor(user, password, id, apellidos, nombres);
                    }
                }
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
        return vendedor;
    }

    public static void actualizarVendedor(Vendedor vendedor) {
        try {
            String sql = "UPDATE tb_vendedor SET nombres_vendedor = ?, apellidos_vendedor = ?, user_vendedor = ?, password_vendedor = ? WHERE id_vendedor = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vendedor.getNombres());
                pstmt.setString(2, vendedor.getApellidos());
                pstmt.setString(3, vendedor.getUser());
                pstmt.setString(4, vendedor.getPassword());
                pstmt.setString(5, vendedor.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static void eliminarVendedor(String idVendedor) {
        try {
            String sql = "DELETE FROM tb_vendedor WHERE id_vendedor = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idVendedor);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    private static void handleSQLException(SQLException sqle) {
        System.out.println("SQLException: " + sqle.getMessage());
        System.out.println("SQLState: " + sqle.getSQLState());
        System.out.println("VendorError: " + sqle.getErrorCode());
    }
}
