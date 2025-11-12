package sv.edu.udb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.Datos.Conexion;
import sv.edu.udb.Datos.UsuariosDB;

import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        logger.info("Iniciando...");

        UsuariosDB usuariosDB = new UsuariosDB();





    }
}