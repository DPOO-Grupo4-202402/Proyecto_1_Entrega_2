package logica;


public class Examen extends Actividad{

	private int duracion;

	public Examen(int idActividad, String titulo, String descripcion, String objetivos, String dificultad, String dificultadEsperada, String resultado, int duracion) {
		this.idActividad = idActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.dificultadEsperada = dificultadEsperada;
		this.resultado = resultado;
		this.setDuracion(duracion);
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	
	
	
}
