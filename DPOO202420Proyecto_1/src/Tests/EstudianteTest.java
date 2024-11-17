package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Actividad;
import logica.Encuesta;
import logica.Estudiante;
import logica.Examen;
import logica.LearningPath;
import logica.Tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

public class EstudianteTest {
	
	private Estudiante estudiante;
	private LearningPath learningPath;
	private Encuesta encuesta;
	private Examen examen;
	private Tarea tarea;
	
	@BeforeEach
	public void setUp() throws Exception {
		Actividad.resetActividadesExistentes();
		estudiante = new Estudiante(1001, "Santiago", "santiago@gmail.com", "santi1234", "Ciencias, programacion");
		learningPath = new LearningPath(1, "Python Basics", "Desarrolla habilidades fundamentales de programacion en Python", "Aprende lo basico de python", "Principiante");
		encuesta = new Encuesta(101, "Encuesta de satisfaccion", "Cuentanos tu experiencia", "Reflexionar sobre lo aprendido", "facil", "facil", "Pendiente", null, null, null, 15);
		examen = new Examen(102, "Examen Final", "Evaluacion conocimientos Python", "Demostrar el estudio", "Dificil", "Dificil", "Pendiente", null, null, null, 120, 80.0, 70.0, "No enviado");
		tarea = new Tarea(103, "Tarea 1", "Realizar proyecto basico en python", "Practicar", "baja", "baja", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));

		Actividad.getActividadesExistentes().add(tarea);
		
		learningPath.agregarActividad(encuesta);
		learningPath.agregarActividad(examen);
		learningPath.agregarActividad(tarea);
	}
	
	@Test
	public void testInscribirseEnLearningPath() throws Exception {
		assertThrows(Exception.class, ( ) -> estudiante.consultarProgreso(learningPath), "Si no esta inscrito no deberia haber progreso");
		estudiante.inscribirseEnLearningPath(learningPath);
		assertNotNull(estudiante.consultarProgreso(learningPath), "Deberia existir un progreso despues de isncribirse");
	}
	
	@Test
	public void testCompletarEncuesta() throws Exception {
		estudiante.inscribirseEnLearningPath(learningPath);
		estudiante.completarActividad(learningPath, encuesta, "Completada");
		assertEquals("Completada", encuesta.getResultado());
		assertTrue(encuesta.esCompletada());
	}
	
	@Test
	public void testCompletarExamenAprobado() throws Exception {
		estudiante.inscribirseEnLearningPath(learningPath);
		estudiante.completarActividad(learningPath, examen, "85");
		
		assertEquals(85.0, examen.getCalificacionObtenida());
		assertEquals("Exitoso", examen.getEstado());
	}
	
	@Test
	public void testCompletarExamenNoAprobado() throws Exception {
		estudiante.inscribirseEnLearningPath(learningPath);
		estudiante.completarActividad(learningPath, examen, "60");
		
		assertEquals(60.0, examen.getCalificacionObtenida());
		assertEquals("No exitosa", examen.getEstado());
	}
	
	@Test
	public void testConsultarProgresoSinInscripcion() {
		Exception exception = assertThrows(Exception.class, () -> estudiante.consultarProgreso(learningPath));
		assertEquals("No estas inscrito en el learning path.", exception.getMessage());
	}
	
	@Test
	public void testCompletarActividadSinPrevia() throws Exception {
		estudiante.inscribirseEnLearningPath(learningPath);
		
		
		Tarea independiente = new Tarea(104, "Tarea previa", "previa de python", "Previar", "baja", "baja", "", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(System.currentTimeMillis() + 86400000));
		independiente.agregarActividadPreviaSugerida(103);
		learningPath.agregarActividad(independiente);
		
		Exception exception = assertThrows(Exception.class, () -> estudiante.completarActividad(learningPath, independiente, "Exitosa"));
		assertEquals("Hacer primero las actividades previas.", exception.getMessage());
		
		estudiante.completarActividad(learningPath, tarea, "Exitosa");
		estudiante.completarActividad(learningPath, independiente, "Exitosa");
		
		assertEquals("Exitosa", independiente.getEstado());
	}
	
	@Test
	public void testMostrarProgreso() throws Exception{
		estudiante.inscribirseEnLearningPath(learningPath);
		
		estudiante.completarActividad(learningPath, tarea, "Exitosa");
		estudiante.completarActividad(learningPath, encuesta, "Completada");
		
		java.io.ByteArrayOutputStream outContent= new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(outContent));
		
		estudiante.mostrarProgreso();
		
		String esperado = "Progreso de Santiago:\n" + 
						  "Learning Path: Python Basics\n" + 
						  "Progreso: 66.7%\n" +
						  " - Actividad: Encuesta de satisfaccion [Completada]\n" + 
						  " - Actividad: Examen Final [Pendiente]\n" + 
						  " - Actividad: Tarea 1 [Completada]\n";
		
		assertEquals(esperado, outContent.toString());
	}
	
	@Test
	public void testVerLearningPathsInscritos() throws Exception {
		estudiante.inscribirseEnLearningPath(learningPath);
		java.io.ByteArrayOutputStream outContent= new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(outContent));
		
		estudiante.verLearningPathsInscritos();
		
		String esperado = "Learning paths inscritos por: Santiago:\n" + 
						  "ID: 1, Titulo: Python Basics\n";
		
		assertEquals(esperado, outContent.toString());
	}	
}
