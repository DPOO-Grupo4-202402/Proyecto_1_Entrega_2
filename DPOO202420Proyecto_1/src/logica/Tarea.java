package logica;

import java.util.Date;

public class Tarea extends Actividad{
	
	private Date fechaEntrega;

	public Tarea(int idActividad, String titulo, String descripcion, String objetivos, String dificultad, String dificultadEsperada, String resultado, Date fechaEntrega) {
		this.idActividad = idActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.dificultadEsperada = dificultadEsperada;
		this.resultado = resultado;
		this.setFechaEntrega(fechaEntrega);
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
}
