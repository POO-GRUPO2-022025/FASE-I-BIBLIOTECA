package sv.edu.udb.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import sv.edu.udb.clases.Prestamo;

public class PrestamosDB {
    private final String SQL_INSERT = "INSERT INTO prestamos(id_usuario,id_material,id_mora,mora_total,fecha_prestamo,fecha_estimada,fecha_devolucion,estado)VALUES(?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE prestamos SET id_usuario=?, id_material=?, id_mora=?, mora_total=?, fecha_prestamo=?, fecha_estimada=?, fecha_devolucion=?, estado=? WHERE id_prestamo=?";
    private final String SQL_DELETE = "DELETE FROM prestamos WHERE id_prestamo=?";
    private final String SQL_SELECT = "SELECT * FROM prestamos WHERE id_prestamo=?";
    private final String SQL_SELECT_ALL = "SELECT id_prestamo, id_usuario, id_material, id_mora, mora_total, fecha_prestamo, fecha_estimada, fecha_devolucion, estado FROM prestamos ORDER BY id_prestamo";

    public Prestamo insert(Prestamo prestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet IdGenerado = null;
        ResultSet rs = null;
        Prestamo nuevoPrestamo = null;


        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdMaterial());
            stmt.setInt(3, prestamo.getIdMora());
            stmt.setBigDecimal(4, prestamo.getMoraTotal());
            stmt.setDate(5, Date.valueOf(prestamo.getFechaPrestamo().toLocalDate()));
            
            // Validar null para fecha_estimada
            if (prestamo.getFechaEstimada() != null) {
                stmt.setDate(6, Date.valueOf(prestamo.getFechaEstimada().toLocalDate()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }
            
            // Validar null para fecha_devolucion
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(7, Date.valueOf(prestamo.getFechaDevolucion().toLocalDate()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }
            
            stmt.setString(8, prestamo.getEstado().toString());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                nuevoPrestamo = new Prestamo();
                nuevoPrestamo.setIdPrestamo(rs.getInt(1));
                System.out.println(nuevoPrestamo.getIdPrestamo());
            }


        } catch (SQLException | ClassNotFoundException e) { //Proceso para capturar errores y manda un mensaje
            throw new RuntimeException("Error al insertar Prestamo", e);
        } finally {
            //Cierre de todos los recursos
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return nuevoPrestamo;

    }

    public boolean update(Prestamo prestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion(); // Se establece la conexión
            stmt = conn.prepareStatement(SQL_UPDATE); // Se prepara la sentencia UPDATE

            stmt.setInt(1, prestamo.getIdUsuario());
            stmt.setInt(2, prestamo.getIdMaterial());
            stmt.setInt(3, prestamo.getIdMora());
            stmt.setBigDecimal(4, prestamo.getMoraTotal());
            stmt.setDate(5, Date.valueOf(prestamo.getFechaPrestamo().toLocalDate()));
            
            // Validar null para fecha_estimada
            if (prestamo.getFechaEstimada() != null) {
                stmt.setDate(6, Date.valueOf(prestamo.getFechaEstimada().toLocalDate()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }
            
            // Validar null para fecha_devolucion
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(7, Date.valueOf(prestamo.getFechaDevolucion().toLocalDate()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }
            
            stmt.setString(8, prestamo.getEstado().toString());
            stmt.setInt(9, prestamo.getIdPrestamo());

            int filas = stmt.executeUpdate(); // Ejecuta el UPDATE
            retorno = filas > 0; // Si se modificó al menos una fila, retorna true

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al actualizar préstamo", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return retorno;
    }

    public boolean delete(int idPrestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPrestamo);
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

    public Prestamo select(int idPrestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prestamo prestamo = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idPrestamo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setIdMaterial(rs.getInt("id_material"));
                prestamo.setIdMora(rs.getInt("id_mora"));
                prestamo.setMoraTotal(rs.getBigDecimal("mora_total"));
                
                // Verificar null antes de convertir fechas
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                if (fechaPrestamo != null) {
                    prestamo.setFechaPrestamo(Date.valueOf(fechaPrestamo.toLocalDate()));
                }
                
                Date fechaEstimada = rs.getDate("fecha_estimada");
                if (fechaEstimada != null) {
                    prestamo.setFechaEstimada(Date.valueOf(fechaEstimada.toLocalDate()));
                }
                
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                if (fechaDevolucion != null) {
                    prestamo.setFechaDevolucion(Date.valueOf(fechaDevolucion.toLocalDate()));
                }
                
                prestamo.setEstado(Prestamo.Estado.valueOf(rs.getString("estado")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar préstamo", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return prestamo;
    }

    public DefaultTableModel selectPrestamos() {
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

    public DefaultTableModel selectPrestamosDetallado() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT_DETALLADO = "SELECT p.id_prestamo, u.nombre AS usuario, m.titulo AS material, " +
                "COALESCE(mo.tarifa_diaria, 0) AS tarifa_diaria, p.fecha_prestamo, p.fecha_estimada, p.fecha_devolucion, p.estado " +
                "FROM prestamos p " +
                "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                "INNER JOIN materiales m ON p.id_material = m.id_material " +
                "LEFT JOIN moras mo ON p.id_mora = mo.id_mora " +
                "ORDER BY p.id_prestamo";
        
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DETALLADO);
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
            throw new RuntimeException("Error al consultar préstamos detallados", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return dtm;
    }

    public DefaultTableModel selectPrestamosDetalladoFiltrado(String tipoMaterial, String estado, boolean conMora) {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // Construir query dinámicamente según filtros
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id_prestamo, u.nombre AS usuario, m.titulo AS material, ")
           .append("COALESCE(mo.tarifa_diaria, 0) AS tarifa_diaria, p.fecha_prestamo, p.fecha_estimada, p.fecha_devolucion, p.estado ")
           .append("FROM prestamos p ")
           .append("INNER JOIN usuarios u ON p.id_usuario = u.id_usuario ")
           .append("INNER JOIN materiales m ON p.id_material = m.id_material ")
           .append("LEFT JOIN moras mo ON p.id_mora = mo.id_mora ");
        
        // Agregar condiciones WHERE si hay filtros
        boolean hayFiltros = false;
        if (tipoMaterial != null && !tipoMaterial.equals("Todos")) {
            sql.append("WHERE m.tipo_material = ? ");
            hayFiltros = true;
        }
        
        if (estado != null && !estado.equals("Todos")) {
            if (hayFiltros) {
                sql.append("AND p.estado = ? ");
            } else {
                sql.append("WHERE p.estado = ? ");
                hayFiltros = true;
            }
        }
        
        if (conMora) {
            if (hayFiltros) {
                sql.append("AND p.mora_total > 0 ");
            } else {
                sql.append("WHERE p.mora_total > 0 ");
            }
        }
        
        sql.append("ORDER BY p.id_prestamo");
        
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(sql.toString());
            
            // Establecer parámetros según filtros
            int paramIndex = 1;
            if (tipoMaterial != null && !tipoMaterial.equals("Todos")) {
                stmt.setString(paramIndex++, tipoMaterial);
            }
            if (estado != null && !estado.equals("Todos")) {
                stmt.setString(paramIndex, estado);
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
            throw new RuntimeException("Error al consultar préstamos filtrados", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return dtm;
    }

}
