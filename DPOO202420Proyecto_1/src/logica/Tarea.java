package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Tarea extends Actividad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate fechaEntrega;
	private String estado;

	public Tarea(int idActividad, 
			String titulo, 
			String descripcion, 
			String objetivos, 
			String dificultad,
			int tiempoEsperado, 
			String resultado, 
			ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, 
			ArrayList<LearningPath> learningPaths, 
			LocalDate fechaEntrega) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.fechaEntrega = fechaEntrega;
		this.setEstado("Enviada");
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	//Metodos para manejar entregas.
	public boolean entregadaATiempo(LocalDate fechaEntregaEstudiante) {
		return !fechaEntregaEstudiante.isAfter(this.fechaEntrega);
	}

	public void marcarExitosa() {
		this.estado = "Exitosa";
	}

	public void marcarNoExitosa() {
		this.estado = "No exitosa";
	}

	@Override
	public void ejecutar() {
		// logica de ejecucion.
		System.out.println("Ejecutando la tarea: " + this.getTitulo() + ", Estado: " + this.estado);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
