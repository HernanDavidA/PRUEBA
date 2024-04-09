package entity;

import java.sql.Date;

public class Contratacion {

    private int id;
    private int idCoder;
    private int idVacante;
    private Date fecha_aplicacion;
    private String estado;
    private double salario;

    public Contratacion() {
    }

    public Contratacion(int id, int idCoder, int idVacante, Date fecha_aplicacion, String estado, double salario) {
        this.id = id;
        this.idCoder = idCoder;
        this.idVacante = idVacante;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCoder() {
        return idCoder;
    }

    public void setIdCoder(int idCoder) {
        this.idCoder = idCoder;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public Date getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(Date fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "id=" + id +
                ", idCoder=" + idCoder +
                ", idVacante=" + idVacante +
                ", fecha_aplicacion=" + fecha_aplicacion +
                ", estado='" + estado + '\'' +
                ", salario=" + salario +
                '}';
    }
}
