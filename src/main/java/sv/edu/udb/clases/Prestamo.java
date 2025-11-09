package sv.edu.udb.clases;

import java.math.BigDecimal;
import java.sql.Date;

public class Prestamo {
    public enum Estado{
        Pendiente,
        En_Curso,
        Devuelto,
        Denegado
    }

    private int idPrestamo;
    private Usuarios usuario;
    private Material material;
    private Mora mora;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private BigDecimal moraTotal;
    private Estado estado;

    // Constructor vacío
    public Prestamo() {}

    // Constructor completo
    public Prestamo(int idPrestamo, Usuarios usuario, Material material,
                    Mora mora, Date fechaPrestamo, Date fechaDevolucion,
                    BigDecimal moraTotal, Estado estado) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.material = material;
        this.mora = mora;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.moraTotal = moraTotal;
        this.estado = estado;
    }



    //Metodos Setters y Getters

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Mora getMora() {
        return mora;
    }

    public void setMora(Mora mora) {
        this.mora = mora;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public BigDecimal getMoraTotal() {
        return moraTotal;
    }

    public void setMoraTotal(BigDecimal moraTotal) {
        this.moraTotal = moraTotal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}


