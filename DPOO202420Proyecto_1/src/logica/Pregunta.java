package logica;

public abstract class Pregunta {
	
	protected int idOpcion;
	protected String textoOpcion;
	protected int puntaje;
	
	public Pregunta(int idOpcion, String textoOpcion, int puntaje) {
		this.idOpcion = idOpcion;
		this.textoOpcion = textoOpcion;
		this.puntaje = puntaje;
	}

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getTextoOpcion() {
		return textoOpcion;
	}

	public void setTextoOpcion(String textoOpcion) {
		this.textoOpcion = textoOpcion;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	//Metodos abstractos.
	public abstract boolean esCorrecta();
	public abstract int calcularPuntaje();
}
