package sv.edu.udb.Datos;

import sv.edu.udb.clases.Autor;
import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.hijas.Libro;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

public class LibroDB {
    private final String SQL_INSERT = "INSERT INTO libros(id_material, id_editorial, isbn) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE libros SET id_editorial=?, isbn=? WHERE id_material=?";
    private final String SQL_DELETE = "DELETE FROM libros WHERE id_material=?";
    private final String SQL_SELECT = "SELECT * FROM libros WHERE id_material=?";
    private final String SQL_SELECT_ALL = "SELECT l.id_material, l.id_editorial, l.isbn, m.tipo_material, m.titulo, m.ubicacion, " +
            "m.cantidad_total, m.cantidad_disponible, m.cantidad_prestados, m.cantidad_daniado " +
            "FROM libros l INNER JOIN materiales m ON l.id_material = m.id_material ORDER BY l.id_material";

    public Libro insert(Libro libro) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.insert(libro);

        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Libro nuevoLibro = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            stmt.setInt(2, libro.getIdEditorial());
            stmt.setString(3, libro.getIsbn());
            stmt.executeUpdate();

            if (libro.getIdsAutores() != null && !libro.getIdsAutores().isEmpty()) {
                LibroAutorDB libroAutorDB = new LibroAutorDB();
                libroAutorDB.insertRelaciones(baseMaterial.getIdMaterial(), libro.getIdsAutores());
            }

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            rs = stmt.executeQuery();
            if (rs.next()) {
                nuevoLibro = new Libro(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getInt("id_editorial"),
                        rs.getString("isbn")
                );
                nuevoLibro.setIdEditorial(rs.getInt("id_editorial"));
                nuevoLibro.setIdsAutores(libro.getIdsAutores());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar libro", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nuevoLibro;
    }

    public boolean update(Libro libro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, libro.getIdEditorial());
            stmt.setString(2, libro.getIsbn());
            stmt.setInt(3, libro.getIdMaterial());

            int filas = stmt.executeUpdate();
            retorno = filas > 0;

            if (retorno && libro.getIdsAutores() != null) {
                LibroAutorDB libroAutorDB = new LibroAutorDB();
                libroAutorDB.deleteRelacionesByLibro(libro.getIdMaterial());
                libroAutorDB.insertRelaciones(libro.getIdMaterial(), libro.getIdsAutores());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar libro", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    // Elimina solo la parte libro
    public boolean delete(int idMaterial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idMaterial);
            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al eliminar libro", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    public Libro select(int idMaterial) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.select(idMaterial);
        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Libro libro = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            if (rs.next()) {
                AutorDB autorDB = new AutorDB();
                List<Autor> autores = autorDB.selectAutoresByLibro(idMaterial);
                
                libro = new Libro(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getInt("id_editorial"),
                        rs.getString("isbn")
                );
                libro.setIdEditorial(rs.getInt("id_editorial"));
                
                for (Autor autor : autores) {
                    libro.addIdAutor(autor.getIdAutor());
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar libro", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return libro;
    }

    public DefaultTableModel selectLibros() {
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
}
