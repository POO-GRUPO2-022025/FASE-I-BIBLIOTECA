package sv.edu.udb.Datos;

import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.hijas.OtroDocumento;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class OtroDocumentoDB {
    private final String SQL_INSERT = "INSERT INTO otros_documentos(id_material, descripcion) VALUES(?,?)";
    private final String SQL_UPDATE = "UPDATE otros_documentos SET descripcion=? WHERE id_material=?";
    private final String SQL_DELETE = "DELETE FROM otros_documentos WHERE id_material=?";
    private final String SQL_SELECT = "SELECT * FROM otros_documentos WHERE id_material=?";
    private final String SQL_SELECT_ALL = "SELECT od.id_material, od.descripcion, m.tipo_material, m.titulo, m.ubicacion, " +
            "m.cantidad_total, m.cantidad_disponible, m.cantidad_prestados, m.cantidad_daniado " +
            "FROM otros_documentos od INNER JOIN materiales m ON od.id_material = m.id_material ORDER BY od.id_material";

    public OtroDocumento insert(OtroDocumento otroDocumento) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.insert(otroDocumento); // Inserta en materiales

        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OtroDocumento nuevoDocumento = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            stmt.setString(2, otroDocumento.getDescripcion());
            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            rs = stmt.executeQuery();
            if (rs.next()) {
                nuevoDocumento = new OtroDocumento(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("descripcion")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar otro documento", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nuevoDocumento;
    }

    // Actualiza solo la parte otro documento
    public boolean update(OtroDocumento otroDocumento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, otroDocumento.getDescripcion());
            stmt.setInt(2, otroDocumento.getIdMaterial());

            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar otro documento", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    // Elimina solo la parte otro documento
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
            throw new RuntimeException("Error al eliminar otro documento", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    // Consulta el otro documento completo
    public OtroDocumento select(int idMaterial) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.select(idMaterial);
        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OtroDocumento otroDocumento = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            if (rs.next()) {
                otroDocumento = new OtroDocumento(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("descripcion")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar otro documento", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return otroDocumento;
    }

    public DefaultTableModel selectOtrosDocumentos() {
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
