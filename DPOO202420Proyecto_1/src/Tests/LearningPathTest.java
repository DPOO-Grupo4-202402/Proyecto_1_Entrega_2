package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Actividad;
import logica.Estudiante;
import logica.LearningPath;
import logica.PreguntaVerdaderoFalso;
import logica.Profesor;
import logica.Progreso;
import logica.Quiz;
import logica.Tarea;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;


public class LearningPathTest {
	
	private LearningPath learningPath;
	private Tarea tarea;
	private Quiz quiz;
	private Estudiante estudiante;
	private Profesor profesor;
	
	@BeforeEach
	public void setUp()  {
		learningPath = new LearningPath(1, "Python Basics", "Aprende lo basico de python", "Desarrolla habilidades fundamentales de programacion", "Principiante");
		tarea = new Tarea(101, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", 40, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));
		quiz = new Quiz(102, "Quiz sobre conceptos de python", "Preguntas sobre asignaciones", "Evaluar conocimientos", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 80.0, 10.0);
		estudiante = new Estudiante(1001, "Santiago", "santiago@gmail.com", "santi1234", "python");
		profesor = new Profesor(2001, "Eduardo", "eduardo@gmail.com", "eduardo1234");
		
	
		quiz.agregarPregunta(new PreguntaVerdaderoFalso(1, "Python es un lenguaje de programacion", 5, true));
		quiz.agregarPregunta(new PreguntaVerdaderoFalso(2, "Siempre se acaba una linea de codigo con ;", 5, false));
	}
	
	@Test
	public void testAgregarActividad() throws Exception{
		learningPath.agregarActividad(quiz);
		assertEquals(1, learningPath.getActividades().size());
		assertTrue(learningPath.getActividades().contains(quiz));
		
		learningPath.agregarActividad(tarea);
		assertEquals(2, learningPath.getActividades().size());
		assertTrue(learningPath.getActividades().contains(tarea));
		
		Exception exception = assertThrows(Exception.class, () -> {learningPath.agregarActividad(tarea);});
		assertEquals("Esta actividad ya está en el Learning Path. ", exception.getMessage());
	}
	
	@Test
	public void testEliminarActividad() throws Exception{
		learningPath.agregarActividad(quiz);
		learningPath.agregarActividad(tarea);
		
		learningPath.eliminarActividad(quiz);
		assertEquals(1, learningPath.getActividades().size());
		assertFalse(learningPath.getActividades().contains(quiz));
		
		learningPath.eliminarActividad(tarea);
		assertEquals(0, learningPath.getActividades().size());
		
		Exception exception = assertThrows(Exception.class, () -> {learningPath.eliminarActividad(quiz);;});
		assertEquals("La actividad no existe en el Learnign Path.", exception.getMessage());
	}
	
	@Test
	public void testOtenerActividad() throws Exception {
		learningPath.agregarActividad(quiz);
		learningPath.agregarActividad(tarea);
		
		Actividad actividad = learningPath.obtenerActividad(102);
		assertEquals(quiz, actividad);
		
		actividad = learningPath.obtenerActividad(101);
		assertEquals(tarea, actividad);
		
		Exception exception = assertThrows(Exception.class, () -> {learningPath.obtenerActividad(888);});
		assertEquals("No se encontró la actividad con el ID especificado.", exception.getMessage());
	}
	
	@Test
	public void testAgregarUsuario() throws Exception {
		learningPath.agregarUsuario(estudiante);
		assertEquals(1, learningPath.getUsuarios().size());
		assertTrue(learningPath.getUsuarios().contains(estudiante));
		
		Exception exception = assertThrows(Exception.class, () -> {learningPath.agregarUsuario(estudiante);});
		assertEquals("Usuario ya inscrito previamente en este Learning Path", exception.getMessage());
	}
	
	@Test
	public void testObtenerProgresoDeUsuario() throws Exception {
		learningPath.agregarUsuario(estudiante);
		Progreso progresoObtenido = learningPath.obtenerProgresoDeUsuario(estudiante);
		assertNotNull(progresoObtenido);
		assertEquals(estudiante, progresoObtenido.getUsuario());
		
		Exception exception = assertThrows(Exception.class, () -> {learningPath.obtenerProgresoDeUsuario(profesor);});
		assertEquals("Usuario inexistente en este Learning Path", exception.getMessage());
		
	}
	
	
	
	
	
	
	
	
	

}
