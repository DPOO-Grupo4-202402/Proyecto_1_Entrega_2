package logica;

import java.util.ArrayList;

public abstract class Actividad {

	protected int idActividad;
	protected String titulo;
	protected String descripcion;
	protected String objetivos;
	protected String dificultad;
	protected String dificultadEsperada;
	protected String resultado;
	protected ArrayList<Resenia> resenias;
	protected ArrayList<Actividad> actividadesPrevias;
	protected ArrayList<LearningPath> learningPaths;
	
	public int getIdActividad() {
		return idActividad;
	}
	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public String getDificultadEsperada() {
		return dificultadEsperada;
	}
	public void setDificultadEsperada(String dificultadEsperada) {
		this.dificultadEsperada = dificultadEsperada;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public ArrayList<Resenia> getResenias() {
		return resenias;
	}
	public void setResenias(ArrayList<Resenia> resenias) {
		this.resenias = resenias;
	}
	public ArrayList<Actividad> getActividadesPrevias() {
		return actividadesPrevias;
	}
	public void setActividadesPrevias(ArrayList<Actividad> actividadesPrevias) {
		this.actividadesPrevias = actividadesPrevias;
	}
	public ArrayList<LearningPath> getLearningPaths() {
		return learningPaths;
	}
	public void setLearningPaths(ArrayList<LearningPath> learningPaths) {
		this.learningPaths = learningPaths;
	}
	
	
}
