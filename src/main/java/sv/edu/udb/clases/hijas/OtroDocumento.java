package sv.edu.udb.clases.hijas;


import sv.edu.udb.clases.Material;

public class OtroDocumento extends Material {
    private String descripcion;

    public OtroDocumento(int idMaterial, TipoMaterial tipoMaterial, String ubicacion,
                         int cantidadTotal, int cantidadDisponible,
                         int cantidadPrestada, int cantidadDaniada,
                         String descripcion) {
        super(idMaterial, tipoMaterial, ubicacion, cantidadTotal, cantidadDisponible, cantidadPrestada, cantidadDaniada);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
