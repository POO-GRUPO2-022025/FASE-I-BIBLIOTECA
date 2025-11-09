package sv.edu.udb.clases.hijas;

import sv.edu.udb.clases.Material;

import java.sql.Date;

public class Revista extends Material {
    private String volumen;
    private String numero;
    private Date fechaPublicacion;


    public Revista(int idMaterial, TipoMaterial tipoMaterial, String ubicacion,
                   int cantidadTotal, int cantidadDisponible,
                   int cantidadPrestada, int cantidadDaniada,
                   String volumen, String numero, Date fechaPublicacion) {
        super(idMaterial, tipoMaterial, ubicacion, cantidadTotal, cantidadDisponible, cantidadPrestada, cantidadDaniada);
        this.volumen = volumen;
        this.numero = numero;
        this.fechaPublicacion = fechaPublicacion;
    }

    //Metodos Getters y Setter

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
