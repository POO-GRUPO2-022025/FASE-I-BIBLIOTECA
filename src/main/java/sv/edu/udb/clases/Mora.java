package sv.edu.udb.clases;

import java.math.BigDecimal;

public class Mora {

    private int idMora;
    private int anio_aplicable;
    private Usuarios.TipoUsuario tipoUsuario;
    private BigDecimal tarifaDiaria;

    public Mora() {}

    public Mora(int idMora, int anio_aplicable, Usuarios.TipoUsuario tipoUsuario,
                BigDecimal tarifaDiaria) {

        this.idMora = idMora;
        this.anio_aplicable = anio_aplicable;
        this.tipoUsuario = tipoUsuario;
        this.tarifaDiaria = tarifaDiaria;
    }

    public int getIdMora() {
        return idMora;
    }

    public void setIdMora(int idMora) {
        this.idMora = idMora;
    }

    public int getanio_aplicable() {
        return anio_aplicable;
    }

    public void setanio_aplicable(int anio_aplicable) {
        this.anio_aplicable = anio_aplicable;
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
