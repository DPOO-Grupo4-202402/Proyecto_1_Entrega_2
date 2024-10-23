package logica;

import java.util.Date;

public class Progreso {
	
	private Date fechaIniciado;
	private Date fechaCompletado;
	private String resultado;
	
	public Progreso(Date fechaIniciado, Date fechaCompletado, String resultado) {
		this.fechaIniciado = fechaIniciado;
		this.fechaCompletado = fechaCompletado;
		this.resultado = resultado;
	}
	public Date getFechaIniciado() {
		return fechaIniciado;
	}
	public void setFechaIniciado(Date fechaIniciado) {
		this.fechaIniciado = fechaIniciado;
	}
	public Date getFechaCompletado() {
		return fechaCompletado;
	}
	public void setFechaCompletado(Date fechaCompletado) {
		this.fechaCompletado = fechaCompletado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
}
