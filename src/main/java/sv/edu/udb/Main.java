package sv.edu.udb;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sv.edu.udb.vistas.INICIO.INICIO;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        logger.info("Iniciando...");
        INICIO inicio = new INICIO();
        inicio.setVisible(true);
    }
}