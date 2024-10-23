package logica;

import java.util.ArrayList;
import java.util.Date;

public class Tarea extends Actividad{
	
	private Date fechaEntrega;
	private String estado;

	public Tarea(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			String dificultadEsperada, String resultado, ArrayList<Resenia> resenias,
			ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths, Date fechaEntrega) {
		super(idActividad, titulo, descripcion, objetivos, dificultad, resultado, resenias,
				actividadesPrevias, learningPaths);
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
		return fechaEntregaEstudiante.before(this.fechaEntrega);
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
