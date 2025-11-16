package sv.edu.udb.Datos;

import sv.edu.udb.clases.Autor;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDB {
    private final String SQL_INSERT = "INSERT INTO autores(id_autor, nombre, apellidos, pais) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE autores SET nombre=?, apellidos=?, pais=? WHERE id_autor=?";
    private final String SQL_DELETE = "DELETE FROM autores WHERE id_autor=?";
    private final String SQL_SELECT = "SELECT id_autor, nombre, apellidos, pais FROM autores WHERE id_autor=?";
    private final String SQL_SELECT_ALL = "SELECT id_autor, nombre, apellidos, pais FROM autores ORDER BY id_autor";
    private final String SQL_SELECT_BY_LIBRO = "SELECT a.id_autor, a.nombre, a.apellidos, a.pais " +
            "FROM autores a INNER JOIN libro_autor la ON a.id_autor = la.id_autor " +
            "WHERE la.id_material = ?";

    public int insert(Autor autor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, autor.getIdAutor());
            stmt.setString(index++, autor.getNombre());
            stmt.setString(index++, autor.getApellidos());
            stmt.setString(index, autor.getPais());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(Autor autor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, autor.getNombre());
            stmt.setString(index++, autor.getApellidos());
            stmt.setString(index++, autor.getPais());
            stmt.setInt(index, autor.getIdAutor());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(int idAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idAutor);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public Autor select(int idAutor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autor autor = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idAutor);
            rs = stmt.executeQuery();
            if (rs.next()) {
                autor = new Autor(
                        rs.getInt("id_autor"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("pais")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return autor;
    }

    public List<Autor> selectAutoresByLibro(int idMaterial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Autor> autores = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_LIBRO);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(
                        rs.getInt("id_autor"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("pais")
                );
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return autores;
    }

    public DefaultTableModel selectAutores() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return dtm;
    }

    public List<Autor> getAllAutores() {
        List<Autor> autores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("id_autor"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("pais")
                );
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return autores;
    }
}
