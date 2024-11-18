package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Tarea extends Actividad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaEntrega;
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
			Date fechaEntrega) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado);
		this.fechaEntrega = fechaEntrega;
		this.setEstado("Enviada");
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	//Metodos para manejar entregas.
	public boolean entregadaATiempo(Date fechaEntregaEstudiante) {
		return !fechaEntregaEstudiante.after(this.fechaEntrega);
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
