package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que funciona como repositorio central de datos del sistema.
 * Contiene listas estaticas de investigadores, colaboradores y proyectos.
 */
public class Repositorio {

    // Listas estaticas que almacenan los datos principales del sistema
    public static List<Investigador> investigadores = new ArrayList<>();
    public static List<Colaborador> colaboradores = new ArrayList<>();
    public static List<Proyecto> proyectos = new ArrayList<>();

    /**
     * Inicializa algunos datos de ejemplo si las listas estan vacias.
     * Agrega investigadores y colaboradores predefinidos.
     */
    public static void inicializarDatos() {
        if (investigadores.isEmpty()) {
            investigadores.add(new Investigador("Ana", "https://ana.com", "Biologia"));
            investigadores.add(new Investigador("Luis", "https://luis.com", "Ingenieria"));
        }

        if (colaboradores.isEmpty()) {
            colaboradores.add(new Colaborador("Carlos"));
            colaboradores.add(new Colaborador("Maria"));
            colaboradores.add(new Colaborador("Pedro"));
            colaboradores.add(new Colaborador("Sofia"));
            colaboradores.add(new Colaborador("Jorge"));
        }
    }
}
