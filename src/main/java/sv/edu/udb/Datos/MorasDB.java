package sv.edu.udb.Datos;

import sv.edu.udb.clases.Mora;
import sv.edu.udb.clases.Usuarios;

import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.*;
import java.time.Year;

public class MorasDB {
    private final String SQL_INSERT = "INSERT INTO moras(anio_aplicable,tipo_usuario,tarifa_diaria) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE moras SET anio_aplicable =?,tipo_usuario =?,tarifa_diaria =? WHERE id_mora = ?\n";
    private final String SQL_DELETE = "DELETE FROM moras WHERE id_mora = ?";
    private final String SQL_SELECT = "SELECT id_mora, anio_aplicable, tipo_usuario, tarifa_diaria FROM moras WHERE id_mora = ?";
    private final String SQL_SELECT_ALL = "SELECT id_mora, anio_aplicable, tipo_usuario, tarifa_diaria FROM moras ORDER BY id_mora";

    public Mora insert(int anioAplicable, Usuarios.TipoUsuario tipoUsuario,
                       BigDecimal tarifaDiaria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mora moraNueva = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, anioAplicable);
            stmt.setString(2, tipoUsuario.toString());
            stmt.setBigDecimal(3, tarifaDiaria);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                moraNueva = new Mora();
                moraNueva.setIdMora(rs.getInt(1));
                System.out.println(moraNueva.getIdMora());

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al agregar mora", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return moraNueva;
    }

    public Boolean update(Mora moraUpdate) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Boolean retorno = false;
        ResultSet rs = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, moraUpdate.getanio_aplicable());
            stmt.setString(2, moraUpdate.getTipoUsuario().toString());
            stmt.setBigDecimal(3, moraUpdate.getTarifaDiaria());
            stmt.setInt(4, moraUpdate.getIdMora());
            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        } catch (SQLException | ClassNotFoundException e) { //Proceso para capturar errores y manda un mensaje
            throw new RuntimeException("Error al actualizar mora", e);
        } finally {
            //Cierre de todos los recursos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return retorno;
    }
    public boolean delete(int idMora){

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try{

            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idMora);
            int filas = stmt.executeUpdate();
            retorno = filas > 0;

        }catch (SQLException | ClassNotFoundException e) { //Proceso para capturar errores y manda un mensaje

            System.out.println(e.getMessage());
        } finally {
            //Cierre de todos los recursos
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return retorno;
        

    }

    public Mora select(int idMora){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mora mora = null;

        try{
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idMora);
            rs = stmt.executeQuery();
            if (rs.next()) {
                mora = new Mora();
                mora.setIdMora(rs.getInt(1));
                mora.setanio_aplicable(rs.getInt("anio_aplicable"));
                mora.setTipoUsuario(Usuarios.TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                mora.setTarifaDiaria(rs.getBigDecimal("tarifa_diaria"));
            }


        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar préstamo", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return mora;
    }

    public DefaultTableModel selectMoras() {
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

    public int getIdMoraPorTipoUsuario(Usuarios.TipoUsuario tipoUsuario, int anio_prestamo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idMora = 0;

        String SQL_SELECT_BY_TIPO = "SELECT id_mora FROM moras WHERE tipo_usuario = ? AND anio_aplicable = ? LIMIT 1";

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TIPO);
            stmt.setString(1, tipoUsuario.toString());
            stmt.setInt(2, anio_prestamo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idMora = rs.getInt("id_mora");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar ID de mora por tipo de usuario", e);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return idMora;
    }

}

