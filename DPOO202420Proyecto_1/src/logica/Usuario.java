package logica;

import java.util.ArrayList;

public abstract class Usuario {
	
	protected int id;
	protected String nombre;
	protected String email;
	protected String contrasenia;
	
	protected ArrayList<Progreso> progresos;
	protected ArrayList<LearningPath> learningPaths;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	public Usuario(int id, String nombre, String email, String contrasenia) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
		this.progresos = new ArrayList<Progreso>();
		this.learningPaths = new ArrayList<LearningPath>();
	}
	
	
	
	        
	
	
}
