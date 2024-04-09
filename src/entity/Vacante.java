package entity;

public class Vacante {

    private int id;
    private int idEmpresa;
    private String titulo;
    private String duracion;
    private String descripcion;
    private String tecnologia;
    private String estado;

    public Vacante() {
    }

    public Vacante(int id, int idEmpresa, String titulo, String duracion, String descripcion, String tecnologia, String estado) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.titulo = titulo;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.tecnologia = tecnologia;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int Empresa) {
        this.idEmpresa = Empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vacante{" +
                "id=" + id +
                ", idEmpresa=" + idEmpresa +
                ", titulo='" + titulo + '\'' +
                ", duracion='" + duracion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
