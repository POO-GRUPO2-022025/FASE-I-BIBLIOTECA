package sv.edu.udb.clases;

import java.math.BigDecimal;
import java.sql.Date;

public class Mora {

    private int idMora;
    private Date fechaInicio;
    private Usuarios.TipoUsuario tipoUsuario;
    private BigDecimal tarifaDiaria;

    public Mora(int idMora, Date fechaInicio, Usuarios.TipoUsuario tipoUsuario,
                BigDecimal tarifaDiaria) {

        this.idMora = idMora;
        this.fechaInicio = fechaInicio;
        this.tipoUsuario = tipoUsuario;
        this.tarifaDiaria = tarifaDiaria;
    }

    public int getIdMora() {
        return idMora;
    }

    public void setIdMora(int idMora) {
        this.idMora = idMora;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Usuarios.TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Usuarios.TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public BigDecimal getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setTarifaDiaria(BigDecimal tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }
}
