package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Jeans;

public class StatementsJeans {
     public boolean guardar(Jeans jean) throws SQLException {
        String sql = "INSERT INTO ItemsJeans (ID_Ref, Nombre_Ref, Precio) VALUES (?,?,?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        
        try{
            conn = conexionJDB.conectar(); // conectamos
            pstmt = conn.prepareStatement(sql); //preparamos el sql

            // Asignar valores ejecutando el sql

            pstmt.setInt(1, jean.getId());
            pstmt.setString(2, jean.getEstilo());
            pstmt.setDouble(3, jean.getPrice());
         

            int filas = pstmt.executeUpdate(); //  Ejecutas el SQL
            System.out.println(" Registros insertados: " + filas);

            return filas > 0;

        } catch (SQLException e) {
            System.err.println(" Error al guardar el jean: " + e.getMessage());
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
    public ArrayList<Jeans> obtenerTodasRef() throws SQLException {
        ArrayList<Jeans> jeans = new ArrayList<>();
        String sql = "SELECT * FROM ItemsJeans";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
       
        try {
            conn = conexionJDB.conectar(); 
            
             pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery(sql); 

            while (rs.next()) {
                Jeans jean = new Jeans();

                jean.setId(rs.getInt("ID_Ref"));
                jean.setEstilo(rs.getString("Nombre_Ref"));
                jean.setPrice(rs.getDouble("Precio"));
              
                jeans.add(jean);
            }
        
            System.out.println(" Se obtuvieron " + jeans.size() + " Referencias");

        } catch (SQLException e) {
            System.err.println(" Error al consultarlas referencias: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conexionJDB.cerrar(conn);
            } catch (SQLException e) {
                System.err.println(" Error al cerrar recursos: " + e.getMessage());
            }
        }

        return jeans;
    }

    public boolean actualizar(Jeans jean) {
        String sql = "UPADTE ItemsJeans SET  Nombre_Ref = ?, Precio = ? WHERE ID_Ref=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexionJDB.conectar();
            pstmt = conn.prepareStatement(sql);

           
            pstmt.setString(1, jean.getEstilo());
            pstmt.setDouble(2, jean.getPrice());
             pstmt.setInt(3, jean.getId());
           

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
        String sql = "DELETE FROM articulo WHERE ID_Ref=?";

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
