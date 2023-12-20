/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author maste
 */
public class ClienteDB extends DBConn {
    public static void listar() {
        try {
            rs = stmt.executeQuery("SELECT id_cliente, apellidos_cliente, nombres_cliente FROM tb_cliente");

            while (rs.next()) {
                System.out.println(rs.getString("id_cliente") + " - " + rs.getString("apellidos_cliente") + " " + rs.getString("nombres_cliente"));
            }

            rs.close();
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }
}
