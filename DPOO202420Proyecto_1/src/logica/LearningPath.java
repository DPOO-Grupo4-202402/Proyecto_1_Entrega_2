package logica;

import java.util.ArrayList;

public class LearningPath {
	
	private int idRuta;
	private String titulo;
	private String descripcion;
	private String objetivos;
	private String dificultad;
	private ArrayList<Resenia> resenias;
	private ArrayList<Actividad> actividades;
	private ArrayList<Progreso> progresos;
	private ArrayList<Usuario> usuarios;
	
	
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
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
	public ArrayList<Resenia> getResenias() {
		return resenias;
	}
	public void setResenias(ArrayList<Resenia> resenias) {
		this.resenias = resenias;
	}
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}
	public ArrayList<Progreso> getProgresos() {
		return progresos;
	}
	public void setProgresos(ArrayList<Progreso> progresos) {
		this.progresos = progresos;
	}
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	// Constructor completo
		public LearningPath(int idRuta, String titulo, String descripcion, String objetivos, String dificultad) {
			this.idRuta = idRuta;
			this.titulo = titulo;
			this.descripcion = descripcion;
			this.objetivos = objetivos;
			this.dificultad = dificultad;
			this.resenias = new ArrayList<Resenia>();  
			this.actividades = new ArrayList<Actividad>();
			this.progresos = new ArrayList<Progreso>();
			this.usuarios = new ArrayList<Usuario>();
		}
		
		// Constructor por defecto
		public LearningPath() {
			this.resenias = new ArrayList<Resenia>();
			this.actividades = new ArrayList<Actividad>();
			this.progresos = new ArrayList<Progreso>();
			this.usuarios = new ArrayList<Usuario>();
		}


		// Métodos para agregar y eliminar reseñas
		public void agregarResenia(Resenia resenia) {
			this.resenias.add(resenia);
		}

		public void eliminarResenia(Resenia resenia) {
			this.resenias.remove(resenia);
		}

		// Métodos para agregar y eliminar actividades
		public void agregarActividad(Actividad actividad) {
			this.actividades.add(actividad);
		}

		public void eliminarActividad(Actividad actividad) {
			this.actividades.remove(actividad);
		}

		// Métodos para agregar y eliminar usuarios
		public void agregarUsuario(Usuario usuario) {
			this.usuarios.add(usuario);
		}

		public void eliminarUsuario(Usuario usuario) {
			this.usuarios.remove(usuario);
		}

	
}
