package conexion;

import java.sql.*;
import java.util.ArrayList;


import model.Cliente;

public class StatementsClientes {


    public boolean guardar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (ID_Cliente, Nombre, Email, Contrasena, Telefono, Direccion) VALUES (?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        
        try{
            conn = conexionJDB.conectar(); // conectamos
            pstmt = conn.prepareStatement(sql); //preparamos el sql

            // Asignar valores ejecutando el sql

            pstmt.setString(1, cliente.getId());
            pstmt.setString(2, cliente.getName());
            pstmt.setString(3, cliente.getMail());
            pstmt.setString(4, cliente.getPassword());
            pstmt.setString(5, cliente.getPhoneNumber());
            pstmt.setString(6, cliente.getAddress());

            int filas = pstmt.executeUpdate(); //  Ejecutas el SQL
            System.out.println(" Registros insertados: " + filas);

            return filas > 0;

        } catch (SQLException e) {
            System.err.println(" Error al guardar el cliente: " + e.getMessage());
            return false;

        } finally {
            // Cierres manuales
            try {
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println(" Error al cerrar recursos: " + e.getMessage());
            }
        }

    }

    // READ
    public ArrayList<Cliente> obtenerTodosClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        try {
            conn = conexionJDB.conectar(); 
            
             pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery(sql); 

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getString("ID_Cliente"));
                cliente.setName(rs.getString("Nombre"));
                cliente.setMail(rs.getString("Email"));
                cliente.setPassword(rs.getString("Contrasena"));
                cliente.setPhoneNumber(rs.getString("Telefono"));
                cliente.setAddress(rs.getString("Direccion"));

                clientes.add(cliente);
            }
        
            System.out.println(" Se obtuvieron " + clientes.size() + " Clientes");

        } catch (SQLException e) {
            System.err.println(" Error al consultar clientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println(" Error al cerrar recursos: " + e.getMessage());
            }
        }

        return clientes;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre=?, email=?, password=?, direccion=?, telefono=? WHERE id_Cliente=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexionJDB.conectar();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getId());
            pstmt.setString(2, cliente.getName());
            pstmt.setString(3, cliente.getMail());
            pstmt.setString(4, cliente.getPassword());
            pstmt.setString(5, cliente.getPhoneNumber());
            pstmt.setString(6, cliente.getAddress());

            int filas = pstmt.executeUpdate();
            System.out.println("✏️ Registros actualizados: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println(" Error al actualizar: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println(" Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // 🔹 DELETE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Cliente WHERE ID_Cliente=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexionJDB.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();
            System.out.println("Registros eliminados: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}

    

