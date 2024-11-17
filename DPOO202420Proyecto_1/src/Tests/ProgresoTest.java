package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Estudiante;
import logica.LearningPath;
import logica.Progreso;
import logica.Quiz;
import logica.Tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

public class ProgresoTest {
	
	private Progreso progreso;
	private Estudiante estudiante;
	private LearningPath learningPath;
	private Tarea tarea;
	private Quiz quiz;
	
	@BeforeEach
	public void setUp() throws Exception {
		estudiante = new Estudiante(1001, "Santiago", "santiago@gmail.com", "santi1234", "python");
		learningPath = new LearningPath(1, "Python Basics", "Aprende lo basico de python", "Desarrolla habilidades fundamentales de programacion", "Principiante");
		tarea = new Tarea(101, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", "baja", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));
		quiz = new Quiz(102, "Quiz sobre conceptos de python", "Preguntas sobre asignaciones", "Evaluar conocimientos", "baja", "baja", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 30, 80.0, 10.0);
		
		learningPath.agregarActividad(quiz);
		learningPath.agregarActividad(tarea);
		
		estudiante.inscribirseEnLearningPath(learningPath);
		
		progreso = estudiante.consultarProgreso(learningPath);
	}
	
	@Test
	public void testActualizarProgresoSinActividades() {
		LearningPath vacio = new LearningPath(2, "Vacio", "No actividades", "Obj vacia", "principiante");
		progreso = new Progreso(new Date(), estudiante, vacio);
		progreso.actualizarProgreso();
		assertEquals(0.0, progreso.getPorcentajeCompletado());
		assertEquals("En progreso", progreso.getResultado());
		assertNull(progreso.getFechaCompletado());
	}
	
	@Test
	public void testActualizarProgresoSinActividadesCompletadas() throws Exception {
		estudiante.completarActividad(learningPath, quiz, "No aprobado");
		estudiante.completarActividad(learningPath, tarea, "No exitosa");
		progreso.actualizarProgreso();
		assertEquals(0.0, progreso.getPorcentajeCompletado());
		assertEquals("En progreso", progreso.getResultado());
	}
	
	@Test
	public void testActualizarProgresoConActividadesParciales() throws Exception {
		estudiante.completarActividad(learningPath, quiz, "Aprobado");
		progreso.actualizarProgreso();
		assertEquals(50.0, progreso.getPorcentajeCompletado(), "El porcentaje con una actividad completada debe ser 50%.");
		assertEquals("En progreso", progreso.getResultado(), "El resultado debe ser aun en progreso");
	}
	
	@Test
	public void testActualizarProgresoCompletado() throws Exception {
		estudiante.completarActividad(learningPath, quiz, "Aprobado");
		estudiante.completarActividad(learningPath, tarea, "Exitosa");
		
		assertTrue(quiz.esCompletada(), "El quiz ya deberia estar completado.");
		assertTrue(tarea.esCompletada(), "La tarea ya deberia estar completado.");
		
		progreso.actualizarProgreso();
		
		assertEquals(100.0, progreso.getPorcentajeCompletado(), "El porcentaje con dos actividades completadas debe ser 100%.");
		assertEquals("Completado", progreso.getResultado(), "El resultado debe ser Completado.");
		assertNotNull(progreso.getFechaCompletado(), "No deberia ser nula.");
	}
	
	@Test
	public void testInscripcionDoble() {
		Exception exception = assertThrows(Exception.class, () -> {estudiante.inscribirseEnLearningPath(learningPath);});
		assertEquals("Ya estas inscrito en el learning path.", exception.getMessage());
	}

}
