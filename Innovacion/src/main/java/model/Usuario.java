package model;

import java.util.Objects;

public abstract class Usuario { // Clase abstracta ya que no instanciamos un Usuario directamente
    private String nombre;

    public Usuario(String nombre) {
        // Validación básica para asegurar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "nombre='" + nombre + '\'' +
               '}';
    }
}