package model;

public class Investigador extends Usuario {
    private String paginaWeb;
    private String areaExperiencia;

    public Investigador(String nombre, String paginaWeb, String areaExperiencia) {
        super(nombre);
        this.paginaWeb = paginaWeb;
        this.areaExperiencia = areaExperiencia;
    }
}