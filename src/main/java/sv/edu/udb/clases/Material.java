package sv.edu.udb.clases;

public class Material {
    public enum TipoMaterial {
        LIBRO,
        REVISTA,
        AUDIOVISUAL,
        OTRO_DOCUMENTO
    }

    private int idMaterial;
    private TipoMaterial tipoMaterial;
    private String ubicacion;
    private int cantidadTotal;
    private int cantidadDisponible;
    private int cantidadPrestada;
    private int cantidadDaniada;


    public Material(int idMaterial, TipoMaterial tipoMaterial, String ubicacion,
                    int cantidadTotal, int cantidadDisponible,
                    int cantidadPrestada, int cantidadDaniada) {
        this.idMaterial = idMaterial;
        this.tipoMaterial = tipoMaterial;
        this.ubicacion = ubicacion;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadPrestada = cantidadPrestada;
        this.cantidadDaniada = cantidadDaniada;
    }

    //Metodos Getters y Setters
    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadPrestada() {
        return cantidadPrestada;
    }

    public void setCantidadPrestada(int cantidadPrestada) {
        this.cantidadPrestada = cantidadPrestada;
    }

    public int getCantidadDaniada() {
        return cantidadDaniada;
    }

    public void setCantidadDaniada(int cantidadDaniada) {
        this.cantidadDaniada = cantidadDaniada;
    }

}
