package sv.edu.udb.Datos;

import java.sql.*;

public class Conexion {

    //Se colocan los atributos para la conexión
    private static final String driverDB = "com.mysql.cj.jdbc.Driver";
    private static final String urlDB = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String usuarioDB = "root";
    private static final String passDB = "123456789";

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        Class.forName(driverDB);
        try {
            Connection conn = DriverManager.getConnection(urlDB, usuarioDB, passDB);
            System.out.println("Conexion establecida!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la DB");
            return null;
        }
    }

    //Cierre de los recursos
    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando ResultSet", e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando Statement", e);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando Connection", e);
        }
    }


}
