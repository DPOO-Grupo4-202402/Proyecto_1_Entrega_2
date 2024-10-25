package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.Actividad;
import logica.Encuesta;
import logica.Estudiante;
import logica.Examen;
import logica.LearningPath;
import logica.Profesor;
import logica.Progreso;
import logica.Quiz;
import logica.Resenia;
import logica.RevisarRecurso;
import logica.Tarea;
import logica.Usuario;

public class PersistenciaDatos {
	
    public static void guardarProfesores(List<Profesor> profesores) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profesores.csv"))) {
            for (Profesor profesor : profesores) {
                String linea = profesor.getId() + "," + profesor.getNombre() + "," + 
                               profesor.getEmail() + "," + profesor.getContrasenia();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    
    public static List<Profesor> cargarProfesores() throws IOException {
        List<Profesor> profesores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("profesores.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String email = campos[2];
                String contrasenia = campos[3];
                Profesor profesor = new Profesor(id, nombre, email, contrasenia);
                profesores.add(profesor);
            }
        }
        return profesores;
    }
    
    public static void guardarEstudiantes(List<Estudiante> estudiantes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estudiantes.csv"))) {
            for (Estudiante estudiante : estudiantes) {
                String linea = estudiante.getId() + "," + estudiante.getNombre() + "," + 
                               estudiante.getEmail() + "," + estudiante.getContrasenia() +
                               estudiante.getIntereses();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public static List<Estudiante> cargarEstudiante() throws IOException {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("estudiantes.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String email = campos[2];
                String contrasenia = campos[3];
                String intereses = campos[4];
                Estudiante estudiante = new Estudiante(id, nombre, email, contrasenia, intereses);
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    public static void guardarLearningPaths(List<LearningPath> learningPaths) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("learningPaths.csv"))) {
            for (LearningPath lp : learningPaths) {
                String linea = lp.getIdRuta() + "," + lp.getTitulo() + "," + lp.getDescripcion() + "," +
                               lp.getObjetivos() + "," + lp.getDificultad();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public static List<LearningPath> cargarLearningPaths() throws IOException {
        List<LearningPath> learningPaths = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("learningPaths.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                int idRuta = Integer.parseInt(campos[0]);
                String titulo = campos[1];
                String descripcion = campos[2];
                String objetivos = campos[3];
                String dificultad = campos[4];
                LearningPath lp = new LearningPath(idRuta, titulo, descripcion, objetivos, dificultad);
                learningPaths.add(lp);
            }
        }
        return learningPaths;
    }

    public void guardarActividades(ArrayList<Actividad> actividades, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            for (Actividad actividad : actividades) {
                String linea = actividad.getIdActividad() + "," +
                               actividad.getTitulo() + "," +
                               actividad.getDescripcion().replace(",", ";") + "," +
                               actividad.getObjetivos() + "," +
                               actividad.getDificultad() + "," +
                               actividad.getResultado() + "," +
                               actividad.getClass().getSimpleName(); 

                if (actividad instanceof Tarea) {
                    Tarea tarea = (Tarea) actividad;
                    linea += "," + tarea.getFechaEntrega().getTime() + "," + tarea.getEstado();
                } else if (actividad instanceof Quiz) {
                    Quiz quiz = (Quiz) actividad;
                    linea += "," + quiz.getDuracion() + "," + quiz.getCalificacionMinima() + "," + quiz.getCalificacionObtenida();
                } else if (actividad instanceof Examen) {
                    Examen examen = (Examen) actividad;
                    linea += "," + examen.getDuracion() + "," + examen.getCalificacionObtenida() + "," + examen.getCalificacionMinima() + "," + examen.getEstado();
                } else if (actividad instanceof Encuesta) {
                    Encuesta encuesta = (Encuesta) actividad;
                    linea += "," + encuesta.getDuracion();
                } else if (actividad instanceof RevisarRecurso) {
                    RevisarRecurso recurso = (RevisarRecurso) actividad;
                    linea += "," + recurso.getTipo();
                }

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error al guardar las actividades", e);
        }
    }


    public ArrayList<Actividad> cargarActividades(String rutaArchivo) throws IOException {
        ArrayList<Actividad> actividades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int idActividad = Integer.parseInt(datos[0]);
                String titulo = datos[1];
                String descripcion = datos[2].replace(";", ",");
                String objetivos = datos[3];
                String dificultad = datos[4];
                String resultado = datos[5];
                String tipoActividad = datos[6];
                
                Actividad actividad = null;

                switch (tipoActividad) {
                    case "Tarea":
                        Date fechaEntrega = new Date(Long.parseLong(datos[7]));
                        String estado = datos[8];
                        actividad = new Tarea(idActividad, titulo, descripcion, objetivos, dificultad, dificultad, resultado, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), fechaEntrega);
                        ((Tarea) actividad).setEstado(estado);
                        break;

                    case "Quiz":
                        int duracionQuiz = Integer.parseInt(datos[7]);
                        double calificacionMinimaQuiz = Double.parseDouble(datos[8]);
                        double calificacionObtenidaQuiz = Double.parseDouble(datos[9]);
                        actividad = new Quiz(idActividad, titulo, descripcion, objetivos, dificultad, dificultad, resultado, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), duracionQuiz, calificacionMinimaQuiz, calificacionObtenidaQuiz);
                        break;

                    case "Examen":
                        int duracionExamen = Integer.parseInt(datos[7]);
                        double calificacionObtenidaExamen = Double.parseDouble(datos[8]);
                        double calificacionMinimaExamen = Double.parseDouble(datos[9]);
                        String estadoExamen = datos[10];
                        actividad = new Examen(idActividad, titulo, descripcion, objetivos, dificultad, dificultad, resultado, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), duracionExamen, calificacionObtenidaExamen, calificacionMinimaExamen, estadoExamen);
                        break;

                    case "Encuesta":
                        int duracionEncuesta = Integer.parseInt(datos[7]);
                        actividad = new Encuesta(idActividad, titulo, descripcion, objetivos, dificultad, dificultad, resultado, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), duracionEncuesta);
                        break;

                    case "RevisarRecurso":
                        String tipoRecurso = datos[7];
                        actividad = new RevisarRecurso(idActividad, titulo, descripcion, objetivos, dificultad, dificultad, resultado, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), tipoRecurso);
                        break;

