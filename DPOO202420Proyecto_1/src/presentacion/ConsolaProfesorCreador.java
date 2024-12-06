package presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import logica.LearningPath;
import logica.Profesor;
import logica.Actividad;
import logica.Quiz;
import logica.RevisarRecurso;
import logica.Examen;
import logica.Encuesta;
import logica.Tarea;
import logica.Resenia;
//import presentacion.ConsolaApp;

public class ConsolaProfesorCreador extends ConsolaApp {
    public static void main(String[] args) {
    	System.out.println("Carga de datos..."+ "\n");
    	cargarDatos();
    	Scanner scanner = new Scanner(System.in);
    	int opcion;
    	
    	System.out.println("--------------------------------------------------------"+ "\n");
        do {
        	System.out.println("0. Cerrar");
        	System.out.println("1. Iniciar sesión"+ "\n");
        	System.out.println("Selecciona una opción: ");
        	opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcion) {
            case 0 -> System.out.println("Saliendo...");
            case 1 -> iniciarSesion(scanner);
            default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        	 
        
        	
        } while (opcion !=0);
        scanner.close();
    }

    private static void iniciarSesion (Scanner scanner) {
    	Profesor profesorActual = autenticarProfesor(scanner);
        if (profesorActual != null) {
        	System.out.println("Bienvenido(a), " + profesorActual.getNombre() + "!");
            mostrarMenuProfesor(scanner, profesorActual);
        } else {
            System.out.println("Autenticación fallida."+"\n");
            return;
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

    private static void mostrarMenuProfesor(Scanner scanner, Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Profesor Creador ---");
            System.out.println("1. Crear nuevo Learning Path");
            System.out.println("2. Editar Learning Path existente");
            System.out.println("3. Ver reseñas de actividades");
            System.out.println("4. Crear actividad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1 -> crearLearningPath(scanner, profesor);
                case 2 -> editarLearningPath(scanner, profesor);
                case 3 -> verResenias(scanner, profesor);
                case 4 -> crearActividad(scanner, profesor);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void crearLearningPath(Scanner scanner, Profesor profesor) {
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
         System.out.println("Learning Path creado exitosamente. el otro");
         profesor.crearLearningPath(learningPaths.size(), nombre, descripcion, objetivos, dificultad);
         System.out.println(profesor.getLearningPaths());
         guardarDatos();
    }

    private static void editarLearningPath(Scanner scanner, Profesor profesor) {
        if (profesor.getLearningPaths().isEmpty()) {
            System.out.println("No hay Learning Paths disponibles para editar.");
            return;
        }

        System.out.println("\n--- Learning Paths Disponibles ---");
        for (int i = 0; i < profesor.getLearningPaths().size(); i++) {
            LearningPath lp = profesor.getLearningPaths().get(i);
            System.out.println((i + 1) + ". " + lp.getTitulo());
        }

        System.out.print("Seleccione el número del Learning Path a editar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= profesor.getLearningPaths().size()) {
            System.out.println("Selección inválida.");
            return;
        }

        LearningPath lpSeleccionado = profesor.getLearningPaths().get(index);
        LearningPath lpListaGeneral = buscarLearningPathPorId(lpSeleccionado.getIdRuta());

        System.out.println("\n--- Opciones de Edición ---");
        System.out.println("1. Editar nombre");
        System.out.println("2. Editar descripción");
        System.out.println("3. Editar objetivos");
        System.out.println("4. Editar nivel de dificultad");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1 -> {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                lpSeleccionado.setTitulo(nuevoNombre);
                lpListaGeneral.setTitulo(nuevoNombre);
            }
            case 2 -> {
                System.out.print("Nueva descripción: ");
                String nuevaDescripcion = scanner.nextLine();
                lpSeleccionado.setDescripcion(nuevaDescripcion);
                lpListaGeneral.setDescripcion(nuevaDescripcion);
            }
            case 3 -> {
                System.out.print("Nuevos objetivos: ");
                String nuevosObjetivos = scanner.nextLine();
                lpSeleccionado.setObjetivos(nuevosObjetivos);
                lpListaGeneral.setObjetivos(nuevosObjetivos);
            }
            case 4 -> {
                System.out.print("Nuevo nivel de dificultad: ");
                String nuevaDificultad = scanner.nextLine();
                lpSeleccionado.setDificultad(nuevaDificultad);
                lpListaGeneral.setDificultad(nuevaDificultad);
            }
            case 0 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida.");
        }

        guardarDatos();
        profesor.verLearningPaths();
        System.out.println("Learning Path actualizado exitosamente.");
    }

    public static LearningPath buscarLearningPathPorId(int id) {
        return learningPaths.stream()
            .filter(lp -> lp.getIdRuta() == id)
            .findFirst()
            .orElse(null); 
    }
    
    private static void crearActividad(Scanner scanner, Profesor profesor) {
        System.out.print("Ingrese el ID del Learning Path donde desea agregar la actividad: ");
        int idLearningPath = scanner.nextInt();
        scanner.nextLine(); 

       // LearningPath lpSeleccionado = buscarLearningPathPorId(idLearningPath);
        LearningPath lpSeleccionado = profesor.getLearningPaths()
        	    .stream()
        	    .filter(lp -> lp.getIdRuta() == idLearningPath)
        	    .findFirst()
        	    .orElse(null);

        if (lpSeleccionado == null) {
            System.out.println("Learning Path no encontrado.");
            return;
        }

        System.out.println("Creando una nueva actividad para el Learning Path: " + lpSeleccionado.getTitulo());

        System.out.println("Seleccione el tipo de actividad:");
        System.out.println("1. Tarea");
        System.out.println("2. Quiz");
        System.out.println("3. Examen");
        System.out.println("4. Encuesta");
        System.out.println("5. Revisar recurso");
        System.out.print("Ingrese su opción: ");
        int tipoActividad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Actividad nuevaActividad = null;
        switch (tipoActividad) {
            case 1 -> {
            	System.out.print("Ingrese el título de la tarea: ");
            	String titulo = scanner.nextLine();
                System.out.print("Ingrese la descripción de la tarea: ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el objetivo de la tarea: ");
                String objetivo = scanner.nextLine();
                System.out.print("Ingrese el nivel de dificultad: ");
                String dificultad = scanner.nextLine();
                System.out.print("Ingrese la duración estimada (en minutos): ");
                int duracion = scanner.nextInt();
                Date fechaEntrega = solicitarFecha(scanner);
                scanner.nextLine(); 
                nuevaActividad = new Tarea(Actividad.getActividadesExistentes().size() + 1, titulo, descripcion, objetivo, dificultad, duracion, "pendiente", null, null, null, fechaEntrega);
            }
            case 2 -> {
            	System.out.print("Ingrese el título del quiz: ");
            	String titulo = scanner.nextLine();
                System.out.print("Ingrese la descripción del quiz: ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el objetivo del quiz: ");
                String objetivo = scanner.nextLine();
                System.out.print("Ingrese el nivel de dificultad: ");
                String dificultad = scanner.nextLine();
                System.out.print("Ingrese la duración estimada (en minutos): ");
                int duracion = scanner.nextInt();
                System.out.print("Ingrese la calificación mínima de quiz ");
            	double calMinima = scanner.nextDouble();
                scanner.nextLine(); 
                nuevaActividad = new Quiz(Actividad.getActividadesExistentes().size() + 1, titulo, descripcion, objetivo, dificultad, duracion, "pendiente", null, null, null, calMinima, (Double) null);
            }
            case 3 -> {
            	System.out.print("Ingrese el título del examen: ");
            	String titulo = scanner.nextLine();
                System.out.print("Ingrese la descripción del examen: ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el objetivo del examen: ");
                String objetivo = scanner.nextLine();
                System.out.print("Ingrese el nivel de dificultad: ");
                String dificultad = scanner.nextLine();
                System.out.print("Ingrese la duración estimada (en minutos): ");
                int duracion = scanner.nextInt();
                System.out.print("Ingrese la calificación mínima del examen ");
            	double calMinima = scanner.nextDouble();
                scanner.nextLine(); 
                nuevaActividad = new Examen(Actividad.getActividadesExistentes().size() + 1, titulo, descripcion, objetivo, dificultad, duracion, "pendiente", null, null, null, calMinima, (Double) null, null);
            }
            case 4 -> {
            	System.out.print("Ingrese el título de la encuesta: ");
            	String titulo = scanner.nextLine();
                System.out.print("Ingrese la descripción de la encuesta: ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el objetivo de la encuesta: ");
                String objetivo = scanner.nextLine();
                System.out.print("Ingrese el nivel de dificultad: ");
                String dificultad = scanner.nextLine();
                System.out.print("Ingrese la duración estimada (en minutos): ");
                int duracion = scanner.nextInt();
                scanner.nextLine(); 
                nuevaActividad = new Encuesta(Actividad.getActividadesExistentes().size() + 1, titulo, descripcion, objetivo, dificultad, duracion, "pendiente", null, null, null);
            }
            case 5 -> {
            	System.out.print("Ingrese el título del recurso: ");
            	String titulo = scanner.nextLine();
                System.out.print("Ingrese la descripción del recurso: ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el objetivo del recurso: ");
                String objetivo = scanner.nextLine();
                System.out.print("Ingrese el nivel del recurso: ");
                String dificultad = scanner.nextLine();
                System.out.print("Ingrese la duración estimada (en minutos): ");
                int duracion = scanner.nextInt();
                System.out.print("Ingrese el tipo del recurso: ");
                String tipo = scanner.nextLine();
                scanner.nextLine(); 
                nuevaActividad = new RevisarRecurso(Actividad.getActividadesExistentes().size() + 1, titulo, descripcion, objetivo, dificultad, duracion, "pendiente", null, null, null, tipo);
            }
            default -> {
                System.out.println("Tipo de actividad no válido.");
                return;
            }
        }

        try {
        	LearningPath lpListaGeneral = buscarLearningPathPorId(lpSeleccionado.getIdRuta());
            lpListaGeneral.agregarActividad(nuevaActividad);
            profesor.agregarActividadAlLearningPath(lpSeleccionado, nuevaActividad);
            actividades.add(nuevaActividad);
            System.out.println(actividades);
            guardarDatos();
            System.out.println("Actividad creada y agregada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar la actividad: " + e.getMessage());
        }
    } 
    
    private static Date solicitarFecha(Scanner scanner) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false); 
        Date fecha = null;

        while (fecha == null) {
            System.out.print("Ingrese la fecha de entrega (formato dd/MM/yyyy): ");
            String entrada = scanner.nextLine();

            try {
                fecha = formatoFecha.parse(entrada);
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Intente nuevamente.");
            }
        }

        return fecha;
    }

    private static void verResenias(Scanner scanner, Profesor profesor) {
        if (profesor.getLearningPaths().isEmpty()) {
            System.out.println("No hay Learning Paths disponibles para consultar reseñas.");
            return;
        }

        System.out.println("\n--- Learning Paths Disponibles ---");
        for (int i = 0; i < profesor.getLearningPaths().size(); i++) {
            LearningPath lp = profesor.getLearningPaths().get(i);
            System.out.println((i + 1) + ". " + lp.getTitulo());
        }

        System.out.print("Seleccione el número del Learning Path para consultar reseñas: ");
        int indexLP = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (indexLP < 0 || indexLP >= profesor.getLearningPaths().size()) {
            System.out.println("Selección inválida.");
            return;
        }

        LearningPath lpSeleccionado = profesor.getLearningPaths().get(indexLP);

        if (lpSeleccionado.getActividades().isEmpty()) {
            System.out.println("El Learning Path seleccionado no tiene actividades.");
            return;
        }

        System.out.println("\n--- Actividades en el Learning Path ---");
        for (int i = 0; i < lpSeleccionado.getActividades().size(); i++) {
            Actividad actividad = lpSeleccionado.getActividades().get(i);
            System.out.println((i + 1) + ". " + actividad.getTitulo());
        }

        System.out.print("Seleccione el número de la actividad para ver sus reseñas: ");
        int indexActividad = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (indexActividad < 0 || indexActividad >= lpSeleccionado.getActividades().size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Actividad actividadSeleccionada = lpSeleccionado.getActividades().get(indexActividad);

        if (actividadSeleccionada.getResenias().isEmpty()) {
            System.out.println("La actividad seleccionada no tiene reseñas.");
            return;
        }

        System.out.println("\n--- Reseñas de la Actividad ---");
        for (Resenia resenia : actividadSeleccionada.getResenias()) {
            System.out.println("Calificación: " + resenia.getRating());
            System.out.println("Comentario: " + resenia.getComentario());
            System.out.println("Fecha: " + resenia.getFecha());
            System.out.println("-------------------------");
        }
    }


    
    
}