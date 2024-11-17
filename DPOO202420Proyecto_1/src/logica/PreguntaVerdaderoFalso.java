package logica;

public class PreguntaVerdaderoFalso extends Pregunta{
	
	private boolean seleccion;
	private boolean correcta;

	public PreguntaVerdaderoFalso(int idOpcion, String textoOpcion, int puntaje, boolean correcta) {
		super(idOpcion, textoOpcion, puntaje);
		this.seleccion = false;
		this.correcta = correcta;
	}

	public boolean isSeleccion() {
		return seleccion;
	}


	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}


	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

	@Override
	public boolean esCorrecta() {
		return this.seleccion == this.correcta;
	}

	@Override
	public int calcularPuntaje() {
		if (esCorrecta()) {
			return this.puntaje;
		} else {
			return 0;
		}
	}

}
