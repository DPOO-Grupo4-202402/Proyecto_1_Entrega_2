package logica;

import java.util.ArrayList;
import java.util.Date;

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

		// Métodos para manejar actividades.
		public void agregarActividad(Actividad actividad) throws Exception {
			if (actividad != null && !this.actividades.contains(actividad)) {
				this.actividades.add(actividad);
			} else {
				throw new Exception("Esta actividad ya está en el Learning Path. ");
			}
		}

		public void eliminarActividad(Actividad actividad) throws Exception {
			if (this.actividades.contains(actividad)) {
				this.actividades.remove(actividad);
			} else {
				throw new Exception("La actividad no existe en el Learnign Path.");
			}
		}
		
		public Actividad obtenerActividad(int idActividad) throws Exception {
			for (Actividad actividad : this.actividades) {
				if (actividad.getIdActividad() == idActividad) {
					return actividad;
				}
			}
			throw new Exception("No se encontró la actividad con el ID especificado.");
		}

		// Métodos para agregar y eliminar usuarios
		public void agregarUsuario(Usuario usuario) throws Exception {
			if (!this.usuarios.contains(usuario)) {
				this.usuarios.add(usuario);
				this.progresos.add(new Progreso(null, null, "En progreso"));
			}
			throw new Exception("Usuario ya inscrito previamente en este Learning Path");
		}

		public void eliminarUsuario(Usuario usuario) throws Exception {
			int index = this.usuarios.indexOf(usuario);
			if (index != -1) {
				this.usuarios.remove(index);
				this.progresos.remove(index);
			}
			throw new Exception("Usuario inexistente en este Learning Path");
		}
		
		public Progreso obtenerProgresoDeUsuario(Usuario usuario) throws Exception {
			int index = this.usuarios.indexOf(usuario);
			if (index != -1 ) {
				return this.progresos.get(index);
			}
			throw new Exception("Usuario inexistente en este Learning Path");
		}
		
		public void actualizarProgresoDeUsuario(Usuario usuario, String nuevoResultado, Date fechaCompletado) {
			int index = this.usuarios.indexOf(usuario);
			if (index != -1) {
				Progreso progreso = this.progresos.get(index);
				progreso.setResultado(nuevoResultado);
				progreso.setFechaCompletado(fechaCompletado);
			}
		}
}
