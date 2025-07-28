package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Proyecto {
    protected String nombre;
    protected String descripcion;
    protected String videoURL;
    protected Investigador responsable;
    protected List<Investigador> asociados = new ArrayList<>();

    public Proyecto(String nombre, String descripcion, String videoURL, Investigador responsable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.videoURL = videoURL;
        this.responsable = responsable;
    }

    public void agregarAsociado(Investigador i) {
        if (asociados.size() < 4) {
            asociados.add(i);
        }
    }
}