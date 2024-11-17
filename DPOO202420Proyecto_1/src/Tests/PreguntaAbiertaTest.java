package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.PreguntaAbierta;

import static org.junit.jupiter.api.Assertions.*;

public class PreguntaAbiertaTest {
	
	private PreguntaAbierta pregunta;
	
	@BeforeEach
	public void setUp() {
		pregunta = new PreguntaAbierta(1, "Pregunta 1", 5, "Respuesta 1", "");
	}
	
	@Test
	public void testEsCorrecta() {
		pregunta.setRespuestaEstudiante("Respuesta 1");
		assertTrue(pregunta.esCorrecta());
		
		pregunta.setRespuestaEstudiante("No lo se");
		assertFalse(pregunta.esCorrecta());
		
		pregunta.setRespuestaEstudiante("respuesta 1");
		assertTrue(pregunta.esCorrecta());
	}
	
	@Test
	public void testCalcularPuntaje() {
		pregunta.setRespuestaEstudiante("Respuesta 1");
		assertEquals(5, pregunta.calcularPuntaje());
		
		pregunta.setRespuestaEstudiante("No lo se");
		assertEquals(0, pregunta.calcularPuntaje());
	}
	
	@Test
	public void testGettersYsetters() {
		pregunta.setRespuestaEstudiante("jaja");
		assertEquals("jaja", pregunta.getRespuestaEstudiante());
		
		pregunta.setRespuestaCorrecta("uwu");
		assertEquals("uwu", pregunta.getRespuestaCorrecta());
		
		pregunta.setTextoOpcion("holaa");
		assertEquals("holaa", pregunta.getTextoOpcion());
		
		pregunta.setPuntaje(2);
		assertEquals(2, pregunta.getPuntaje());
	}
	
	@Test
	public void testConstrutor() {
		assertEquals(1, pregunta.getIdOpcion());
		assertEquals("Pregunta 1", pregunta.getTextoOpcion());
		assertEquals(5, pregunta.getPuntaje());
		assertEquals("Respuesta 1", pregunta.getRespuestaCorrecta());
		assertEquals("", pregunta.getRespuestaEstudiante());

	}
}
