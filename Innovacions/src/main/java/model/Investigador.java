package model;

public class Investigador extends Usuario {

    // Atributos del investigador: pagina web y area de experiencia
    private String paginaWeb;
    private String areaExperiencia;

    /*
     * Constructor del investigador.
     * Inicializa los valores de nombre, paginaWeb y areaExperiencia.
     */
    public Investigador(String nombre, String paginaWeb, String areaExperiencia) {
        super(nombre);
        this.paginaWeb = paginaWeb;
        this.areaExperiencia = areaExperiencia;
    }

    /**
     * Obtiene la URL de la pagina web del investigador.
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * Establece la URL de la pagina web del investigador.
     */
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * Obtiene el área de experiencia del investigador.
     */
    public String getAreaExperiencia() {
        return areaExperiencia;
    }

    /**
     * Establece el área de experiencia del investigador.
     */
    public void setAreaExperiencia(String areaExperiencia) {
        this.areaExperiencia = areaExperiencia;
    }

    /**
     * Muestra el nombre del investigador seguido de su área de experiencia.
     */
    @Override
    public String toString() {
        return nombre + " - " + areaExperiencia;
    }
}
