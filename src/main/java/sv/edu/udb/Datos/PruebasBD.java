package sv.edu.udb.Datos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.Prestamo;
import sv.edu.udb.clases.Usuarios;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.chrono.ChronoLocalDate;

public class PruebasBD {
    private static final Logger logger = LogManager.getLogger(sv.edu.udb.Main.class);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        logger.info("Iniciando...");

        UsuariosDB usuariosDB = new UsuariosDB();
       // usuariosDB.insert("Joel","Encargado","joel3@email.com","1234");

       // MorasDB morasDB = new MorasDB();
        //morasDB.insert(LocalDate.parse("2025-12-12"), Usuarios.TipoUsuario.Profesor, BigDecimal.valueOf(10.00));
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(3);
        prestamo.setIdMaterial(2);
        prestamo.setIdUsuario(1);
        prestamo.setIdMora(2);
        prestamo.setFechaPrestamo(Date.valueOf("2025-12-12"));
        prestamo.setMoraTotal(BigDecimal.valueOf(12.00));
        prestamo.setFechaDevolucion(Date.valueOf("2025-12-12"));
        prestamo.setEstado(Prestamo.Estado.Pendiente);

        PrestamosDB prestamosDB = new PrestamosDB();
        prestamosDB.insert(prestamo);
        Prestamo p = null;
        p=prestamosDB.select(4);

        System.out.println("ID Préstamo: " + p.getIdPrestamo());
        System.out.println("ID Usuario: " + p.getIdUsuario());
        System.out.println("ID Material: " + p.getIdMaterial());
        System.out.println("ID Mora: " + p.getIdMora());
        System.out.println("Mora Total: " + p.getMoraTotal());
        System.out.println("Fecha Préstamo: " + p.getFechaPrestamo());
        System.out.println("Fecha Devolución: " + p.getFechaDevolucion());
        System.out.println("Estado: " + p.getEstado());






    }
}