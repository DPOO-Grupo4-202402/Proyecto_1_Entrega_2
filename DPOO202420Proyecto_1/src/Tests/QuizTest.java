package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.PreguntaVerdaderoFalso;
import logica.Quiz;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class QuizTest {
	
	private Quiz quiz;
	private PreguntaVerdaderoFalso pregunta1;
	private PreguntaVerdaderoFalso pregunta2;
	
	@BeforeEach
	public void setUp() {
		quiz = new Quiz(102, "Quiz sobre conceptos de python", "Preguntas sobre asignaciones", "Evaluar conocimientos", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 80.0, 10.0);
		
		pregunta1 = new PreguntaVerdaderoFalso(1, "Python es un Lenguaje de Programacion?", 5, true);
		pregunta2 = new PreguntaVerdaderoFalso(1, "En Python cada linea se acaba con un ';'?", 5, false);
		
		quiz.agregarPregunta(pregunta1);
		quiz.agregarPregunta(pregunta2);
	}
	
	@Test
	public void testAgregarPregunta() {
		PreguntaVerdaderoFalso pregunta3 = new PreguntaVerdaderoFalso(3, "Python fue creado en 2024?", 10, false);
		quiz.agregarPregunta(pregunta3);
		assertTrue(quiz.getPreguntasCerradas().contains(pregunta3));
	}
	
	@Test
	public void testEliminarPregunta() {
		quiz.eliminarPregunta(pregunta1);
		assertFalse(quiz.getPreguntasCerradas().contains(pregunta1));
	}
	
	@Test
	public void testObtenerPregunta() {
		assertEquals(pregunta1, quiz.obtenerPregunta(0));
		assertEquals(pregunta2, quiz.obtenerPregunta(1));
	}
	
	@Test
	public void testCalificarQuizAprobado() {
		pregunta1.setSeleccion(true);
		pregunta2.setSeleccion(false);
		
		double porcentaje = quiz.calificarQuiz(new ArrayList<>());
		assertEquals(100.0, porcentaje, 0.01);
		assertEquals("Aprobado", quiz.getResultado());
	}
	
	@Test
	public void testCalificarQuizNoAprobado() {
		pregunta1.setSeleccion(false);
		pregunta2.setSeleccion(true);
		
		double porcentaje = quiz.calificarQuiz(new ArrayList<>());
		assertEquals(0.0, porcentaje, 0.01);
		assertEquals("No Aprobado", quiz.getResultado());
	}
	
	@Test
	public void testSetDuracion() {
		quiz.setDuracion(10);
		assertEquals(10, quiz.getDuracion());
	}
	
	@Test
	public void testSetCalificacionMinima() {
		quiz.setCalificacionMinima(60.0);
		assertEquals(60.0, quiz.getCalificacionMinima());
	}
	
	@Test
	public void testEjecutar() {
		ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		quiz.setResultado("Aprobado");
		quiz.ejecutar();
		
		System.setOut(System.out);
		
		String salida = salidaConsola.toString().trim();
		
		assertTrue(salida.contains("Quiz completado: Quiz sobre conceptos de python"));
		assertTrue(salida.contains("Estado: Aprobado"));
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