                    default:
                        throw new IllegalArgumentException("Tipo de actividad desconocido: " + tipoActividad);
                }

                actividades.add(actividad);
            }
        } catch (IOException e) {
            throw new IOException("Error al cargar las actividades", e);
        }
        return actividades;
    }
    
    public static void guardarProgresos(List<Progreso> progresos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("progresos.csv"))) {
            for (Progreso progreso : progresos) {
                String linea = progreso.getFechaIniciado() + "," + progreso.getFechaCompletado() + "," +
                               progreso.getResultado() + "," + progreso.getUsuario().getId() + "," +
                               progreso.getLearningPath().getIdRuta();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public static List<Progreso> cargarProgresos(List<Usuario> usuarios, List<LearningPath> learningPaths) throws IOException {
        List<Progreso> progresos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("progresos.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                Date fechaIniciado = new Date(Long.parseLong(campos[0])); 
                Date fechaCompletado = new Date(Long.parseLong(campos[1])); 
                String resultado = campos[2];
                int idUsuario = Integer.parseInt(campos[3]);
                int idRuta = Integer.parseInt(campos[4]);

                Usuario usuario = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst().orElse(null);
                LearningPath lp = learningPaths.stream().filter(l -> l.getIdRuta() == idRuta).findFirst().orElse(null);
                Progreso progreso = new Progreso(fechaIniciado, usuario, lp);
                progreso.setFechaCompletado(fechaCompletado);
                progreso.setResultado(resultado);
                progresos.add(progreso);
            }
        }
        return progresos;
    }

    public static void guardarResenias(List<Resenia> resenias) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resenias.csv"))) {
            for (Resenia resenia : resenias) {
                String linea = resenia.getAutor() + "," + resenia.getComentario() + "," + resenia.getRating();
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    public ArrayList<Resenia> cargarResenias(String rutaArchivo, ArrayList<Usuario> usuarios) throws IOException {
        ArrayList<Resenia> resenias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int autorId = Integer.parseInt(datos[0]);
                int rating = Integer.parseInt(datos[1]);
                String comentario = datos[2].replace(";", ",");
                Date fecha = new Date(Long.parseLong(datos[3]));

                Usuario autor = usuarios.stream()
                                        .filter(user -> user.getId() == autorId)
                                        .findFirst()
                                        .orElse(null);

                if (autor != null) {
                    Resenia resenia = new Resenia(rating, comentario, autor, fecha);
                    resenias.add(resenia);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error al cargar las resenias", e);
        }
        return resenias;
    }

}
