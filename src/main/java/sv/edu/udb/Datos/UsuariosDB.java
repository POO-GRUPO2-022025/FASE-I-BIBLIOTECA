package sv.edu.udb.Datos;

import org.mindrot.jbcrypt.BCrypt;
import sv.edu.udb.clases.Usuarios;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UsuariosDB {

    private final String SQL_INSERT = "INSERT INTO usuarios(nombre,tipo_usuario,correo,password) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE usuarios SET nombre=?, tipo_usuario=?, correo=? WHERE id_usuario=?\n";
    private final String SQL_DELETE = "DELETE FROM usuarios where id_usuario=?";
    private final String SQL_SELECT = "SELECT id_usuario,nombre,tipo_usuario,correo,password FROM usuarios WHERE id_usuario=?";
    private final String SQL_LOGIN = "SELECT * FROM usuarios WHERE correo=?";
    private final String SQL_SELECT_ALL = "SELECT id_usuario, nombre, tipo_usuario, correo, password FROM usuarios ORDER BY id_usuario";
    private final String SQL_RESET_PASS = "UPDATE usuarios SET password=? WHERE correo=?";

    //Metodo insert crea un nuevo usuario en la base de datos y devuelve el objeto completo con los datos

    public Usuarios insert(Usuarios usuarios) {
        //Declaracion de los recursos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usuarioNuevo = null;
        try {
            conn = Conexion.getConexion(); //Se realiza la conexion a la base de datos
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); //Se prepara INSERT y se devuelve el ID que genera
            //Se inicia asignando los valores para cada uno de los ? de la sentencia
            stmt.setString(1, usuarios.getNombre());
            stmt.setString(2, usuarios.getTipoUsuario().toString());
            stmt.setString(3, usuarios.getCorreo());
            stmt.setString(4, usuarios.getPassword());
            stmt.executeUpdate(); //Se ejecuta el INSERT

            rs = stmt.getGeneratedKeys(); //Se obtiene el ID que se genera

            if (rs.next()) { //Se valida si se genera el ID y se inicia el proceso para obtener los datos
                usuarioNuevo = new Usuarios();
                usuarioNuevo.setIdUsuario(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) { //Proceso para capturar errores y manda un mensaje
            throw new RuntimeException("Error al añadir usuario", e);
        } finally {
            //Cierre de todos los recursos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarioNuevo; //Devuelve el Usuarios
    }

    //Metodo para modificar usuarios
    //Retornara true si los datos son modificados
    public boolean update(Usuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE); //Se prepara la sentencia UPDATE
            //Se le asignan los nuevos valores
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getTipoUsuario().toString());
            stmt.setString(3, usuario.getCorreo());
           
            stmt.setInt(4, usuario.getIdUsuario());
            int filas = stmt.executeUpdate(); //Ejecuta el UPDATE y devuelve nuemero de lineas modificadas
            retorno = filas > 0; //Si las lineas modificadas es mayor a cero, retorna true


        } catch (SQLException | ClassNotFoundException e) { //Captura de errores
            throw new RuntimeException(e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return retorno;
    }

    //Metodo para borrar usuario
    //Devuelve true si el usuario es borrado con exito
    public boolean delete(int id_usuario) {

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE); //Se prepara la sentencia UPDATE
            stmt.setInt(1, id_usuario);
            int filas = stmt.executeUpdate();
            retorno = filas > 0;


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al borrar usuarios", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);

        }
        return retorno;
    }

    public Usuarios select(int id_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usuario = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTipoUsuario(Usuarios.TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar préstamo", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    public Usuarios loginUser(String correo, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usuario = null;

        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_LOGIN);
            stmt.setString(1, correo); //Se hace la consulta mediante el correo ingresado
            rs = stmt.executeQuery();//Ejecuta la consulta

            if (rs.next()) { //Valida si devuleve un resultado la consulta
                String hashAlmacenado = rs.getString("password"); //Guarda la clave encriptada en un variable para luego validar
                if (BCrypt.checkpw(pass, hashAlmacenado)) { //Valida la clave y si esta es correcta devuelve true
                    usuario = new Usuarios();
                    usuario.setNombre(rs.getString("nombre")); //Cuando la clave es correcta devuelve el nombre del usuario
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error con el login", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
            Conexion.close(rs);
        }
        return usuario;
    }
    // Metodo para resetear la contraseña, desde un administrador
    public boolean ResetPass(String correo, String nuevoPass) { //Solicita el correo y la nueva contraseña a asignar
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try{
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_RESET_PASS);
            stmt.setString(1, BCrypt.hashpw(nuevoPass, BCrypt.gensalt())); //Se setea la nueva contraseña ya con hash
            stmt.setString(2, correo);

            int filas = stmt.executeUpdate();
            retorno = filas > 0; //Devuelve true si se cambia la contraseña


        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al resetear contraseña", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return retorno;


    }

    // Metodo para resetear la contraseña, desde un usuarios
    public boolean CambioPass(String correo, String passAterior, String nuevoPass) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean retorno = false;
        try{
        Usuarios usuarioValidado = loginUser(correo, passAterior); //Primero valida que la contraseña anterior sea valida
        if (usuarioValidado != null) {//Si el resultado es correcto procede a hacer un cambio de contraseña
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_RESET_PASS);
            stmt.setString(1, BCrypt.hashpw(nuevoPass, BCrypt.gensalt()));
            stmt.setString(2, correo);

            int filas = stmt.executeUpdate();
            retorno = filas > 0;
        }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al cambiar la contraseña", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return retorno;
    }

    public DefaultTableModel selectUsuarios() {
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




}

