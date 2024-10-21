package logica;

public class Quiz extends Actividad{

	private int calificacionMinima;
	private int duracion;
	
	public Quiz(int idActividad, String titulo, String descripcion, String objetivos, String dificultad, String dificultadEsperada, String resultado, int calificacionMinima, int duracion) {
		this.idActividad = idActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.dificultadEsperada = dificultadEsperada;
		this.resultado = resultado;
		this.calificacionMinima = calificacionMinima;
		this.setDuracion(duracion);
	
	}

	public int getCalificacionMinima() {
		return calificacionMinima;
	}

	public void setCalificacionMinima(int calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	
}
