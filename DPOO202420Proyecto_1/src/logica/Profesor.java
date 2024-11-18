package logica;

import java.io.Serializable;

public class Profesor extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Profesor(int id, String nombre, String email, String contrasenia) {
		super(id, nombre, email, contrasenia);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public String toString() {
        return "Profesor{id=" + id + ", nombre='" + nombre + "', email='" + email + "', contrasenia='" + contrasenia + "'}";
    }
	
	public LearningPath crearLearningPath (int idRuta, String titulo, String descripcion, String objetivos, String dificultad) {
		LearningPath nuevoLearningPath = new LearningPath(idRuta, titulo, descripcion, objetivos, dificultad);
		this.learningPaths.add(nuevoLearningPath);
		return nuevoLearningPath;
	}
	
	public void agregarActividadAlLearningPath(LearningPath learningPath, Actividad actividad) throws Exception{
		if (this.learningPaths.contains(learningPath)) {
			learningPath.agregarActividad(actividad);
		} else {
			throw new Exception ("No tienes permisos para agregar actividades a este learning path.");
		}
	}
	
	public void eliminarActividadAlLearningPath(LearningPath learningPath, Actividad actividad) throws Exception{
		if (this.learningPaths.contains(learningPath)) {
			learningPath.eliminarActividad(actividad);
		} else {
			throw new Exception ("No tienes permisos para eliminar actividades a este learning path.");
		}
	}
	
	public void verLearningPaths() {
		System.out.println("Learning Paths creados por: " + this.getNombre() + ":");
		for (LearningPath lp : this.learningPaths) {
			System.out.println("Id: " + lp.getIdRuta() + ", Titulo: " +lp.getTitulo());
		}
	}

}
