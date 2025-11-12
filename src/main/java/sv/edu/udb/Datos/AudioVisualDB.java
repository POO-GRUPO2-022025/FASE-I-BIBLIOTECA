package sv.edu.udb.Datos;

import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.hijas.Audiovisual;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class AudioVisualDB {
    private final String SQL_INSERT = "INSERT INTO audiovisuales(id_material, formato, duracion) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE audiovisuales SET formato=?, duracion=? WHERE id_material=?";
    private final String SQL_DELETE = "DELETE FROM audiovisuales WHERE id_material=?";
    private final String SQL_SELECT = "SELECT * FROM audiovisuales WHERE id_material=?";
    private final String SQL_SELECT_ALL = "SELECT av.id_material, av.formato, av.duracion, m.tipo_material, m.titulo, m.ubicacion, " +
            "m.cantidad_total, m.cantidad_disponible, m.cantidad_prestados, m.cantidad_daniado " +
            "FROM audiovisuales av INNER JOIN materiales m ON av.id_material = m.id_material ORDER BY av.id_material";

    public Audiovisual insert(Audiovisual audiovisual) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.insert(audiovisual); // Inserta en materiales

        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Audiovisual nuevoAV = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            stmt.setString(2, audiovisual.getFormato());
            stmt.setInt(3, audiovisual.getDuracion());
            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            rs = stmt.executeQuery();
            if (rs.next()) {
                nuevoAV = new Audiovisual(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("formato"),
                        rs.getInt("duracion")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar audiovisual", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nuevoAV;
    }

    // Actualiza solo la parte audiovisual
    public boolean update(Audiovisual audiovisual) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, audiovisual.getFormato());
            stmt.setInt(2, audiovisual.getDuracion());
            stmt.setInt(3, audiovisual.getIdMaterial());

            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar audiovisual", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    // Elimina solo la parte audiovisual
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
            throw new RuntimeException("Error al eliminar audiovisual", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    // Consulta el audiovisual completo
    public Audiovisual select(int idMaterial) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.select(idMaterial);
        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Audiovisual audiovisual = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            if (rs.next()) {
                audiovisual = new Audiovisual(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("formato"),
                        rs.getInt("duracion")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar audiovisual", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return audiovisual;
    }

    public DefaultTableModel selectAudiovisuales() {
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


