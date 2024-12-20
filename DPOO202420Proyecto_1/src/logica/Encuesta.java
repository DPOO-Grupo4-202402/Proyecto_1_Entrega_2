package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Encuesta extends Actividad implements Serializable{
	
	private int duracion;
	private ArrayList<PreguntaAbierta> preguntas;

	public Encuesta(int idActividad, 
			String titulo, 
			String descripcion, 
			String objetivos, 
			String dificultad,
			int duracion,
			String resultado, 
			ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, 
			ArrayList<LearningPath> learningPaths 
			) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.duracion = duracion;
		this.preguntas = new ArrayList<>();
	}
	
	public ArrayList<PreguntaAbierta> getPreguntas(){
		return preguntas;
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
