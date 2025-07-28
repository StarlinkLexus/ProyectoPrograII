package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Proyecto { // Clase abstracta ya que no instanciamos un Proyecto directamente
    private String nombre;
    private String descripcion;
    private String urlVideoExplicativo;
    private Investigador investigadorResponsable;
    private List<Investigador> investigadoresAsociados; // Máximo 4

    public Proyecto(String nombre, String descripcion, String urlVideoExplicativo, Investigador investigadorResponsable) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede ser nulo o vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del proyecto no puede ser nula o vacía.");
        }
        if (urlVideoExplicativo == null || urlVideoExplicativo.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL del video explicativo no puede ser nula o vacía.");
        }
        if (investigadorResponsable == null) {
            throw new IllegalArgumentException("El investigador responsable no puede ser nulo.");
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlVideoExplicativo = urlVideoExplicativo;
        this.investigadorResponsable = investigadorResponsable;
        this.investigadoresAsociados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proyecto no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del proyecto no puede ser nula o vacía.");
        }
        this.descripcion = descripcion;
    }

    public String getUrlVideoExplicativo() {
        return urlVideoExplicativo;
    }

    public void setUrlVideoExplicativo(String urlVideoExplicativo) {
        if (urlVideoExplicativo == null || urlVideoExplicativo.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL del video explicativo no puede ser nula o vacía.");
        }
        this.urlVideoExplicativo = urlVideoExplicativo;
    }

    public Investigador getInvestigadorResponsable() {
        return investigadorResponsable;
    }

    public void setInvestigadorResponsable(Investigador investigadorResponsable) {
        if (investigadorResponsable == null) {
            throw new IllegalArgumentException("El investigador responsable no puede ser nulo.");
        }
        this.investigadorResponsable = investigadorResponsable;
    }

    public List<Investigador> getInvestigadoresAsociados() {
        return new ArrayList<>(investigadoresAsociados); // Devuelve una copia para evitar modificación externa
    }

    public void agregarInvestigadorAsociado(Investigador investigador) {
        if (investigador == null) {
            throw new IllegalArgumentException("El investigador asociado no puede ser nulo.");
        }
        if (investigadoresAsociados.size() >= 4) {
            throw new IllegalStateException("Un proyecto no puede tener más de 4 investigadores asociados.");
        }
        if (investigador.equals(this.investigadorResponsable)) {
            throw new IllegalArgumentException("El investigador responsable no puede ser un investigador asociado.");
        }
        if (this.investigadoresAsociados.contains(investigador)) {
            throw new IllegalArgumentException("El investigador ya está asociado a este proyecto.");
        }
        this.investigadoresAsociados.add(investigador);
    }

    public void removerInvestigadorAsociado(Investigador investigador) {
        if (investigador == null) {
            throw new IllegalArgumentException("El investigador asociado no puede ser nulo.");
        }
        this.investigadoresAsociados.remove(investigador);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(nombre, proyecto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
               "nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", urlVideoExplicativo='" + urlVideoExplicativo + '\'' +
               ", investigadorResponsable=" + (investigadorResponsable != null ? investigadorResponsable.getNombre() : "N/A") +
               ", investigadoresAsociados=" + investigadoresAsociados.size() +
               '}';
    }
}