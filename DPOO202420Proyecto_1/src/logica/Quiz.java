package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz extends Actividad implements Serializable{

	private double calificacionMinima;
	private int duracion;
	private double calificacionObtenida;
	private ArrayList<PreguntaVerdaderoFalso> preguntasCerradas;

	public Quiz(int idActividad, 
			String titulo, 
			String descripcion, 
			String objetivos, 
			String dificultad,
			int duracion,
			String resultado, 
			ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, 
			ArrayList<LearningPath> learningPaths, 
			double calificacionMinima, 
			double calificacionObtenida) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.duracion = duracion;
		this.calificacionMinima = calificacionMinima;
		this.setCalificacionObtenida(calificacionObtenida);
		this.setPreguntasCerradas(new ArrayList<>());
	}

	//Getters y Setters.
	public double getCalificacionMinima() {
		return calificacionMinima;
	}

	public void setCalificacionMinima(double calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getCalificacionObtenida() {
		return calificacionObtenida;
	}

	public void setCalificacionObtenida(double calificacionObtenida) {
		this.calificacionObtenida = calificacionObtenida;
	}
	
	public ArrayList<PreguntaVerdaderoFalso> getPreguntasCerradas() {
		return preguntasCerradas;
	}

	public void setPreguntasCerradas(ArrayList<PreguntaVerdaderoFalso> preguntasCerradas) {
		this.preguntasCerradas = preguntasCerradas;
	}
	
	//Calificar quiz.
	public double calificarQuiz(ArrayList<String> respuestaEstudiante) {
		int correctas = 0;
		for (int i = 0; i < preguntasCerradas.size(); i++) {
			if (preguntasCerradas.get(i).esCorrecta()) {
				correctas++;
			}
		}
		double porcentaje = (double) (correctas / preguntasCerradas.size()) * 100;
		
		if (porcentaje >= this.calificacionMinima) {
			this.resultado = "Aprobado";
		} else {
			this.resultado = "No Aprobado";
		}
		return porcentaje;
	}
	
	//Metodos para manejar preguntas.
	public void agregarPregunta(PreguntaVerdaderoFalso pregunta) {
		preguntasCerradas.add(pregunta);
	}
	
	public void eliminarPregunta(PreguntaVerdaderoFalso pregunta)
	{
		preguntasCerradas.remove(pregunta);
	}
	
	public PreguntaVerdaderoFalso obtenerPregunta(int indice) {
		return preguntasCerradas.get(indice);
	}
	@Override
	public void ejecutar() {
		//Logica de ejecucion.
		System.out.println("Quiz completado: " + this.getTitulo() + ", Estado: " + this.resultado);
	}
}