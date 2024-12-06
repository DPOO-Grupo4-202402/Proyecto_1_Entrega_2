package gui;

import javax.swing.*;
import java.awt.*;
import logica.LearningPath;
import logica.Profesor;

public class CrearActividad extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Profesor profesor;
    private JComboBox<LearningPath> comboLearningPaths;
    private JButton btnTarea;
    private JButton btnQuiz;
    private JButton btnExamen;
    private JButton btnEncuesta;
    private JButton btnRevisarRecurso;
    private JButton btnVolver;

    public CrearActividad(Profesor profesor) {
        this.profesor = profesor;

        setTitle("Crear Actividad");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Crear Actividad", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel para seleccionar el Learning Path
        JPanel panelLearningPath = new JPanel(new GridLayout(2, 1, 10, 10));
        panelLearningPath.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");
        comboLearningPaths = new JComboBox<>(profesor.getLearningPaths().toArray(new LearningPath[0]));

        panelLearningPath.add(lblLearningPath);
        panelLearningPath.add(comboLearningPaths);
        add(panelLearningPath, BorderLayout.NORTH);

        // Panel para botones de actividades
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnTarea = new JButton("Crear Tarea");
        btnQuiz = new JButton("Crear Quiz");
        btnExamen = new JButton("Crear Examen");
        btnEncuesta = new JButton("Crear Encuesta");
        btnRevisarRecurso = new JButton("Crear Revisar Recurso");

        btnTarea.addActionListener(e -> abrirVentanaTarea());
        btnQuiz.addActionListener(e -> abrirVentanaQuiz());
        btnExamen.addActionListener(e -> abrirVentanaExamen());
        btnEncuesta.addActionListener(e -> abrirVentanaEncuesta());
        btnRevisarRecurso.addActionListener(e -> abrirVentanaRevisarRecurso());

        panelBotones.add(btnTarea);
        panelBotones.add(btnQuiz);
        panelBotones.add(btnExamen);
        panelBotones.add(btnEncuesta);
        panelBotones.add(btnRevisarRecurso);

        add(panelBotones, BorderLayout.CENTER);

        // Panel para botón de volver
        JPanel panelVolver = new JPanel(new FlowLayout());
        btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> volverMenuProfesor());
        panelVolver.add(btnVolver);
        add(panelVolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    private LearningPath obtenerLearningPathSeleccionado() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        if (lpSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lpSeleccionado;
    }

    private void abrirVentanaTarea() {
        LearningPath lp = obtenerLearningPathSeleccionado();
        if (lp != null) {
            new CrearTarea(profesor, lp);
            dispose();
        }
    }

    private void abrirVentanaQuiz() {
        LearningPath lp = obtenerLearningPathSeleccionado();
        if (lp != null) {
            new CrearQuiz(profesor, lp);
            dispose();
        }
    }

    private void abrirVentanaExamen() {
        LearningPath lp = obtenerLearningPathSeleccionado();
        if (lp != null) {
            new CrearExamen(profesor, lp);
            dispose();
        }
    }

    private void abrirVentanaEncuesta() {
        LearningPath lp = obtenerLearningPathSeleccionado();
        if (lp != null) {
            new CrearEncuesta(profesor, lp);
            dispose();
        }
    }

    private void abrirVentanaRevisarRecurso() {
        LearningPath lp = obtenerLearningPathSeleccionado();
        if (lp != null) {
            new CrearRevisarRecurso(profesor, lp);
            dispose();
        }
    }

    private void volverMenuProfesor() {
        MenuProfesorCreador menu = new MenuProfesorCreador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
