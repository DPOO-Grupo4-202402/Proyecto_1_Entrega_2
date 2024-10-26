package logica;

import java.util.ArrayList;

public class RevisarRecurso extends Actividad{
	
	private String tipo;

	public RevisarRecurso(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, String tipo) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void marcarRevisado() {
		this.resultado = "Revisado";
	}
	
	public String obtenerResultado() {
		return this.resultado;
	}

	@Override
	public void ejecutar() {
		// Logica de ejecucion.
		marcarRevisado();
		System.out.println("Encuesta completada: " + this.getTitulo() + ", Estado: " + this.resultado);
	}
	
	

}