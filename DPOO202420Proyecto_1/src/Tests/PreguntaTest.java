package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.PreguntaConcreta;

import static org.junit.jupiter.api.Assertions.*;

public class PreguntaTest {
	
	private PreguntaConcreta pregunta;
	
	@BeforeEach
	public void setUp() {
		pregunta = new PreguntaConcreta(1, "Pregunta 1", 5, true);
	}
	
	@Test
	public void testEsCorrecta() {
		assertTrue(pregunta.esCorrecta());
		pregunta.setCorrecta(false);
		assertFalse(pregunta.esCorrecta());
	}
	
	@Test
	public void testCalcularPuntaje() {
		assertEquals(5, pregunta.calcularPuntaje());
		pregunta.setCorrecta(false);
		assertEquals(0, pregunta.calcularPuntaje());
	}
	
	@Test
	public void testConstructor() {
		assertEquals(1, pregunta.getIdOpcion());
		assertEquals("Pregunta 1", pregunta.getTextoOpcion());
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
