package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Encuesta;
import logica.PreguntaAbierta;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class EncuestaTest {
	
	private Encuesta encuesta;
	private PreguntaAbierta pregunta1;
	private PreguntaAbierta pregunta2;
	
	@BeforeEach
	public void setUp() {
		encuesta = new Encuesta(101, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", "facil", "Pendiente", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 15);
		
		pregunta1 = new PreguntaAbierta(1, "Que te gusto mas del curso?", 10, "Las clases", "");
		pregunta2 = new PreguntaAbierta(2, "Que mejorarias del curso?", 5, "Nada", "");
	}
	
	@Test
	public void testAgregarPregunta() {
		encuesta.agregarPregunta(pregunta1);
		encuesta.agregarPregunta(pregunta2);
		
		assertEquals(2, encuesta.getPreguntas().size());
		assertTrue(encuesta.getPreguntas().contains(pregunta1));
		assertTrue(encuesta.getPreguntas().contains(pregunta2));
	}
	
	@Test
	public void testEliminarPregunta() {
		encuesta.agregarPregunta(pregunta1);
		encuesta.agregarPregunta(pregunta2);
		encuesta.eliminarPregunta(pregunta1);
		
		assertEquals(1, encuesta.getPreguntas().size());
		assertFalse(encuesta.getPreguntas().contains(pregunta1));
		assertTrue(encuesta.getPreguntas().contains(pregunta2));
	}
	
	@Test
	public void testMarcarCompletada() {
		encuesta.marcarCompletada();
		
		assertEquals("Completada", encuesta.getResultado());
	}
	
	@Test
	public void testEjecutar() {
		ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		encuesta.ejecutar();
		
		System.setOut(System.out);
		
		String salida = salidaConsola.toString().trim();
		
		assertTrue(salida.contains("Encuesta completada: Encuesta de satisfaccion"));
		assertTrue(salida.contains("Estado: Completada"));
	}

}
