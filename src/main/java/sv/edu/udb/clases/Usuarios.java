package sv.edu.udb.clases;

public class Usuarios {
    public enum TipoUsuario{
        Encargado,
        Profesor,
        Alumno
    }

    private int idUsuario;
    private String nombre;
    private String correo;
    private String password;
    private TipoUsuario tipoUsuario;

    public Usuarios(int idUsuario, String nombre, String correo, String password, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.tipoUsuario = TipoUsuario.Encargado;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
