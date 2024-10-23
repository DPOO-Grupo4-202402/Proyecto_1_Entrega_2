package logica;

import java.util.HashMap;

public class PreguntaCerrada extends Pregunta{
	
	private HashMap<Integer, String> opciones;
	private int respuestaSeleccionada;

	public PreguntaCerrada(int idOpcion, String textoOpcion, int puntaje, int respuestaSeleccionada) {
		super(idOpcion, textoOpcion, puntaje);
		this.setOpciones(new HashMap<>());
		this.setRespuestaSeleccionada(respuestaSeleccionada);
	}

	public HashMap<Integer, String> getOpciones() {
		return opciones;
	}

	public void setOpciones(HashMap<Integer, String> opciones) {
		this.opciones = opciones;
	}

	public int getRespuestaSeleccionada() {
		return respuestaSeleccionada;
	}

	public void setRespuestaSeleccionada(int respuestaSeleccionada) {
		this.respuestaSeleccionada = respuestaSeleccionada;
	}

}
