package logica;

import java.util.ArrayList;

public abstract class Actividad {

	protected int idActividad;
	protected String titulo;
	protected String descripcion;
	protected String objetivos;
	protected String dificultad;
	protected String resultado;
	protected ArrayList<Resenia> resenias;
	protected ArrayList<Actividad> actividadesPrevias;
	static public ArrayList<Actividad> actividadesExistentes = new ArrayList<Actividad>();
	protected ArrayList<LearningPath> learningPaths;
	
	//, ArrayList<Resenia> resenias, ArrayList<Actividad> actividadesPrevias, ArrayList<LearningPath> learningPaths
	
	public Actividad(int idActividad, String titulo, String descripcion, String objetivos, String dificultad,
			 String resultado) {
		this.idActividad = idActividad;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.resultado = resultado;
		this.resenias = new ArrayList<Resenia>();
		this.actividadesPrevias = new ArrayList<Actividad>();
		this.learningPaths = new ArrayList<LearningPath>();
	}
	public int getIdActividad() {
		return idActividad;
	}
	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public ArrayList<Resenia> getResenias() {
		return resenias;
	}
	public void setResenias(ArrayList<Resenia> resenias) {
		this.resenias = resenias;
	}
	public ArrayList<Actividad> getActividadesPrevias() {
		return actividadesPrevias;
	}
	public void setActividadesPrevias(ArrayList<Actividad> actividadesPrevias) {
		this.actividadesPrevias = actividadesPrevias;
	}
	public void setActividadesExistentes(ArrayList<Actividad> actividadesExistentes) {
		Actividad.actividadesExistentes = actividadesExistentes;
	}
	public ArrayList<LearningPath> getLearningPaths() {
		return learningPaths;
	}
	public void setLearningPaths(ArrayList<LearningPath> learningPaths) {
		this.learningPaths = learningPaths;
	}
	
	public static void resetActividadesExistentes() {
		actividadesExistentes.clear();
	}
	
	public static ArrayList<Actividad> getActividadesExistentes(){
		return actividadesExistentes;
	}
	
	
	//Metodos para manejar las actividades previas.
	public Actividad buscarActividadPreviaSugerida(int idActividad) throws Exception {
		
		for (Actividad actividadBuscada : Actividad.actividadesExistentes) {
			if (actividadBuscada.getIdActividad() == idActividad) {
				return actividadBuscada;
			}
		}
		throw new Exception("No existe la actividad previa");
	}
	
	public void agregarActividadPreviaSugerida(int idActividad) throws Exception {
		
		Actividad previa = this.buscarActividadPreviaSugerida(idActividad);
	
		if (actividadesPrevias.contains(previa)) {
			throw new Exception("La actividad previa con ese id ya existía en esta actividad");
		}
		actividadesPrevias.add(previa);
	}
	
	public void eliminarActividadPreviaSugerida(int idActividad) throws Exception {
		Actividad actividadExistente = this.buscarActividadPreviaSugerida(idActividad);
		if (actividadesPrevias.contains(actividadExistente)) {
			actividadesPrevias.remove(actividadExistente);
		}
	}
	
	//Metodos de verificacion.
	public boolean esCompletada() {
		if (this instanceof Tarea) {
			Tarea tarea = (Tarea) this;
			if(tarea.getEstado().equals("Exitosa")) {
				return true;
			} else {
				return false;
			}
		} else if (this instanceof Quiz){
			Quiz quiz = (Quiz) this;
			if (quiz.getResultado().equals("Aprobado")) {
				return true;
			} else {
				return false;
			}	
		} else if (this instanceof Examen){
			Examen examen = (Examen) this;
			if (examen.getEstado().equals("Exitoso")) {
				return true;
			} else {
				return false;
			}
		} else if (this instanceof Encuesta) {
			Encuesta encuesta = (Encuesta) this;
			if (encuesta.getResultado().equals("Completada")) {
				return true;
			} else {
				return false;
			}
		} else if (this instanceof RevisarRecurso){
			RevisarRecurso recurso = (RevisarRecurso) this;
			if (recurso.obtenerResultado().equals("Revisado")) {
				return true;
			} else {
				return false;
			}
		} else {
			if(this.resultado != null && !this.resultado.equals("Pendiente")) {
				return true;
			} else {
				return false; 
			}
		}
	}
	
	public boolean sePuedeHacer() {
		
		boolean hayIncompletas = false;
		
		for(Actividad previa : actividadesPrevias) {
			if (!previa.esCompletada()) {
				hayIncompletas = true;
				System.out.println("Advertencia: La actividad previa: " + previa.getTitulo() + " no esta completada.");
			}
		}
		return true;
	}
	
	//Metodo para manejar los resultados.
	public void asignarResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String obtenerResultado() {
		return this.resultado;
	}
	
	//Metodo abstracto a ser implementado por las clases que heredan.
	public abstract void ejecutar();
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------
	
	// Métodos para manejar resenias.
	public void agregarResenia(Resenia resenia) throws Exception {
		if (resenia != null && !this.resenias.contains(resenia)) {
			this.resenias.add(resenia);
		} else {
			throw new Exception("La resenia ya existe.");
		}
	}

	public void eliminarResenia(Resenia resenia) throws Exception {
		if (this.resenias.contains(resenia)) {
			this.resenias.remove(resenia);
		} else {
			throw new Exception("La resenia no existe");
		}
	}
	
	public ArrayList<Resenia> listaDeResenias(){
		return this.resenias;	
	}
}	
