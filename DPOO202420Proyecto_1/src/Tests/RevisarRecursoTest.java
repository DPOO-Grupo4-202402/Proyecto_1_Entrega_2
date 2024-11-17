package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.RevisarRecurso;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class RevisarRecursoTest {
	
	private RevisarRecurso recurso;
	
	@BeforeEach
	public void setUp() {
		recurso = new RevisarRecurso(101, "Lectura de Python", "Revisar conceptos de Python", "Dominar lo fundamental", "Facil", "Facil", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "Documento");
	}
	
	@Test
	public void testMarcarRevisado() {
		recurso.marcarRevisado();
		assertEquals("Revisado", recurso.obtenerResultado());
	}
	
	@Test
	public void testSetTipo() {
		recurso.setTipo("Video");
		assertEquals("Video", recurso.getTipo());
	}
	
	@Test
	public void testEjecutar() {
		ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		recurso.ejecutar();
		
		System.setOut(System.out);
		
		String salida = salidaConsola.toString().trim();
		
		assertTrue(salida.contains("Recurso Revisado: Lectura de Python"));
		assertTrue(salida.contains("Estado: Revisado"));
	}

}
