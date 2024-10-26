package logica;

import java.util.ArrayList;

public class Encuesta extends Actividad{
	
	private int duracion;
	private ArrayList<PreguntaAbierta> preguntas;

	public Encuesta(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, int duracion) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}	
	
	//Metodos para manejar preguntas.
	public void agregarPregunta(PreguntaAbierta pregunta) {
		preguntas.add(pregunta);
	}
	
	public void eliminarPregunta(Pregunta pregunta) {
		preguntas.remove(pregunta);
	}
	
	public void marcarCompletada() {
		this.resultado = "Completada";
	}

	@Override
	public void ejecutar() {
		// logica de ejecucion.
		marcarCompletada();
		System.out.println("Encuesta completada: " + this.getTitulo() + ", Estado: " + this.resultado);
		
	}

}
