package logica;

import java.util.ArrayList;

public class Encuesta extends Actividad{
	
	private int duracion;

	public Encuesta(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, int duracion) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado, resenias,
				actividadesPrevias, learningPaths);
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}	

}
