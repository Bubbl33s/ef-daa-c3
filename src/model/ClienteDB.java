/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.DBConn.rs;
import static model.DBConn.stmt;

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
}
