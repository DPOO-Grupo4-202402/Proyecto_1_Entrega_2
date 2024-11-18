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
//import persistencia.ArchivoSerializable;
//import persistencia.PersistenciaDatos;
import persistencia.SerializacionDeArchivo;;

public class ConsolaApp {
	
    protected static List<Profesor> profesores = new ArrayList<>();
    protected static List<Estudiante> estudiantes = new ArrayList<>();
    protected static List<LearningPath> learningPaths = new ArrayList<>();
    protected static List<Actividad> actividades = new ArrayList<>();
    private Estudiante estudiante;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrar nuevo estudiante");
            System.out.println("2. Registrar nuevo profesor");
            System.out.println("3. Crear un Learning Path");
            System.out.println("4. Inscribir estudiante en un Learning Path");
            System.out.println("5. Ver progreso de un estudiante");
            System.out.println("6. Guardar datos");
            System.out.println("7. Cargar datos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> registrarEstudiante(scanner);
                case 2 -> registrarProfesor(scanner);
                case 3 -> crearLearningPath(scanner);
                case 4 -> inscribirEstudianteEnLearningPath(scanner);
                case 5 -> verProgresoEstudiante(scanner);
                	   case 6 -> guardarDatos();
                       case 7 -> cargarDatos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del estudiante: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.println("Intereses del estudiante: ");
        String intereses = scanner.nextLine();

        Estudiante estudiante = new Estudiante(estudiantes.size() + 1, nombre, email, password, intereses);
        estudiantes.add(estudiante);
        System.out.println("Estudiante registrado exitosamente.");
        //  try {
        //	SerializacionDeArchivo.guardarObjetoSerializable(estudiante, "Estudiantes.csv");
            
        //    System.out.println("Datos guardados exitosamente.");
        // } catch (IOException e) {
        //    System.out.println("Error al guardar los datos: " + e.getMessage());
            // }
    }

    private static void registrarProfesor(Scanner scanner) {
        System.out.print("Nombre del profesor: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del profesor: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Profesor profesor = new Profesor(profesores.size() + 1, nombre, email, password);
        profesores.add(profesor);
        System.out.println("Profesor registrado exitosamente.");
        // try {
        //	SerializacionDeArchivo.guardarObjetoSerializable(profesor, "Profesores.csv");
            
        //    System.out.println("Datos guardados exitosamente.");
        // } catch (IOException e) {
        //     System.out.println("Error al guardar los datos: " + e.getMessage());
        // }

    }

    private static void crearLearningPath(Scanner scanner) {
        System.out.print("Nombre del Learning Path: ");
        String nombre = scanner.nextLine();
        System.out.println("Decripcion del Learning Path: ");
        String descripcion = scanner.nextLine();
        System.out.println("Objetivos del Learning Path: ");
        String objetivos = scanner.nextLine();
        System.out.println("Dificultad del Learning Path: ");
        String dificultad = scanner.nextLine();
        LearningPath lp = new LearningPath(learningPaths.size() + 1, nombre, descripcion, objetivos, dificultad);
        learningPaths.add(lp);
        System.out.println("Learning Path creado exitosamente.");
        //try {
        //	SerializacionDeArchivo.guardarObjetoSerializable(lp, "LearningPath.csv");
            
        //    System.out.println("Datos guardados exitosamente.");
       // } catch (IOException e) {
       //     System.out.println("Error al guardar los datos: " + e.getMessage());
       // }
        
    }

    private static void inscribirEstudianteEnLearningPath(Scanner scanner) {
    	
        System.out.print("ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); 

        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getId() == idEstudiante)
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        estudiante.verLearningPathsInscritos();

        System.out.print("Nombre del Learning Path: ");
        String nombreLp = scanner.nextLine();
        LearningPath lp = learningPaths.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(nombreLp))
                .findFirst()
                .orElse(null);

        if (lp == null) {
            System.out.println("Learning Path no encontrado.");
            return;
        }

        try {
			estudiante.inscribirseEnLearningPath(lp);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
        System.out.println("Estudiante inscrito en el Learning Path exitosamente.");
    }

    private static void verProgresoEstudiante(Scanner scanner) {
        System.out.print("ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine();

        Estudiante estudiante = estudiantes.stream()
                .filter(e -> e.getId() == idEstudiante)
                .findFirst()
                .orElse(null);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        estudiante.mostrarProgreso();
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
    
    protected static void guardarDatos() {
        try {
        	System.out.println("\n"+"-----------------------------------------------");
            // Guardar lista de estudiantes
            SerializacionDeArchivo.guardarObjetoSerializable(estudiantes, "Estudiantes.dat");
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante);
            }

            // Guardar lista de profesores
            SerializacionDeArchivo.guardarObjetoSerializable(profesores, "Profesores.dat");
            for (Profesor profesor : profesores) {
                System.out.println(profesor);
            }

            // Guardar lista de learning paths
            SerializacionDeArchivo.guardarObjetoSerializable(learningPaths, "LearningPaths.dat");
            for (LearningPath lp : learningPaths) {
                System.out.println(lp);
            }
            
         // Guardar lista de actividades
            SerializacionDeArchivo.guardarObjetoSerializable(actividades, "actividades.dat");
            System.out.println("Actividades guardadas: " + actividades.size());

            System.out.println("-----------------------------------------------");
            System.out.println("Datos guardados exitosamente en archivos binarios.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

      protected static void cargarDatos() {
    	  try {
    	        // Cargar lista de estudiantes desde archivo serializado
    	        Object estudiantesObjeto = SerializacionDeArchivo.leerObjetoSerializable("Estudiantes.dat");
    	        if (estudiantesObjeto instanceof List<?>) {
    	            estudiantes = (List<Estudiante>) estudiantesObjeto;
    	            for (Estudiante estudiante : estudiantes) {
    	                System.out.println(estudiante);
    	            }
    	   
    	        }

    	        // Cargar lista de profesores desde archivo serializado
    	        Object profesoresObjeto = SerializacionDeArchivo.leerObjetoSerializable("Profesores.dat");
    	        if (profesoresObjeto instanceof List<?>) {
    	            profesores = (List<Profesor>) profesoresObjeto;
    	            for (Profesor profesor : profesores) {
    	                System.out.println(profesor);
    	            }
    	        }

    	        // Cargar lista de Learning Paths desde archivo serializado
    	        Object learningPathsObjeto = SerializacionDeArchivo.leerObjetoSerializable("LearningPaths.dat");
    	        if (learningPathsObjeto instanceof List<?>) {
    	            learningPaths = (List<LearningPath>) learningPathsObjeto;
    	            for (LearningPath lp : learningPaths) {
    	                System.out.println(lp);
    	            }
    	        }
    	        // Cargar lista de actividades
                Object actividadesCargadas = SerializacionDeArchivo.leerObjetoSerializable("actividades.dat");
                if (actividadesCargadas instanceof List) {
                    actividades = (List<Actividad>) actividadesCargadas;
                    System.out.println("Actividades cargadas: " + actividades.size());
                }
    	        

    	        System.out.println("Datos cargados exitosamente desde archivos serializados.");
    	    } catch (Exception e) {
    	        System.out.println("Error al cargar los datos: " + e.getMessage());
    	    }
    	}
      
      
}
