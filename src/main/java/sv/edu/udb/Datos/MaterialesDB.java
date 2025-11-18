package sv.edu.udb.Datos;

import sv.edu.udb.clases.Material;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MaterialesDB {
    private final String SQL_INSERT = "INSERT INTO materiales(id_material,tipo_material, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestados, cantidad_daniado) VALUES(?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE materiales SET tipo_material=?, titulo=?, ubicacion=?, cantidad_total=?, cantidad_disponible=?, cantidad_prestados=?, cantidad_daniado=? WHERE id_material=?";
    private final String SQL_DELETE = "DELETE FROM materiales WHERE id_material=?";
    private final String SQL_SELECT = "SELECT * FROM materiales WHERE id_material=?";
    private final String SQL_SELECT_ALL = "SELECT id_material, tipo_material, titulo, ubicacion, cantidad_total, cantidad_disponible, cantidad_prestados, cantidad_daniado FROM materiales ORDER BY id_material";

    public Material insert(Material material) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet IdGenerado = null;
        ResultSet rs = null;
        Material nuevoMaterial = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, material.getIdMaterial());
            stmt.setString(2, material.getTipoMaterial().toString());
            stmt.setString(3, material.getTitulo());
            stmt.setString(4, material.getUbicacion());
            stmt.setInt(5, material.getCantidadTotal());
            stmt.setInt(6, material.getCantidadDisponible());
            stmt.setInt(7, material.getCantidadPrestada());
            stmt.setInt(8, material.getCantidadDaniada());
            stmt.executeUpdate();

            IdGenerado = stmt.getGeneratedKeys();

            if (IdGenerado.next()) {
                stmt = conn.prepareStatement(SQL_SELECT);
                stmt.setInt(1, IdGenerado.getInt(1));
                rs = stmt.executeQuery();
                if (rs.next()) {
                    nuevoMaterial = new Material();
                    nuevoMaterial.setIdMaterial(rs.getInt("id_material"));
                    nuevoMaterial.setTipoMaterial(Material.TipoMaterial.valueOf(rs.getString("tipo_material")));
                    nuevoMaterial.setTitulo(rs.getString("titulo"));
                    nuevoMaterial.setUbicacion(rs.getString("ubicacion"));
                    nuevoMaterial.setCantidadTotal(rs.getInt("cantidad_total"));
                    nuevoMaterial.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                    nuevoMaterial.setCantidadPrestada(rs.getInt("cantidad_prestados"));
                    nuevoMaterial.setCantidadDaniada(rs.getInt("cantidad_daniado"));

                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al insertar material", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return nuevoMaterial;
    }


    public boolean update(Material material) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, material.getTipoMaterial().toString());
            stmt.setString(2, material.getTitulo());
            stmt.setString(3, material.getUbicacion());
            stmt.setInt(4, material.getCantidadTotal());
            stmt.setInt(5, material.getCantidadDisponible());
            stmt.setInt(6, material.getCantidadPrestada());
            stmt.setInt(7, material.getCantidadDaniada());
            
            stmt.setInt(8, material.getIdMaterial());

            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar préstamo", e);
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
            throw new RuntimeException("Error al eliminar préstamo", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return retorno;

    }

    public Material select(int idMaterial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Material material = null;

        try{
            conn =Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMaterial);
            rs = stmt.executeQuery();
            if (rs.next()) {
                material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setTipoMaterial(Material.TipoMaterial.valueOf(rs.getString("tipo_material")));
                material.setTitulo(rs.getString("titulo"));
                material.setUbicacion(rs.getString("ubicacion"));
                material.setCantidadTotal(rs.getInt("cantidad_total"));
                material.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                material.setCantidadPrestada(rs.getInt("cantidad_prestados"));
                material.setCantidadDaniada(rs.getInt("cantidad_daniado"));

            }

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar el material", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return material;
    }

    public DefaultTableModel selectMateriales() {
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

    public DefaultTableModel selectMaterialesFiltrado(String tipoMaterial, String titulo) {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // Construir query dinámicamente según filtros
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_material, tipo_material, titulo, ubicacion, cantidad_total, ")
           .append("cantidad_disponible, cantidad_prestados, cantidad_daniado ")
           .append("FROM materiales ");
        
        boolean hayFiltros = false;
        
        // Filtro por tipo de material
        if (tipoMaterial != null && !tipoMaterial.isEmpty() && !tipoMaterial.equals("Todos")) {
            sql.append("WHERE tipo_material = ? ");
            hayFiltros = true;
        }
        
        // Filtro por título (búsqueda parcial)
        if (titulo != null && !titulo.isEmpty()) {
            if (hayFiltros) {
                sql.append("AND titulo LIKE ? ");
            } else {
                sql.append("WHERE titulo LIKE ? ");
                hayFiltros = true;
            }
        }
        
        sql.append("ORDER BY id_material");
        
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(sql.toString());
            
            // Establecer parámetros según filtros
            int paramIndex = 1;
            if (tipoMaterial != null && !tipoMaterial.isEmpty() && !tipoMaterial.equals("Todos")) {
                stmt.setString(paramIndex++, tipoMaterial);
            }
            if (titulo != null && !titulo.isEmpty()) {
                stmt.setString(paramIndex, "%" + titulo + "%");
            }
            
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar materiales filtrados", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return dtm;
    }

}
