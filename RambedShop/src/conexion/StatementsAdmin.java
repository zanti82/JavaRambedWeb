package conexion;

import java.sql.*;
import java.util.ArrayList;

import model.Admin;

public class StatementsAdmin {


    public boolean guardar(Admin admin) throws SQLException {
        String sql = "INSERT INTO Administrador (ID_Admin, Nombre_Admin, Email, Contrasena, Telefono, Direccion) VALUES (?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        
        try{
            conn = conexionJDB.conectar(); // conectamos
            pstmt = conn.prepareStatement(sql); //preparamos el sql

            // Asignar valores ejecutando el sql

            pstmt.setString(1, admin.getId());
            pstmt.setString(2, admin.getName());
            pstmt.setString(3, admin.getMail());
            pstmt.setString(4, admin.getPassword());
            pstmt.setString(5, admin.getPhoneNumber());
            pstmt.setString(5, admin.getAddress());

            int filas = pstmt.executeUpdate(); //  Ejecutas el SQL
            System.out.println(" Registros insertados: " + filas);

            return filas > 0;

        } catch (SQLException e) {
            System.err.println(" Error al guardar el admin: " + e.getMessage());
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
    public ArrayList<Admin> obtenerTodosAdmins() throws SQLException {
        ArrayList<Admin> adminList = new ArrayList<>();
        String sql = "SELECT * FROM Administrador order by Nombre_Admin";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        try {
            conn = conexionJDB.conectar(); 
            
             pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery(sql); 

            while (rs.next()) {
                Admin admin = new Admin();

                admin.setId(rs.getString("ID_Admin"));
                admin.setName(rs.getString("Nombre_Admin"));
                admin.setMail(rs.getString("Email"));
                admin.setPassword(rs.getString("Contrasena"));
                admin.setPhoneNumber(rs.getString("Telefono"));
                admin.setAddress(rs.getString("Direccion"));

                adminList.add(admin);
            }
        
            System.out.println(" Se obtuvieron " + adminList.size() + " Admins");

        } catch (SQLException e) {
            System.err.println(" Error al consultar los admin: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println(" Error al cerrar recursos: " + e.getMessage());
            }
        }

        return adminList;
    }

    public boolean actualizar(Admin admin) {
        String sql = "UPDATE Administrador SET ID_Admin=?, Nombre_Admin=?, Email=?, Contrasena=?, Telefono=?, Direccion=? WHERE ID_Admin=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexionJDB.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getMail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, admin.getPhoneNumber());
            pstmt.setString(5, admin.getAddress());

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
    public boolean eliminar(String id) {
        String sql = "DELETE FROM articulo WHERE id_Cliente=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexionJDB.conectar();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);

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

    

