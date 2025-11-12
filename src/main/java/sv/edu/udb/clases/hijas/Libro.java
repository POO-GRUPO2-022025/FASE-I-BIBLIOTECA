package sv.edu.udb.clases.hijas;

import sv.edu.udb.clases.Material;

public class Libro extends Material {
    private String autor;
    private String editorial;
    private String isbn;

    public Libro(int idMaterial, TipoMaterial tipoMaterial, String titulo, String ubicacion,
                 int cantidadTotal, int cantidadDisponible,
                 int cantidadPrestada, int cantidadDaniada,
                 String autor, String editorial, String isbn) {
        super(idMaterial, tipoMaterial,titulo, ubicacion, cantidadTotal, cantidadDisponible, cantidadPrestada, cantidadDaniada);
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }
    public Libro(){}

    //Metodos Getters y Setter

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
