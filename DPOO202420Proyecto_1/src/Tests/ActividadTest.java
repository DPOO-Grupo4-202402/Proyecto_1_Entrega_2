package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Actividad;
import logica.Encuesta;
import logica.Estudiante;
import logica.Quiz;
import logica.Resenia;
import logica.Tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

public class ActividadTest {
	
	private Tarea tarea;
	private Quiz quiz;
	private Estudiante autor;
	
	@BeforeEach
	public void setUp() {
		Actividad.resetActividadesExistentes();
		
		tarea = new Tarea(101, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));
		quiz = new Quiz(102, "Quiz sobre conceptos de python", "Preguntas sobre asignaciones", "Evaluar conocimientos", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 80.0, 10.0);
		autor = new Estudiante(1001, "Santiago", "santiago@gmail.com", "santi1234", "python");
		
		Actividad.getActividadesExistentes().add(tarea);
		Actividad.getActividadesExistentes().add(quiz);
	}
	
	@Test
	public void testEsCompletada() {
		assertFalse(tarea.esCompletada(), "La tarea no deberia marcarse como completada");
		assertFalse(quiz.esCompletada(), "El quiz no deberia marcarse como completada");
		
		tarea.setEstado("Exitosa");
		quiz.asignarResultado("Aprobado");
		
		assertTrue(tarea.esCompletada(), "La tarea deberia marcarse como completada");
		assertTrue(quiz.esCompletada(), "El quiz deberia marcarse como completada");
	}
	
	@Test
	public void testSePuedeHacerSinPrevias() {
		assertTrue(tarea.sePuedeHacer(), "La tarea no tiene pruebas, se deberia poder realizar.");
		assertTrue(quiz.sePuedeHacer(), "El quiz no tiene pruebas, se deberia poder realizar.");	
	}
	
	@Test
	public void testAgregarActividadPrevia() throws Exception {
		Encuesta encuesta = new Encuesta(103, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", 15, "Pendiente", null, null, null);
		Actividad.getActividadesExistentes().add(encuesta);
		quiz.agregarActividadPreviaSugerida(103);
		assertTrue(quiz.getActividadesPrevias().contains(encuesta), "La actividad previa deberia haber sido agregada.");
	}
	
	@Test
	public void testAgregarActividadPreviaDuplicada() throws Exception {
		Encuesta encuesta = new Encuesta(103, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", 15, "Pendiente", null, null, null);
		Actividad.getActividadesExistentes().add(encuesta);
		quiz.agregarActividadPreviaSugerida(103);
		Exception exception = assertThrows(Exception.class, () -> quiz.agregarActividadPreviaSugerida(103));
		assertEquals("La actividad previa con ese id ya existía en esta actividad", exception.getMessage());
	}
	
	@Test
	public void testEliminarActividadPreviaSugerida() throws Exception {
		quiz.agregarActividadPreviaSugerida(101);
		quiz.eliminarActividadPreviaSugerida(101);
		assertFalse(quiz.getActividadesPrevias().contains(tarea));
	}
	
	@Test
	public void testEliminarActividadPreviaInexistente() {
		Exception exception = assertThrows(Exception.class, () -> quiz.eliminarActividadPreviaSugerida(999));
		assertEquals("No existe la actividad previa", exception.getMessage());
	}
	
	@Test
	public void testSePuedeHacerPreviasIncompletas() throws Exception {
		Encuesta encuesta = new Encuesta(103, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", 15, "Pendiente", null, null, null);
		Actividad.getActividadesExistentes().add(encuesta);
		tarea.agregarActividadPreviaSugerida(103);
		assertTrue(tarea.sePuedeHacer());
	}
	
	@Test
	public void testSePuedeHacerPreviasCompletas() throws Exception {
		Encuesta encuesta = new Encuesta(103, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", 15, "Pendiente", null, null, null);
		Actividad.getActividadesExistentes().add(encuesta);
		encuesta.asignarResultado("Completada");
		assertTrue(encuesta.esCompletada());
		tarea.agregarActividadPreviaSugerida(103);
		assertTrue(tarea.sePuedeHacer());
	}
	
	@Test
	public void testAsignarYObtenerResultado() {
		tarea.asignarResultado("Completada");
		assertEquals("Completada", tarea.obtenerResultado());
	}
	
	@Test
	public void testAgregarResenia() throws Exception {
		Resenia resenia = new Resenia(4, "Increible", autor, new Date());
		tarea.agregarResenia(resenia);
		assertTrue(tarea.getResenias().contains(resenia));
	}
	
	@Test
	public void testAgregarReseniaDuplicada() throws Exception {
		Resenia resenia = new Resenia(4, "Increible", autor, new Date());
		tarea.agregarResenia(resenia);
		Exception exception = assertThrows(Exception.class, () -> tarea.agregarResenia(resenia));
		assertEquals("La resenia ya existe.", exception.getMessage());
	}
	
	@Test
	public void testEliminarResenia() throws Exception {
		Resenia resenia = new Resenia(4, "Increible", autor, new Date());
		tarea.agregarResenia(resenia);
		tarea.eliminarResenia(resenia);
		assertFalse(tarea.getResenias().contains(resenia));
	}
	
	@Test
	public void testEliminarReseniaInexistente() {
		Resenia resenia = new Resenia(4, "Increible", autor, new Date());
		Exception exception = assertThrows(Exception.class, () -> tarea.eliminarResenia(resenia));
		assertEquals("La resenia no existe", exception.getMessage());
	}
}
