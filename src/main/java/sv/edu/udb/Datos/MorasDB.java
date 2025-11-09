package sv.edu.udb.Datos;

import sv.edu.udb.clases.Mora;
import sv.edu.udb.clases.Usuarios;

import java.math.BigDecimal;
import java.sql.*;

public class MorasDB {
    private final String SQL_INSERT ="INSERT INTO moras(fecha_inicio,tipo_usuario,tarifa_diaria) VALUES (?,?,?)";
    private final String SQL_UPDATE ="";
    private final String SQL_DELETE ="";
    private final String SQL_SELECT ="SELECT id_mora, fecha_inicio, tipo_usuario, tarifa_diaria FROM moras WHERE id_mora = ?";

        public Mora insert(Date fechaInicio, Usuarios.TipoUsuario tipoUsuario,
                           BigDecimal taridaDiaria) {
            Connection conn = null
            PreparedStatement stmt = null;
            ResultSet IdGenerado = null;
            ResultSet rs = null;
            Mora moraNueva = null;

            try {
                conn = Conexion.getConexion();
                stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, fechaInicio);
                stmt.setString(2, tipoUsuario.toString());
                stmt.setBigDecimal(3, taridaDiaria);
                stmt.executeUpdate();
                IdGenerado = stmt.getGeneratedKeys();

                if (IdGenerado.next()) {
                    stmt = conn.prepareStatement(SQL_SELECT);
                    stmt.setInt(1, IdGenerado.getInt(1));
                    rs = stmt.executeQuery();

                }




            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            return null;
        }
}

