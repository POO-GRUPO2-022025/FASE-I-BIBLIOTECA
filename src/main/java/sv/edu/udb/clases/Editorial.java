package sv.edu.udb.clases;

public class Editorial {
    private int idEditorial;
    private String nombre;
    private String pais;

    public Editorial() {
    }

    public Editorial(int idEditorial, String nombre, String pais) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Editorial(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
