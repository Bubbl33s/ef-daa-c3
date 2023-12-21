package model;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import controller.Cliente;

public non-sealed class ClienteDB extends DBConn {

    public static void listar() {
        try {
            rs = stmt.executeQuery("SELECT * FROM tb_cliente");

            while (rs.next()) {
                System.out.println(rs.getString("id_cliente") + " - " +
                        rs.getString("nombres_cliente") + " - " +
                        rs.getString("apellidos_cliente") + " - " +
                        rs.getString("telefono_cliente") + " - " +
                        rs.getString("email_cliente"));
            }

            rs.close();
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static String[] obtenerIds() {
        List<String> idsClientes = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT id_cliente FROM tb_cliente");

            while (rs.next()) {
                idsClientes.add(rs.getString("id_cliente"));
            }

            rs.close();
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }

        return idsClientes.toArray(String[]::new);
    }

    public static void insertarCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO tb_cliente (nombres_cliente, apellidos_cliente, telefono_cliente, email_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getNombres());
                pstmt.setString(2, cliente.getApellidos());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getEmail());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static Cliente obtenerClientePorId(String idCliente) {
        Cliente cliente = null;
        try {
            String sql = "SELECT id_cliente, nombres_cliente, apellidos_cliente, telefono_cliente, email_cliente FROM tb_cliente WHERE id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idCliente);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String id = rs.getString("id_cliente");
                        String nombres = rs.getString("nombres_cliente");
                        String apellidos = rs.getString("apellidos_cliente");
                        String telefono = rs.getString("telefono_cliente");
                        String email = rs.getString("email_cliente");
                        cliente = new Cliente(id, nombres, apellidos, telefono, email);
                    }
                }
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
        return cliente;
    }

    public static void actualizarCliente(Cliente cliente) {
        try {
            String sql = "UPDATE tb_cliente SET apellidos_cliente = ?, nombres_cliente = ?, telefono_cliente = ?, email_cliente = ? WHERE id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getApellidos());
                pstmt.setString(2, cliente.getNombres());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getEmail());
                pstmt.setString(5, cliente.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }

    public static void eliminarCliente(String idCliente) {
        try {
            String sql = "DELETE FROM tb_cliente WHERE id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idCliente);
                pstmt.executeUpdate();
            }
        } catch (SQLException sqle) {
            handleSQLException(sqle);
        }
    }
}
