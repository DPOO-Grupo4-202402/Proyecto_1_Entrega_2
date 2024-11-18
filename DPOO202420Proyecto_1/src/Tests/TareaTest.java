package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Tarea;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TareaTest {
	
	private Tarea tarea;
	private Date fechaEntrega;
	
	@BeforeEach
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		fechaEntrega = cal.getTime();
		
		tarea = new Tarea(101, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), fechaEntrega);
	}
	
	@Test
	public void testEntregadaATiempo() {
		Date entregaEstudiante = new Date();
		assertTrue(tarea.entregadaATiempo(entregaEstudiante));
	}
	
	@Test
	public void testNoEntregadaATiempo() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 2);
		Date entregaEstudiante = cal.getTime();
		assertFalse(tarea.entregadaATiempo(entregaEstudiante));
	}
	
	@Test
	public void testMarcarExitosa() {
		tarea.marcarExitosa();
		assertEquals("Exitosa", tarea.getEstado());
	}
	
	@Test
	public void testNoMarcarExitosa() {
		tarea.marcarNoExitosa();
		assertEquals("No exitosa", tarea.getEstado());
	}
	
	@Test
	public void testEjecutar() {
		ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
		System.setOut(new PrintStream(salidaConsola));
		
		tarea.ejecutar();
		
		System.setOut(System.out);
		
		String salida = salidaConsola.toString();
		
		assertTrue(salida.contains("Ejecutando la tarea: Tarea 1"));
		assertTrue(salida.contains("Estado: Enviada"));
	}
	
	@Test
	public void testFechaEntrega() {
		assertEquals(fechaEntrega, tarea.getFechaEntrega());
	}
	
	@Test
	public void testModificarFechaEntrega() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 5);
		Date nuevaEntrega = cal.getTime();
		
		tarea.setFechaEntrega(nuevaEntrega);
		
		assertEquals(nuevaEntrega, tarea.getFechaEntrega());
	}

}
