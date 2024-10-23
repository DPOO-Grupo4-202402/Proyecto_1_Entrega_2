package logica;

public class Estudiante extends Usuario{
	
	private String intereses;

	public Estudiante(int id, String nombre, String email, String contrasenia, String intereses) {
		super(id, nombre, email, contrasenia);
		this.intereses = intereses;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

}
