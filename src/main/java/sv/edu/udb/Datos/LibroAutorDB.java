package sv.edu.udb.Datos;

import java.sql.*;
import java.util.List;

public class LibroAutorDB {
    private final String SQL_INSERT = "INSERT INTO libro_autor(id_material, id_autor) VALUES(?,?)";
    private final String SQL_DELETE_BY_LIBRO = "DELETE FROM libro_autor WHERE id_material=?";
    private final String SQL_DELETE_BY_AUTOR = "DELETE FROM libro_autor WHERE id_autor=?";
    private final String SQL_DELETE_RELATION = "DELETE FROM libro_autor WHERE id_material=? AND id_autor=?";

    public boolean insertRelacion(int idMaterial, int idAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, idMaterial);
            stmt.setInt(2, idAutor);
            int rows = stmt.executeUpdate();
            resultado = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public boolean insertRelaciones(int idMaterial, List<Integer> idsAutores) {
        if (idsAutores == null || idsAutores.isEmpty()) {
            return true;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = true;
        try {
            conn = Conexion.getConexion();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SQL_INSERT);
            
            for (Integer idAutor : idsAutores) {
                stmt.setInt(1, idMaterial);
                stmt.setInt(2, idAutor);
                stmt.addBatch();
            }
            
            int[] rows = stmt.executeBatch();
            conn.commit();
            
            for (int row : rows) {
                if (row <= 0) {
                    resultado = false;
                    break;
                }
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            resultado = false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resultado = false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public boolean deleteRelacionesByLibro(int idMaterial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE_BY_LIBRO);
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
            resultado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public boolean deleteRelacionesByAutor(int idAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE_BY_AUTOR);
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
            resultado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public boolean deleteRelacion(int idMaterial, int idAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE_RELATION);
            stmt.setInt(1, idMaterial);
            stmt.setInt(2, idAutor);
            int rows = stmt.executeUpdate();
            resultado = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }
}
