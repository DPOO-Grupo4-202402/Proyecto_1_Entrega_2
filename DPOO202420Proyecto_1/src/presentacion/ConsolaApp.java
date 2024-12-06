package presentacion;

import logica.Profesor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logica.Estudiante;
import logica.LearningPath;
import logica.Progreso;
import logica.Actividad;
import logica.Progreso;
//import persistencia.ArchivoSerializable;
//import persistencia.PersistenciaDatos;
import persistencia.SerializacionDeArchivo;;

public class ConsolaApp {
	
    private static List<Profesor> profesores = new ArrayList<>();
    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<LearningPath> learningPaths = new ArrayList<>();
    private static List<Actividad> actividades = new ArrayList<>();
    protected static List<Progreso> progresos = new ArrayList<>();
    private Estudiante estudiante;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrar nuevo estudiante");
            System.out.println("2. Registrar nuevo profesor");
            System.out.println("3. Guardar datos");
            System.out.println("4. Cargar datos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> registrarEstudiante(scanner);
                case 2 -> registrarProfesor(scanner);       
                	   case 3 -> guardarDatos();
                       case 4 -> cargarDatos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void registrarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del estudiante: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.println("Intereses del estudiante: ");
        String intereses = scanner.nextLine();

        Estudiante estudiante = new Estudiante(getEstudiantes().size() + 1, nombre, email, password, intereses);
        getEstudiantes().add(estudiante);
        System.out.println("Estudiante registrado exitosamente.");
        //  try {
        //	SerializacionDeArchivo.guardarObjetoSerializable(estudiante, "Estudiantes.csv");
            
        //    System.out.println("Datos guardados exitosamente.");
        // } catch (IOException e) {
        //    System.out.println("Error al guardar los datos: " + e.getMessage());
            // }
    }

    public static void registrarProfesor(Scanner scanner) {
        System.out.print("Nombre del profesor: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del profesor: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Profesor profesor = new Profesor(getProfesores().size() + 1, nombre, email, password);
        getProfesores().add(profesor);
        System.out.println("Profesor registrado exitosamente.");
        // try {
        //	SerializacionDeArchivo.guardarObjetoSerializable(profesor, "Profesores.csv");
            
        //    System.out.println("Datos guardados exitosamente.");
        // } catch (IOException e) {
        //     System.out.println("Error al guardar los datos: " + e.getMessage());
        // }

    }

    

    

    

    //   private static void guardarDatos() {
    	   //      try {
        	 //    	 SerializacionDeArchivo.guardarObjetoSerializable(estudiante, "Estudiantes.csv");
    //         PersistenciaDatos.guardarEstudiantes(estudiantes);
    //         PersistenciaDatos.guardarProfesores(profesores);
    //         PersistenciaDatos.guardarLearningPaths(learningPaths);
    //       System.out.println("Datos guardados exitosamente.");
    //    } catch (IOException e) {
    //       System.out.println("Error al guardar los datos: " + e.getMessage());
    //    }
    //  }
    
    public static void guardarDatos() {
        try {
        	System.out.println("\n"+"-----------------------------------------------");
            // Guardar lista de estudiantes
            SerializacionDeArchivo.guardarObjetoSerializable(getEstudiantes(), "Estudiantes.dat");
            for (Estudiante estudiante : getEstudiantes()) {
                System.out.println(estudiante);
            }

            // Guardar lista de profesores
            SerializacionDeArchivo.guardarObjetoSerializable(getProfesores(), "Profesores.dat");
            for (Profesor profesor : getProfesores()) {
                System.out.println(profesor);
            }

            // Guardar lista de learning paths
            SerializacionDeArchivo.guardarObjetoSerializable(getLearningPaths(), "LearningPaths.dat");
            for (LearningPath lp : getLearningPaths()) {
                System.out.println(lp);
            }
            
         // Guardar lista de actividades
            SerializacionDeArchivo.guardarObjetoSerializable(getActividades(), "actividades.dat");
            for (Actividad actividad : getActividades()) {
                System.out.println(actividad);
            }
            
         // Guardar lista de Progresos
            SerializacionDeArchivo.guardarObjetoSerializable(progresos, "progresos.dat");

            System.out.println("-----------------------------------------------");
            System.out.println("Datos guardados exitosamente en archivos binarios.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

      public static void cargarDatos() {
    	  try {
    	        // Cargar lista de estudiantes desde archivo serializado
    	        Object estudiantesObjeto = SerializacionDeArchivo.leerObjetoSerializable("Estudiantes.dat");
    	        if (estudiantesObjeto instanceof List<?>) {
    	            setEstudiantes((List<Estudiante>) estudiantesObjeto);
    	            for (Estudiante estudiante : getEstudiantes()) {
    	                System.out.println(estudiante);
    	            }
    	   
    	        }

    	        // Cargar lista de profesores desde archivo serializado
    	        Object profesoresObjeto = SerializacionDeArchivo.leerObjetoSerializable("Profesores.dat");
    	        if (profesoresObjeto instanceof List<?>) {
    	            setProfesores((List<Profesor>) profesoresObjeto);
    	            for (Profesor profesor : getProfesores()) {
    	                System.out.println(profesor);
    	            }
    	        }

    	        // Cargar lista de Learning Paths desde archivo serializado
    	        Object learningPathsObjeto = SerializacionDeArchivo.leerObjetoSerializable("LearningPaths.dat");
    	        if (learningPathsObjeto instanceof List<?>) {
    	            setLearningPaths((List<LearningPath>) learningPathsObjeto);
    	            for (LearningPath lp : getLearningPaths()) {
    	                System.out.println(lp);
    	            }
    	        }
    	        // Cargar lista de actividades
                Object actividadesCargadas = SerializacionDeArchivo.leerObjetoSerializable("actividades.dat");
                if (actividadesCargadas instanceof List) {
                    setActividades((List<Actividad>) actividadesCargadas);
                    //System.out.println("Actividades cargadas: " + actividades.size());
                }
    	        
             // Cargar lista de Progresos
                Object progresosCargados = SerializacionDeArchivo.leerObjetoSerializable("progresos.dat");
                if (progresosCargados instanceof List) {
                    progresos = (List<Progreso>) progresosCargados;
                    //System.out.println("Progresos cargados: " + progresos.size());
                }

    	        System.out.println("Datos cargados exitosamente desde archivos serializados.");
    	    } catch (Exception e) {
    	        System.out.println("Error al cargar los datos: " + e.getMessage());
    	    }
    	}
      
      public static LearningPath buscarLearningPathPorId(int id) {
          return getLearningPaths().stream()
              .filter(lp -> lp.getIdRuta() == id)
              .findFirst()
              .orElse(null); // Devuelve null si no encuentra el ID
      }

	public static List<Profesor> getProfesores() {
		return profesores;
	}

	public static void setProfesores(List<Profesor> profesores) {
		ConsolaApp.profesores = profesores;
	}

	public static List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public static void setEstudiantes(List<Estudiante> estudiantes) {
		ConsolaApp.estudiantes = estudiantes;
	}

	public static List<LearningPath> getLearningPaths() {
		return learningPaths;
	}

	public static void setLearningPaths(List<LearningPath> learningPaths) {
		ConsolaApp.learningPaths = learningPaths;
	}

	public static List<Actividad> getActividades() {
		return actividades;
	}

	public static void setActividades(List<Actividad> actividades) {
		ConsolaApp.actividades = actividades;
	}
      
      
}
