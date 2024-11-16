package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.LearningPath;
import logica.Progreso;
import logica.Usuario;
import logica.UsuarioConcreto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class UsuarioTest {
	
	private Usuario usuario;
	private LearningPath learningPath;
	
	@BeforeEach
	public void setUp() {
		usuario = new UsuarioConcreto(1, "Jose", "Jose@gmail.com", "jose1234");
		learningPath = new LearningPath(101, "Python Basics", "Learn Python from zero", "Basics of Python", "Beginner");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(1, usuario.getId());
		assertEquals("Jose", usuario.getNombre());
		assertEquals("Jose@gmail.com", usuario.getEmail());
		assertEquals("jose1234", usuario.getContrasenia());
		assertTrue(usuario.getProgresos().isEmpty());
		assertTrue(usuario.getLearningPaths().isEmpty());
	}
	
	@Test
	public void testSetters() {
		usuario.setId(2);
		usuario.setNombre("Carlos");
		usuario.setEmail("carlos@gmail.com");
		usuario.setContrasenia("carlos1234");
		
		assertEquals(2, usuario.getId());
		assertEquals("Carlos", usuario.getNombre());
		assertEquals("carlos@gmail.com", usuario.getEmail());
		assertEquals("carlos1234", usuario.getContrasenia());
	}
	
	@Test
	public void testAgregarProgreso() {
		Progreso progreso = new Progreso(new Date(), usuario, learningPath);
		usuario.agregarProgreso(progreso);
		
		assertEquals(1, usuario.getProgresos().size());
		assertEquals(progreso, usuario.getProgresos().get(0));
		assertEquals(learningPath, usuario.getProgresos().get(0).getLearningPath());
	}
	
	@Test
	public void testAgregarLearningPath() {
		usuario.agregarLearningPath(learningPath);
		
		assertEquals(1, usuario.getLearningPaths().size());
		assertEquals(learningPath, usuario.getLearningPaths().get(0));
	}
	
	

}
