package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Resenia;
import logica.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ReseniaTest {
	
	@BeforeEach
	public void TestConstructor() {
		Usuario autor = new UsuarioTest(1, "Jose", "jose@gmail.com", "jose1234");
		Date fecha = new Date();
		Resenia resenia = new Resenia(4, "Buen tema", autor, fecha);
		
	}

}
