/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            System.out.println("VendedorError: " + sqle.getErrorCode());
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
            System.out.println("VendedorError: " + sqle.getErrorCode());
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
            System.out.println("VendedorError: " + sqle.getErrorCode());
        }

        return vendedor;
    }
}
