package logica;

import java.util.ArrayList;

public class Examen extends Actividad{

	private int duracion;
	private int calificacionObtenida;
	private int calificacionMinima;

	public Examen(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, int duracion, int calificacionObtenida, int calificacionMinima) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado, resenias,
				actividadesPrevias, learningPaths);
		this.duracion = duracion; 
		this.setCalificacionObtenida(calificacionObtenida);
		this.setCalificacionMinima(calificacionMinima);
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCalificacionObtenida() {
		return calificacionObtenida;
	}

	public void setCalificacionObtenida(int calificacionObtenida) {
		this.calificacionObtenida = calificacionObtenida;
	}

	public int getCalificacionMinima() {
		return calificacionMinima;
	}

	public void setCalificacionMinima(int calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}

	
	
	
}
