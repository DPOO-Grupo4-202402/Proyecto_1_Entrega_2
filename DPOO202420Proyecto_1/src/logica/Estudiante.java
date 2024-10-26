package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Estudiante extends Usuario implements Serializable{
	
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
	
	public void inscribirseEnLearningPath(LearningPath learningPath) throws Exception{
		if (!this.learningPaths.contains(learningPath)) {
			learningPath.agregarUsuario(this);
			this.learningPaths.add(learningPath);
			System.out.println("Inscrito exitosamente en el learning path: " + learningPath.getTitulo());
		} else {
			throw new Exception("Ya estas inscrito en el learning path.");
		}
	}
	
	public Progreso consultarProgreso(LearningPath learningPath) throws Exception{
		if(this.learningPaths.contains(learningPath)) {
			return learningPath.obtenerProgresoDeUsuario(this);
		} else {
			throw new Exception("No estas inscrito en el learning path.");
		}
	}
	
	public void mostrarProgreso() {
	    System.out.println("Progreso de " + this.getNombre() + ":");
	    for (LearningPath lp : this.learningPaths) {
	        try {
	            Progreso progreso = consultarProgreso(lp);
	            System.out.println("Learning Path: " + lp.getTitulo());
	            System.out.println("Progreso: " + progreso.getPorcentajeCompletado() + "%");
	            
	            for (Actividad actividad : lp.getActividades()) {
	                String estado = actividad.esCompletada() ? "Completada" : "Pendiente";
	                System.out.println(" - Actividad: " + actividad.getTitulo() + " [" + estado + "]");
	            }
	        } catch (Exception e) {
	            System.out.println("Error al consultar el progreso en " + lp.getTitulo() + ": " + e.getMessage());
	        }
	    }
	}

	
	public void completarActividad(LearningPath learningPath, Actividad actividad, String resultado) throws Exception{
		if (this.learningPaths.contains(learningPath)) {
			Progreso progreso = learningPath.obtenerProgresoDeUsuario(this);
			Actividad actividadACompletar = learningPath.obtenerActividad(actividad.getIdActividad());
			
			if(actividadACompletar.sePuedeHacer()) {
				actividadACompletar.asignarResultado(resultado);
				progreso.actualizarProgreso();
				System.out.println("Actividad completada con exito: " + actividadACompletar.getTitulo());
			} else {
				throw new Exception("Hacer primero las actividades previas.");
			}
		} else {
			throw new Exception("No estas inscrito en el learning path.");
		}
	}
	
	public void verLearningPathsInscritos() {
		System.out.println("Learning paths inscritos por: " + this.getNombre() + ":");
		for (LearningPath lp : this.learningPaths) {
			System.out.println("ID: " + lp.getIdRuta() + ", Titulo: " + lp.getTitulo());
		}
	}
}
