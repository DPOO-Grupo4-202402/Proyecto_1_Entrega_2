package logica;

import java.util.ArrayList;

public class Examen extends Actividad{

	private int duracion;
	private double calificacionObtenida;
	private double calificacionMinima;
	private String estado;
	private ArrayList<PreguntaAbierta> preguntas;

	public Examen(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, int duracion, double calificacionObtenida, double calificacionMinima, String estado) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.duracion = duracion; 
		this.setEstado("Enviado");
		this.setCalificacionObtenida(calificacionObtenida);
		this.setCalificacionMinima(calificacionMinima);
		this.setPreguntas(new ArrayList<>());
	} 
	
	//Getters y Setters.
	public ArrayList<PreguntaAbierta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(ArrayList<PreguntaAbierta> preguntas) {
		this.preguntas = preguntas;
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

	public double getCalificacionMinima() {
		return calificacionMinima;
	}

	public void setCalificacionMinima(double calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	//Metodos para cambiar el estado.
	public void marcarExitosa() {
		this.estado = "Exitoso";
	}
	
	public void marcarNoExitosa() {
		this.estado = "No exitosa";
	}

	//Metodo para verificar si el examen fue aprobado.
	public boolean aprobado (double calificacionObtenida ) {
		if (calificacionObtenida >= this.calificacionMinima) {
			marcarExitosa();
			return true;
		} else {
			marcarNoExitosa();
			return false;
		}
		
	}
	
	@Override
	public void ejecutar() {
		// logica para ejecutar
		System.out.println("Examen completado: " + this.getTitulo() + ", Estado: " + this.resultado);
		
	}

	
}
