package model;

public class Investigador extends Usuario {
    private String paginaWeb;
    private String areaExperiencia;

    public Investigador(String nombre, String paginaWeb, String areaExperiencia) {
        super(nombre);
        this.paginaWeb = paginaWeb;
        this.areaExperiencia = areaExperiencia;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getAreaExperiencia() {
        return areaExperiencia;
    }

    public void setAreaExperiencia(String areaExperiencia) {
        this.areaExperiencia = areaExperiencia;
    }

    @Override
    public String toString() {
        return "Investigador{" +
               "nombre='" + getNombre() + '\'' +
               ", paginaWeb='" + paginaWeb + '\'' +
               ", areaExperiencia='" + areaExperiencia + '\'' +
               '}';
    }
}