package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Actividad;
import logica.Estudiante;
import logica.LearningPath;
import logica.Recomendador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class RecomendadorTest {
	
	private Recomendador recomendador;
	private ArrayList<Actividad> actividades;
	private ArrayList<LearningPath> learningPaths;
	private ArrayList<Estudiante> estudiantes;
	private Estudiante estudiante1;
	private Estudiante estudiante2;
	private LearningPath lp1;
	private LearningPath lp2;
	private LearningPath lp3;
	
	@BeforeEach
	public void setUp() {
		actividades = new ArrayList<>();
		
		estudiante1 = new Estudiante(1001, "Santiago", "santiago@gmail.com", "santi1234", "Ciencias, programacion");
		estudiante2 = new Estudiante(1002, "Miguel", "miguel@gmail.com", "miguel1234", "equilibrio, soluciones binarias");
		
		lp1 =  new LearningPath(1, "Python Basics", "Desarrolla habilidades fundamentales de programacion en Python", "Aprende lo basico de python", "Principiante");
		lp2 =  new LearningPath(2, "Termodinamica Intermedia", "Aprende conceptos sobre sistemas de equilibrio en soluciones binarias", "Comprender el equilibrio en soluciones binarias", "Intermedio");
		lp3 =  new LearningPath(3, "Python para Data Science", "Analisis enfocado a ciencia de datos con Python enfocado en Ciencias", "Aprender Data Science con Python", "Avanzado");
		
		learningPaths = new ArrayList<>();
		learningPaths.add(lp1);
		learningPaths.add(lp2);
		learningPaths.add(lp3);
		
		estudiantes = new ArrayList<>();
		estudiantes.add(estudiante1);
		estudiantes.add(estudiante2);
		
		recomendador = new Recomendador(actividades, learningPaths, estudiantes);
	}
	
	@Test
	public void testContieneIntereses() {
		assertTrue(recomendador.contieneIntereses("programacion, Python", "Desarrolla habilidades fundamentales de programacion"));
		assertFalse(recomendador.contieneIntereses("equilibrio, soluciones binarias", "Desarrolla habilidades fundamentales de programacion en Python"));
		assertTrue(recomendador.contieneIntereses("Ciencias", "Analisis enfocado a ciencia de datos con Python enfocado en Ciencias"));
	}
	
	@Test
	public void testRecomendarLearningPaths() throws Exception {
		ArrayList<LearningPath> recomendacionEstudiante1 = recomendador.recomendarLearningPaths(estudiante1, learningPaths);
		
		assertEquals(2, recomendacionEstudiante1.size());
		assertTrue(recomendacionEstudiante1.contains(lp1));
		assertTrue(recomendacionEstudiante1.contains(lp3));
		
		ArrayList<LearningPath> recomendacionEstudiante2 = recomendador.recomendarLearningPaths(estudiante2, learningPaths);
		
		assertEquals(1, recomendacionEstudiante2.size());
		assertTrue(recomendacionEstudiante2.contains(lp2));
		
		Estudiante sinInteres = new Estudiante(1003, "Julian", "julian@gmail.com", "julian1234", "");
		ArrayList<LearningPath> recomendacionEstudianteSinInteres = recomendador.recomendarLearningPaths(sinInteres, learningPaths);
		
		assertEquals(0, recomendacionEstudianteSinInteres.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
