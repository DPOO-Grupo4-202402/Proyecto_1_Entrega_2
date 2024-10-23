package logica;

import java.util.HashMap;

public class PreguntaCerrada extends Pregunta{
	
	private HashMap<Integer, String> opciones;
	private int respuestaSeleccionada;
	private int respuestaCorrecta;

	public PreguntaCerrada(int idOpcion, String textoOpcion, int puntaje, int respuestaSeleccionada, int respuestaCorrecta) {
		super(idOpcion, textoOpcion, puntaje);
		this.setOpciones(new HashMap<>());
		this.setRespuestaSeleccionada(-1);
		this.setRespuestaCorrecta(respuestaCorrecta);
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

	public int getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(int respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	@Override
	public boolean esCorrecta() {
		return this.respuestaSeleccionada == this.respuestaCorrecta;
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
