package sv.edu.udb.Datos;

import sv.edu.udb.clases.Usuarios;

import java.sql.*;

public class UsuariosDB {

    private final String SQL_INSERT = "INSERT INTO usuarios(nombre,tipo_usuario,correo,password) VALUES(?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE usuarios SET nombre=?, tipo_usuario=?, correo=?, password=? WHERE id_usuario=?\n";
    private final String SQL_DELETE = "DELETE FROM usuarios where id_usuario=?";
    private final String SQL_SELECT = "SELECT id_usuario,nombre,tipo_usuario,correo,password FROM usuarios WHERE id_usuario=?";

    //Metodo insert crea un nuevo usuario en la base de datos y devuelve el objeto completo con los datos

    public Usuarios insert(Usuarios usuarios) {
        //Declaracion de los recursos
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet IdGenerado = null;
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

        } catch (SQLException | ClassNotFoundException e){ //Proceso para capturar errores y manda un mensaje
            throw new RuntimeException("Error al añadir usuario",e);
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
            stmt.setString(4, usuario.getPassword());
            stmt.setInt(5, usuario.getIdUsuario());
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
            throw new RuntimeException("Error al borrar usuarios",e);
        }finally {
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

        try{
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            if(rs.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTipoUsuario(Usuarios.TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al consultar préstamo", e);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }




}
