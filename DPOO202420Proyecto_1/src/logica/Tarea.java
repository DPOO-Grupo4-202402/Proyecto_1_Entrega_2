package logica;

import java.util.ArrayList;
import java.util.Date;

public class Tarea extends Actividad{
	
	private Date fechaEntrega;

	public Tarea(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, Date fechaEntrega) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado, resenias,
				actividadesPrevias, learningPaths);
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
}
