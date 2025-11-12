package sv.edu.udb.Datos;

import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.hijas.Revista;

import java.sql.*;

public class RevistaDB {
    private final String SQL_INSERT = "INSERT INTO revistas(id_material, volumen, numero, fecha_publicacion) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE revistas SET volumen=?, numero=?, fecha_publicacion=? WHERE id_material=?";
    private final String SQL_DELETE = "DELETE FROM revistas WHERE id_material=?";
    private final String SQL_SELECT = "SELECT * FROM revistas WHERE id_material=?";

    public Revista insert(Revista revista) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.insert(revista);
        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Revista nuevaRevista = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            stmt.setString(2, revista.getVolumen());
            stmt.setString(3, revista.getNumero());
            stmt.setDate(4, Date.valueOf(revista.getFechaPublicacion()));
            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, baseMaterial.getIdMaterial());
            rs = stmt.executeQuery();
            if (rs.next()) {
                nuevaRevista = new Revista(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("volumen"),
                        rs.getString("numero"),
                        rs.getDate("fecha_publicacion").toLocalDate()
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar revista", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nuevaRevista;
    }

    public boolean update(Revista revista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, revista.getVolumen());
            stmt.setString(2, revista.getNumero());
            stmt.setDate(3, Date.valueOf(revista.getFechaPublicacion()));
            stmt.setInt(4, revista.getIdMaterial());

            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar revista", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

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
            throw new RuntimeException("Error al eliminar revista", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    public Revista select(int idMaterial) {
        MaterialesDB materialesDB = new MaterialesDB();
        Material baseMaterial = materialesDB.select(idMaterial);
        if (baseMaterial == null) return null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Revista revista = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            if (rs.next()) {
                revista = new Revista(
                        baseMaterial.getIdMaterial(),
                        baseMaterial.getTipoMaterial(),
                        baseMaterial.getTitulo(),
                        baseMaterial.getUbicacion(),
                        baseMaterial.getCantidadTotal(),
                        baseMaterial.getCantidadDisponible(),
                        baseMaterial.getCantidadPrestada(),
                        baseMaterial.getCantidadDaniada(),
                        rs.getString("volumen"),
                        rs.getString("numero"),
                        rs.getDate("fecha_publicacion").toLocalDate()
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar revista", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return revista;
    }
}

