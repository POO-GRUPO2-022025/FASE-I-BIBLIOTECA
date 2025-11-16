package sv.edu.udb.Datos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sv.edu.udb.clases.Material;
import sv.edu.udb.clases.Prestamo;
import sv.edu.udb.clases.Usuarios;

import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.chrono.ChronoLocalDate;

public class PruebasBD {
    private static final Logger logger = LogManager.getLogger(sv.edu.udb.Main.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        logger.info("Iniciando...");

        //Prueba para ingresar usuario
        UsuariosDB usuariosDB = new UsuariosDB();
        Usuarios usuario = new Usuarios();

        usuario.setNombre("Joel Granados");
        usuario.setTipoUsuario(Usuarios.TipoUsuario.Encargado);
        usuario.setCorreo("joelgranados@email.com");
        usuario.setPassword("12345");
        usuariosDB.insert(usuario);


        //Prueba para validar usuario
        //Se ingresa el correo y la contraseña
        String correo = "joel1@email.com";
        String password = "1234";
        Usuarios usuarioValidado = usuariosDB.loginUser(correo, password); // Devuelve el objeto Usuarios si este es correcto
        if (usuarioValidado != null) { //Si es diferente a null, (si encontro el usuario valido)
            System.out.println("Hola, bienvenido: " + usuarioValidado.getNombre()); //Muestra el mensaje de bienvenida
        } else {
            System.out.println("Usuario no valido");//De lo contrario mensaje de error al validar
        }
        //Prueba del selectALL de usuarios que devuelve una lista de usuarios registrados

    }

}