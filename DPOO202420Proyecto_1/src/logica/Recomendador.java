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
	
	public ArrayList<LearningPath> recomendarLearningPaths(Estudiante estudiante, ArrayList<LearningPath> learningPahsDisponibles) throws Exception{
		ArrayList<LearningPath> learningPathsRecomendados = new ArrayList<>();
		
		if (estudiante.getIntereses() == null || estudiante.getIntereses().trim().isEmpty()) {
			return learningPathsRecomendados;
		}
		
		for(LearningPath lp : learningPahsDisponibles) {
			if(contieneIntereses(estudiante.getIntereses(), lp.getDescripcion())) {
				learningPathsRecomendados.add(lp);
			} 
		}
		return learningPathsRecomendados;
	}
	
	public boolean contieneIntereses(String intereses, String descripcionLearningPath) {
		String[] listaIntereses = intereses.toLowerCase().split(",");//Los intereses os estudiantes los deben agregar separados por comas.
		String descripcion = descripcionLearningPath.toLowerCase();
		
		for (String interes : listaIntereses) {
			if (descripcion.contains(interes.trim())) {
				return true;
			}
		}
		return false;
	}
}
