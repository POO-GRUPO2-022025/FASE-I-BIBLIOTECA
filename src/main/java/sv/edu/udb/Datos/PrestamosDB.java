package sv.edu.udb.Datos;

import sv.edu.udb.clases.Mora;
import sv.edu.udb.clases.Prestamo;

import java.sql.*;

public class PrestamosDB {
    private final String SQL_INSERT = "INSERT INTO prestamos(id_usuario,id_material,id_mora,mora_total,fecha_prestamo,fecha_devolucion,estado)VALUES(?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE prestamos SET id_usuario=?, id_material=?, id_mora=?, mora_total=?, fecha_prestamo=?, fecha_devolucion=?, estado=? WHERE id_prestamo=?";
    private final String SQL_DELETE = "DELETE FROM prestamos WHERE id_prestamo=?";
    private final String SQL_SELECT = "SELECT * FROM prestamos WHERE id_prestamo=?";

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
            stmt.setDate(6, Date.valueOf(prestamo.getFechaDevolucion().toLocalDate()));
            stmt.setString(7, prestamo.getEstado().toString());
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

            stmt.setInt(1, prestamo.getIdPrestamo());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setInt(3, prestamo.getIdMora());
            stmt.setBigDecimal(4, prestamo.getMoraTotal());
            stmt.setDate(5, Date.valueOf(prestamo.getFechaPrestamo().toLocalDate()));
            stmt.setDate(6, Date.valueOf(prestamo.getFechaDevolucion().toLocalDate()));
            stmt.setString(7, String.valueOf(Prestamo.Estado.valueOf(prestamo.getEstado().toString())));
            stmt.setInt(8, prestamo.getIdPrestamo());

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
                prestamo.setFechaPrestamo(Date.valueOf(rs.getDate("fecha_prestamo").toLocalDate()));
                prestamo.setFechaDevolucion(Date.valueOf(rs.getDate("fecha_devolucion").toLocalDate()));
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
}
