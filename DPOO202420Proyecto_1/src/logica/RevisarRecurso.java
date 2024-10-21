package logica;

public class RevisarRecurso extends Actividad{
	
	private String tipo;

	public RevisarRecurso(int idActividad, String titulo, String descripcion, String objetivos, String dificultad, String dificultadEsperada, String resultado, String tipo) {
		this.idActividad = idActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.dificultadEsperada = dificultadEsperada;
		this.resultado = resultado;
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
