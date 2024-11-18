package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.LearningPath;
import logica.Profesor;
import logica.Quiz;
import logica.Tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

public class ProfesorTest {
	
	private Profesor profesor;
	private LearningPath learningPath;
	private Tarea tarea;
	private Quiz quiz;
	
	@BeforeEach
	public void setUp() {
		profesor = new Profesor(2001, "Eduardo", "eduardo@gmail.com", "eduardo1234");
		learningPath = profesor.crearLearningPath(1, "Python Basics", "Aprende lo basico de python", "Desarrolla habilidades fundamentales de programacion", "Principiante");
		tarea = new Tarea(101, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", 40, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));
		quiz = new Quiz(102, "Quiz sobre conceptos de python", "Preguntas sobre asignaciones", "Evaluar conocimientos", "baja", 30, "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 80.0, 10.0);
	}
	
	@Test
	public void testCrearLearningPath() {
		assertNotNull(learningPath, "El learning Path no debe de ser nulo.");
		assertEquals(1, learningPath.getIdRuta(), "El Id no coincide.");
		assertEquals("Python Basics", learningPath.getTitulo(), "El titulo no coincide.");
	}
	
	@Test
	public void testAgregarActividadAlLearningPath() throws Exception {
		profesor.agregarActividadAlLearningPath(learningPath, quiz);
		assertTrue(learningPath.getActividades().contains(quiz));
		
		profesor.agregarActividadAlLearningPath(learningPath, tarea);
		assertTrue(learningPath.getActividades().contains(tarea));
	}
	
	@Test
	public void testAgregarActividadSinPermiso() {
		Profesor otro = new Profesor(2002, "Valentina", "valentina@gmail.com", "valen1234");
		Exception exception = assertThrows(Exception.class, () -> otro.agregarActividadAlLearningPath(learningPath, tarea));
		assertEquals("No tienes permisos para agregar actividades a este learning path.", exception.getMessage());
	}
	
	@Test
	public void testEliminarActividad() throws Exception {
		profesor.agregarActividadAlLearningPath(learningPath, quiz);
		profesor.agregarActividadAlLearningPath(learningPath, tarea);
		profesor.eliminarActividadAlLearningPath(learningPath, quiz);
		assertFalse(learningPath.getActividades().contains(quiz));
	}
	
	@Test
	public void testEliminarActividadSinPermiso() {
		Profesor otro = new Profesor(2002, "Valentina", "valentina@gmail.com", "valen1234");
		Exception exception = assertThrows(Exception.class, () -> otro.eliminarActividadAlLearningPath(learningPath, tarea));
		assertEquals("No tienes permisos para eliminar actividades a este learning path.", exception.getMessage());
	}
	
	@Test
	public void testVerLearningPaths() {
		profesor.crearLearningPath(2, "Programacion en Javascript", "Introduccion a la web", "Conocer lo basico", "Basico");
		assertDoesNotThrow(() -> profesor.verLearningPaths(), "No deberia lanzar excepciones");
	}

}
