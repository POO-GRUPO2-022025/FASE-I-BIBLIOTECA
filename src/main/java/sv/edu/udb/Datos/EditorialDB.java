package sv.edu.udb.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import sv.edu.udb.clases.Editorial;

public class EditorialDB {
    private final String SQL_INSERT = "INSERT INTO editoriales(id_editorial, nombre, pais) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE editoriales SET nombre=?, pais=? WHERE id_editorial=?";
    private final String SQL_DELETE = "DELETE FROM editoriales WHERE id_editorial = ?";
    private final String SQL_SELECT = "SELECT id_editorial, nombre, pais FROM editoriales ORDER BY id_editorial";

    public int insert(Editorial editorial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, editorial.getIdEditorial());
            stmt.setString(index++, editorial.getNombre());
            stmt.setString(index, editorial.getPais());
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


    public int update(Editorial editorial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, editorial.getNombre());
            stmt.setString(index++, editorial.getPais());
            stmt.setInt(index, editorial.getIdEditorial());
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


    public int delete(Editorial editorial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, editorial.getIdEditorial());
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


    public DefaultTableModel selectEditoriales() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            /*
             * ResultSetMetaData contiene información sobre la estructura de los datos:
             * - Cuántas columnas tiene la tabla
             * - Nombre de cada columna
             * - Tipo de dato de cada columna
             */
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            
            /*
             * Primero construimos los encabezados (headers) de la tabla.
             * Por cada columna en el ResultSet, agregamos su nombre al DefaultTableModel.
             * Esto define las "columnas" de nuestra tabla.
             */
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            
            /*
             * Ahora recorremos cada registro (fila) devuelto por la consulta SQL.
             */
            while (rs.next()) {
                /*
                 * Para cada fila, creamos un arreglo de Object[] con el tamaño igual al número de columnas.
                 * Este arreglo representará una fila completa en el DefaultTableModel.
                 */
                Object[] fila = new Object[numberOfColumns];
                
                /*
                 * Llenamos el arreglo con los valores de cada columna.
                 * rs.getObject(i+1) obtiene el valor de la columna en la posición i+1
                 * (sumamos 1 porque ResultSet usa índices base-1, mientras que los arreglos usan base-0)
                 */
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                
                /*
                 * Finalmente agregamos la fila completa al DefaultTableModel.
                 */
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
        
        /*
         * Retornamos el DefaultTableModel completamente construido.
         * Ahora contiene:
         * - Los nombres de las columnas (id_editorial, nombre, pais)
         * - Todas las filas con los datos de cada editorial
         * Este objeto se puede usar directamente con un JTable para mostrar los datos en pantalla.
         */
        return dtm;
    }
}
