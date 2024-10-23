package logica;

public class PreguntaAbierta extends Pregunta{
	
	private String respuestaCorrecta;
	private String respuestaEstudiante;

	public PreguntaAbierta(int idOpcion, String textoOpcion, int puntaje, String respuestaCorrecta, String respuestaEstudiante) {
		super(idOpcion, textoOpcion, puntaje);
		this.respuestaCorrecta = respuestaCorrecta;
		this.respuestaEstudiante = "";
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public String getRespuestaEstudiante() {
		return respuestaEstudiante;
	}

	public void setRespuestaEstudiante(String respuestaEstudiante) {
		this.respuestaEstudiante = respuestaEstudiante;
	}

	@Override
	public boolean esCorrecta() {
		return this.respuestaEstudiante.equalsIgnoreCase(respuestaCorrecta);
	}

	@Override
	public int calcularPuntaje() {
		if (respuestaEstudiante.equals(respuestaCorrecta)){
			return puntaje;
		} else {
			return 0;
		}
	}

}
