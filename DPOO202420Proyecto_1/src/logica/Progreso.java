package logica;

import java.util.Date;
import java.util.List;

public class Progreso {
	
	private Date fechaIniciado;
	private Date fechaCompletado;
	private String resultado;
	private Usuario usuario;
	private int actividadesObligatorias;
	private int actividadesCompletadas;
	private LearningPath learningPath;
	private double porcentajeCompletado;
	
	public Progreso(Date fechaIniciado, Usuario usuario, LearningPath learningPath) {
		this.fechaIniciado = fechaIniciado;
		this.fechaCompletado = null;
		this.resultado = "En progreso";
		this.setUsuario(usuario);
		this.learningPath = learningPath;
		this.porcentajeCompletado = 0.0;
	}
	
	//Getters y Setters.
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getActividadesObligatorias() {
		return actividadesObligatorias;
	}

	public void setActividadesObligatorias(int actividadesObligatorias) {
		this.actividadesObligatorias = actividadesObligatorias;
	}

	public int getActividadesCompletadas() {
		return actividadesCompletadas;
	}

	public void setActividadesCompletadas(int actividadesCompletadas) {
		this.actividadesCompletadas = actividadesCompletadas;
	}

	public LearningPath getLearningPath() {
		return learningPath;
	}

	public void setLearningPath(LearningPath learningPath) {
		this.learningPath = learningPath;
	}

	public double getPorcentajeCompletado() {
		return porcentajeCompletado;
	}

	public void setPorcentajeCompletado(double porcentajeCompletado) {
		this.porcentajeCompletado = porcentajeCompletado;
	}
	
	public void actualizarProgreso() {
		List<Actividad> actividadesObligatorias = learningPath.getActividades();
		int totalActividades = actividadesObligatorias.size();
		int activadesCompletadas = 0;
		
		for (Actividad actividad : actividadesObligatorias) {
			if (actividad.esCompletada()) {
				activadesCompletadas++;
			}
		}
		
		if (totalActividades > 0) {
			this.porcentajeCompletado = (actividadesCompletadas / (double) totalActividades) * 100;
		} else {
			this.porcentajeCompletado = 100.0; //Si no hay actividades se considera que esta completado.
		}
		
		if (this.porcentajeCompletado == 100.0) {
			this.fechaCompletado = new Date();
			this.resultado = "Completado";	
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
