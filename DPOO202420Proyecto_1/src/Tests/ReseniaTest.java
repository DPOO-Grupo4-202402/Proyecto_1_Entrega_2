package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Resenia;
import logica.Usuario;
import logica.UsuarioConcreto;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ReseniaTest {
	
	private Resenia resenia;
	private Usuario autor;
	
	@BeforeEach
	public void setUp() {
		autor = new UsuarioConcreto(1, "Jose", "Jose@gmail.com", "jose1234");
		resenia = new Resenia(4, "Me gusta el curso todo se entiende de maravilla", autor, new Date());
	}
	
	@Test
	public void testConstructor() {
		assertEquals(4, resenia.getRating());
		assertEquals("Me gusta el curso todo se entiende de maravilla", resenia.getComentario());
		assertEquals(autor, resenia.getAutor());
		assertNotNull(resenia.getFecha());
	}
	
	@Test
	public void testSetRatingValido() {
		resenia.setRating(2);
		assertEquals(2, resenia.getRating());
	}
	
	@Test
	public void testSetRatingInvalido() {
		assertThrows(IllegalArgumentException.class, () -> resenia.setRating(-1));
		assertThrows(IllegalArgumentException.class, () -> resenia.setRating(6));
	}
	
	@Test
	public void testObtenerResenia() {
		String texto = resenia.obtenerResenia();
		assertTrue(texto.contains("Autor: Jose"));
		assertTrue(texto.contains("Rating: 4"));
		assertTrue(texto.contains("Comentario: Me gusta el curso todo se entiende de maravilla"));
	}

}
