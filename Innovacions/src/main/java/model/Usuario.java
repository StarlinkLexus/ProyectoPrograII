package model;

public abstract class Usuario {

    // Atributo del usuario: nombre
    protected String nombre;

    /*
     * Constructor del usuario.
     * Inicializa el nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre como representacion en texto del objeto.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
