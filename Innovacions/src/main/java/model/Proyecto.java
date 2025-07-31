package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un proyecto generico. Puede ser un proyecto de
 * investigacion o de emprendimiento.
 */
public abstract class Proyecto {
	
	//Atributos
	protected String nombre;
	protected String descripcion;
	protected String urlVideo;
	protected Investigador responsable;
	protected List<Colaborador> colaboradores = new ArrayList<>();

	/**
	 * Constructor del proyecto con datos esenciales.
	 * 
	 * @param nombre      Nombre del proyecto
	 * @param descripcion Descripcion general del proyecto
	 * @param urlVideo    Enlace a un video relacionado
	 * @param responsable Investigador encargado del proyecto
	 */
	public Proyecto(String nombre, String descripcion, String urlVideo, Investigador responsable) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.urlVideo = urlVideo;
		this.responsable = responsable;
	}

	// Métodos getter para obtener los atributos del proyecto

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public Investigador getResponsable() {
		return responsable;
	}

	/**
	 * Agrega un colaborador al proyecto si aún no está en la lista.
	 * 
	 * @param c Colaborador a añadir
	 */
	public void agregarColaborador(Colaborador c) {
		if (c != null && !colaboradores.contains(c)) {
			colaboradores.add(c);
		}
	}

	/**
	 * Devuelve la lista de colaboradores actuales del proyecto.
	 */
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	// Devuelve el nombre del proyecto al convertir el objeto a String
	@Override
	public String toString() {
		return nombre;
	}
}