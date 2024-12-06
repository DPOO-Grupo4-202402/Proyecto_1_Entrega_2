package presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import logica.Estudiante;
import logica.LearningPath;
import logica.Actividad;
import logica.Resenia;

public class ConsolaEstudiante extends ConsolaApp {

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
        Estudiante estudianteActual = autenticarEstudiante(scanner);
        if (estudianteActual != null) {
            System.out.println("Bienvenido(a), " + estudianteActual.getNombre() + "!");
            mostrarMenuEstudiante(scanner, estudianteActual);
        } else {
            System.out.println("Autenticación fallida.\n");
        }
    }

    private static Estudiante autenticarEstudiante(Scanner scanner) {
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = scanner.nextLine();

        for (Estudiante estudiante : getEstudiantes()) { // estudiantes se hereda de ConsolaApp
            if (estudiante.getNombre().equalsIgnoreCase(nombreUsuario)){
            		if (estudiante.getContrasenia().equals(contrasenia)) {
                        
                        return estudiante;  // Retorna el objeto Profesor si coincide nombre y contraseña
                    } else {
                        System.out.println("Contraseña incorrecta");
                        return null;  // Devuelve null si la contraseña es incorrecta
                    }
                }
            }

            // Mensaje de error si el usuario no fue encontrado en toda la lista
            System.out.println("No se encuentra el usuario");
            return null;  // Devuelve null si no se encontró al usuario en la lista
        }

    private static void mostrarMenuEstudiante(Scanner scanner, Estudiante estudiante) {
        int opcion;
        do {
            System.out.println("\n--- Menú del Estudiante ---");
            System.out.println("1. Ver Learning Paths inscritos");
            System.out.println("2. Inscribirse en un nuevo Learning Path");
            System.out.println("3. Revisar progreso");
            System.out.println("4. Agregar una reseña a una actividad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada.

            switch (opcion) {
                case 1 -> verLearningPathsInscritos(estudiante);
                case 2 -> inscribirseEnLearningPath(scanner, estudiante);
                case 3 -> revisarProgreso(estudiante);
                case 4 -> agregarResenia(scanner, estudiante);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void verLearningPathsInscritos(Estudiante estudiante) {
        if (estudiante.getLearningPaths().isEmpty()) {
            System.out.println("No tienes Learning Paths inscritos.");
            return;
        }

        estudiante.verLearningPathsInscritos();
    }

    private static void inscribirseEnLearningPath(Scanner scanner, Estudiante estudiante) {
        System.out.println("\n--- Learning Paths Disponibles ---");
        for (LearningPath lp : getLearningPaths()) { // learningPaths se hereda de ConsolaApp
            System.out.println("ID: " + lp.getIdRuta() + " | Título: " + lp.getTitulo());
        }

        System.out.print("Ingrese el ID del Learning Path al que desea inscribirse: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        LearningPath lpSeleccionado = buscarLearningPathPorId(idLearningPath);

        if (lpSeleccionado == null) {
            System.out.println("Learning Path no encontrado.");
            return;
        }

        try {
        	
            estudiante.inscribirseEnLearningPath(lpSeleccionado);
            System.out.println("Te has inscrito correctamente en el Learning Path: " + lpSeleccionado.getTitulo());
            guardarDatos();
        } catch (Exception e) {
            System.out.println("Error al inscribirse: " + e.getMessage());
        }
    }

    public static LearningPath buscarLearningPathPorId(int id) {
        return getLearningPaths().stream()
            .filter(lp -> lp.getIdRuta() == id)
            .findFirst()
            .orElse(null); // Devuelve null si no encuentra el ID
    }

    private static void revisarProgreso(Estudiante estudiante) {
        System.out.println("\n--- Progreso en tus Learning Paths ---");
        for (LearningPath lp : estudiante.getLearningPaths()) {
            System.out.println("Learning Path: " + lp.getTitulo());
            estudiante.mostrarProgreso(); 
        }
    }

    private static void agregarResenia(Scanner scanner, Estudiante estudiante) {
        verLearningPathsInscritos(estudiante);
        System.out.print("Ingrese el ID del Learning Path: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        LearningPath lpSeleccionado = estudiante.getLearningPaths()
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

        System.out.print("Ingrese el ID de la actividad para agregar una reseña: ");
        int idActividad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

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
            Resenia nuevaResenia = new Resenia(calificacion, comentario, estudiante, new Date());
            actividadSeleccionada.agregarResenia(nuevaResenia);
            guardarDatos();
            System.out.println("Reseña agregada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar reseña: " + e.getMessage());
        }
    }
}