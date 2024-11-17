package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Examen;
import logica.PreguntaAbierta;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ExamenTest {
	
	private Examen examen;
	private PreguntaAbierta pregunta1;
	private PreguntaAbierta pregunta2;
	private ArrayList<PreguntaAbierta> preguntas;
	
	@BeforeEach
	public void setUp() {
		pregunta1 = new PreguntaAbierta(1, "Pregunta 1", 5, "Respuesta 1", "");
		pregunta2 = new PreguntaAbierta(2, "Pregunta 2", 5, "Respuesta 2", "");
		
		preguntas = new ArrayList<>();
		preguntas.add(pregunta1);
		preguntas.add(pregunta2);
		
		examen = new Examen(102, "Examen Final", "Evaluacion conocimientos Python", "Demostrar el estudio", "Dificil", "Dificil", "Pendiente", null, null, null, 120, 80.0, 70.0, "No enviado");
		
		examen.setPreguntas(preguntas);
	}
	
	@Test
	public void testAgregarPregunta() {
		assertEquals(2, examen.getPreguntas().size());
		examen.getPreguntas().add(new PreguntaAbierta(3, "Pregunta 3", 5, "Respuesta 3", ""));
		assertEquals(3, examen.getPreguntas().size());
	}
	
	@Test
	public void testCalificacionExitosa() {
		examen.setCalificacionObtenida(90.0);
		
		boolean resultado = examen.aprobado(examen.getCalificacionObtenida());
		
		assertTrue(resultado);
		assertEquals("Exitoso", examen.getEstado());
	}
	
	@Test
	public void testCalificacionNoExitosa() {
		examen.setCalificacionObtenida(50.0);
		
		boolean resultado = examen.aprobado(examen.getCalificacionObtenida());
		
		assertFalse(resultado);
		assertEquals("No exitosa", examen.getEstado());
	}
	
	@Test
	public void testEjecutar() {
		ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		examen.ejecutar();
		
		System.setOut(System.out);
		
		String salida = salidaConsola.toString().trim();
		
		assertTrue(salida.contains("Examen completado: Examen Final"));
		assertTrue(salida.contains("Estado: Pendiente"));
	}

}
