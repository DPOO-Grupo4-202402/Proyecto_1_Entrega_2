package logica;

import java.util.ArrayList;

public class Recomendador {

	private ArrayList<Actividad> actividades;
	private ArrayList<LearningPath> learningPaths;
	private ArrayList<Estudiante> estudiantes;
	
	public Recomendador(ArrayList<Actividad> actividades, ArrayList<LearningPath> learningPaths,
			ArrayList<Estudiante> estudiantes) {
		this.actividades = actividades;
		this.learningPaths = learningPaths;
		this.estudiantes = estudiantes;
	}
	
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}
	
	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public ArrayList<LearningPath> getLearningPaths() {
		return learningPaths;
	}
	
	public void setLearningPaths(ArrayList<LearningPath> learningPaths) {
		this.learningPaths = learningPaths;
	}
	
	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	
	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
}
