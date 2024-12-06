package presentacion;

import java.util.Date;
import java.util.Scanner;
import logica.LearningPath;
import logica.Profesor;
import logica.Progreso;
import logica.Resenia;
import logica.Estudiante;
import logica.Actividad;

public class ConsolaProfesorCalificador extends ConsolaApp {

    public static void main(String[] args) {
        System.out.println("Carga de datos...\n");
        cargarDatos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("--------------------------------------------------------\n");
        do {
            System.out.println("0. Cerrar");
            System.out.println("1. Iniciar sesión\n");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 0 -> System.out.println("Saliendo...");
                case 1 -> iniciarSesion(scanner);
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }

        } while (opcion != 0);
        scanner.close();
    }

    private static void iniciarSesion(Scanner scanner) {
        Profesor profesorActual = autenticarProfesor(scanner);
        if (profesorActual != null) {
            System.out.println("Bienvenido(a), " + profesorActual.getNombre() + "!");
            mostrarMenuProfesorCalificador(scanner, profesorActual);
        } else {
            System.out.println("Autenticación fallida.\n");
        }
    }
    
    private static Profesor autenticarProfesor(Scanner scanner) {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = scanner.nextLine();
        
        for (Profesor profesor : getProfesores()) {  
        	 if (profesor.getNombre().equalsIgnoreCase(nombreUsuario)) {
                 if (profesor.getContrasenia().equals(contrasenia)) {
                     
                     return profesor;  
                 } else {
                     System.out.println("Contraseña incorrecta");
                     return null;  
                 }
             }
         }

         
         System.out.println("No se encuentra el usuario");
         return null; 
     }

    private static void mostrarMenuProfesorCalificador(Scanner scanner, Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Profesor Calificador ---");
            System.out.println("1. Ver estudiantes inscritos en un Learning Path");
            System.out.println("2. Revisar progreso de un estudiante");
            System.out.println("3. Evaluar actividades de un estudiante");
            System.out.println("4. Agregar una reseña a una actividad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> verEstudiantesEnLearningPath(scanner);
                case 2 -> revisarProgresoEstudiante(scanner);
                case 3 -> evaluarActividadesEstudiante(scanner);
                case 4 -> agregarResenia(scanner, profesor);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void verEstudiantesEnLearningPath(Scanner scanner) {
        System.out.println("\n--- Learning Paths Disponibles ---");
        for (LearningPath lp : learningPaths) { 
            System.out.println("ID: " + lp.getIdRuta() + " | Título: " + lp.getTitulo());
        }

        System.out.print("Ingrese el ID del Learning Path para ver sus estudiantes inscritos: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); 

        LearningPath lpSeleccionado = buscarLearningPathPorId(idLearningPath);

        if (lpSeleccionado == null) {
            System.out.println("Learning Path no encontrado.");
            return;
        }

        System.out.println("\n--- Estudiantes inscritos en el Learning Path: " + lpSeleccionado.getTitulo() + " ---");
        boolean estudiantesEncontrados = false;
        System.out.println(lpSeleccionado);

        for (Estudiante estudiante : getEstudiantes()) {
        	System.out.println(estudiante.getLearningPaths());
            if (estudiante.getLearningPaths().contains(lpSeleccionado)) {
                System.out.println("ID: " + estudiante.getId() + " | Nombre: " + estudiante.getNombre());
                estudiantesEncontrados = true;
            }
        }

        if (!estudiantesEncontrados) {
            System.out.println("No hay estudiantes inscritos en este Learning Path.");
        }
    }

    public static LearningPath buscarLearningPathPorId(int id) {
        return learningPaths.stream()
            .filter(lp -> lp.getIdRuta() == id)
            .findFirst()
            .orElse(null); 
    }
    
    private static void revisarProgresoEstudiante(Scanner scanner) {
        System.out.print("\nIngrese el ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); 

        Estudiante estudianteSeleccionado = getEstudiantes().stream()
            .filter(est -> est.getId() == idEstudiante)
            .findFirst()
            .orElse(null);

        if (estudianteSeleccionado == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("\n--- Progreso del estudiante: " + estudianteSeleccionado.getNombre() + " ---");
        for (LearningPath lp : estudianteSeleccionado.getLearningPaths()) {
            System.out.println("Learning Path: " + lp.getTitulo());
            estudianteSeleccionado.mostrarProgreso();
        }
    }

    private static void evaluarActividadesEstudiante(Scanner scanner) {
        System.out.print("\nIngrese el ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine();

        Estudiante estudianteSeleccionado = getEstudiantes().stream()
            .filter(est -> est.getId() == idEstudiante)
            .findFirst()
            .orElse(null);

        if (estudianteSeleccionado == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("\n--- Learning Paths del estudiante: " + estudianteSeleccionado.getNombre() + " ---");
        for (LearningPath lp : estudianteSeleccionado.getLearningPaths()) {
            System.out.println("ID: " + lp.getIdRuta() + " | Título: " + lp.getTitulo());
        }

        System.out.print("Ingrese el ID del Learning Path: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); 

        LearningPath lpSeleccionado = estudianteSeleccionado.getLearningPaths()
            .stream()
            .filter(lp -> lp.getIdRuta() == idLearningPath)
            .findFirst()
            .orElse(null);

        if (lpSeleccionado == null) {
            System.out.println("No estás inscrito en este Learning Path.");
            return;
        }

        System.out.println("\n--- Actividades en el Learning Path ---");
        for (Actividad actividad : lpSeleccionado.getActividades()) {
            System.out.println("ID: " + actividad.getIdActividad() + " | Título: " + actividad.getTitulo());
        }

        System.out.print("Ingrese el ID de la actividad para evaluar: ");
        int idActividad = scanner.nextInt();
        scanner.nextLine(); 

        Actividad actividadSeleccionada = lpSeleccionado.getActividades()
            .stream()
            .filter(act -> act.getIdActividad() == idActividad)
            .findFirst()
            .orElse(null);

        if (actividadSeleccionada == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }

        System.out.println("Evaluando la actividad: " + actividadSeleccionada.getTitulo());
        System.out.print("Ingrese el resultado (completado/exitoso/fallido): ");
        String resultado = scanner.nextLine();
        actividadSeleccionada.setResultado(resultado);

        System.out.println("Resultado actualizado: " + resultado);
        guardarDatos();
    }
    
    private static void agregarResenia(Scanner scanner, Profesor profesor) {
    	profesor.getLearningPaths();
        System.out.print("Ingrese el ID del Learning Path: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); 

        LearningPath lpSeleccionado = profesor.getLearningPaths()
            .stream()
            .filter(lp -> lp.getIdRuta() == idLearningPath)
            .findFirst()
            .orElse(null);

        if (lpSeleccionado == null) {
            System.out.println("No se encuentra dicho Learning Path.");
            return;
        }

        System.out.println("\n--- Actividades en el Learning Path ---");
        for (Actividad actividad : lpSeleccionado.getActividades()) {
            System.out.println("ID: " + actividad.getIdActividad() + " | Título: " + actividad.getTitulo());
        }

        System.out.print("Ingrese el ID de la actividad para agregar una reseña: ");
        int idActividad = scanner.nextInt();
        scanner.nextLine(); 

        Actividad actividadSeleccionada = lpSeleccionado.getActividades()
            .stream()
            .filter(a -> a.getIdActividad() == idActividad)
            .findFirst()
            .orElse(null);

        if (actividadSeleccionada == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }

        System.out.print("Ingrese su calificación (1-5): ");
        int calificacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese su comentario: ");
        String comentario = scanner.nextLine();

        try {
            Resenia nuevaResenia = new Resenia(calificacion, comentario, profesor, new Date());
            actividadSeleccionada.agregarResenia(nuevaResenia);
            guardarDatos();
            System.out.println("Reseña agregada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar reseña: " + e.getMessage());
        }
    }
}
