package logica;

public class PreguntaConcreta extends Pregunta{
	
	private boolean correcta;

	public PreguntaConcreta(int idOpcion, String textoOpcion, int puntaje, boolean correcta) {
		super(idOpcion, textoOpcion, puntaje);
		this.correcta = correcta;
	}


	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

	@Override
	public boolean esCorrecta() {
		// TODO Auto-generated method stub
		return this.correcta;
	}

	@Override
	public int calcularPuntaje() {
		if (esCorrecta()){
			return this.puntaje;
		} else {
			return 0;
		}
	}
}
