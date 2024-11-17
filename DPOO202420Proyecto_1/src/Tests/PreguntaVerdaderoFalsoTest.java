package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.PreguntaVerdaderoFalso;

import static org.junit.jupiter.api.Assertions.*;

public class PreguntaVerdaderoFalsoTest {
	
	private PreguntaVerdaderoFalso pregunta;
	
	@BeforeEach
	public void setUp() {
		pregunta = new PreguntaVerdaderoFalso(1, "Python es un lenguaje de programacion", 5, true);
	}
	
	@Test
	public void testEsCorrecta() {
		pregunta.setSeleccion(true);
		assertTrue(pregunta.esCorrecta());
		
		pregunta.setSeleccion(false);
		assertFalse(pregunta.esCorrecta());
	}
	
	@Test
	public void testCalcularPuntaje() {
		pregunta.setSeleccion(true);
		assertEquals(5, pregunta.calcularPuntaje());
		
		pregunta.setCorrecta(false);
		assertEquals(0, pregunta.calcularPuntaje());
	}
	
	@Test
	public void testConstructor() {
		assertEquals(1, pregunta.getIdOpcion());
		assertEquals("Python es un lenguaje de programacion", pregunta.getTextoOpcion());
		assertEquals(5, pregunta.getPuntaje());
	}
	
	@Test
	public void GettersYSetters() {
		pregunta.setIdOpcion(2);
		pregunta.setTextoOpcion("Hola mundo");
		pregunta.setPuntaje(10);
		
		assertEquals(2, pregunta.getIdOpcion());
		assertEquals("Hola mundo", pregunta.getTextoOpcion());
		assertEquals(10, pregunta.getPuntaje());
	}
	

}
