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
		public void agregarResenia(Resenia resenia) {
			if (resenia != null && !this.resenias.contains(resenia)) {
				this.resenias.add(resenia);
			} else {
				System.out.println("La resenia ya existe.");
			}
		}

		public void eliminarResenia(Resenia resenia) {
			if (this.resenias.contains(resenia)) {
				this.resenias.remove(resenia);
			} else {
				System.out.println("La resenia no existe");
			}
		}
		
		public ArrayList<Resenia> listaDeResenias(){
			return this.resenias;
		}

		// Métodos para manejar actividades.
		public void agregarActividad(Actividad actividad) {
			if (actividad != null && !this.actividades.contains(actividad)) {
				this.actividades.add(actividad);
			} else {
				System.out.println("Esta actividad ya esta en el Learning Path. ");
			}
		}

		public void eliminarActividad(Actividad actividad) {
			if (this.actividades.contains(actividad)) {
				this.actividades.remove(actividad);
			} else {
				System.out.println("La actividad no existe en el Learnign Path.");
			}
		}
		
		public Actividad obtenerActividad(int idActividad) {
			for (Actividad actividad : this.actividades) {
				if (actividad.getIdActividad() == idActividad) {
					return actividad;
				}
			}
			System.out.println("No se encontro la actividad con el ID especificado.");
			return null;
		}

		// Métodos para agregar y eliminar usuarios
		public void agregarUsuario(Usuario usuario) {
			if (!this.usuarios.contains(usuario)) {
				this.usuarios.add(usuario);
				this.progresos.add(new Progreso(null, null, "En progreso"));
			}
		}

		public void eliminarUsuario(Usuario usuario) {
			int index = this.usuarios.indexOf(usuario);
			if (index != -1) {
				this.usuarios.remove(index);
				this.progresos.remove(index);
			}
		}
		
		public Progreso obtenerProgresoDeUsuario(Usuario usuario) {
			int index = this.usuarios.indexOf(usuario);
			if (index != -1 ) {
				return this.progresos.get(index);
			}
		return null;
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
