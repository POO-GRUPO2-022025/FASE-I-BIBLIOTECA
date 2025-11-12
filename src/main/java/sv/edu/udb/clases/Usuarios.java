package sv.edu.udb.clases;

import org.mindrot.jbcrypt.BCrypt;

public class Usuarios {
    public enum TipoUsuario{
        Encargado,
        Profesor,
        Alumno
    }

    private int idUsuario;
    private String nombre;
    private String correo;
    private String passwordHash;
    private TipoUsuario tipoUsuario;

    public Usuarios(){}

    public Usuarios(int idUsuario, String nombre, String correo, String password, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.passwordHash = encriptarPass(password); //Se encripta la clave mediante el metodo
        this.tipoUsuario = tipoUsuario;
    }
    // Metodo para encriptar la clave
    public String encriptarPass(String passPlano){
        return BCrypt.hashpw(passPlano, BCrypt.gensalt());
    }
    //Metodo para verificar el PASS
    public boolean verificarPass(String passPlano){
        return BCrypt.checkpw(passPlano, this.passwordHash);
    }
    //Métodos getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //El siguiente metodo get para la clave no debe usarse para la interfaz grafica
    public String getPassword() {
        return passwordHash;
    } //El metodo devuelve la clave encriptada
    public void setPassword(String password) {

        this.passwordHash = encriptarPass(password); // Cuando se usa el metodo set, se encrypta la clave
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


}
