package gui;

import javax.swing.*;
import java.awt.*;
import logica.Actividad;
import logica.Estudiante;
import logica.LearningPath;
import logica.Profesor;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class EvaluarActividades extends JFrame {

    private JComboBox<Estudiante> comboEstudiantes;
    private JComboBox<LearningPath> comboLearningPaths;
    private JComboBox<Actividad> comboActividades;
    private JTextField txtResultado;
    private JButton btnGuardar;
    private JButton btnVolver;
    private Profesor profesor;

    public EvaluarActividades(Profesor profesor) {
    	this.profesor = profesor;
        setTitle("Evaluar Actividades");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Evaluar Actividades de un Estudiante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel de selección de estudiante, Learning Path y actividad
        JPanel panelSeleccion = new JPanel(new GridLayout(6, 1, 10, 10));
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblEstudiante = new JLabel("Seleccione un Estudiante:");
        comboEstudiantes = new JComboBox<>(ConsolaApp.getEstudiantes().toArray(new Estudiante[0]));
        comboEstudiantes.addActionListener(e -> actualizarLearningPaths());

        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");
        comboLearningPaths = new JComboBox<>();
        comboLearningPaths.addActionListener(e -> actualizarActividades());

        JLabel lblActividad = new JLabel("Seleccione una Actividad:");
        comboActividades = new JComboBox<>();

        JLabel lblResultado = new JLabel("Ingrese el resultado (completado/exitoso/fallido):");
        txtResultado = new JTextField();

        panelSeleccion.add(lblEstudiante);
        panelSeleccion.add(comboEstudiantes);
        panelSeleccion.add(lblLearningPath);
        panelSeleccion.add(comboLearningPaths);
        panelSeleccion.add(lblActividad);
        panelSeleccion.add(comboActividades);
        panelSeleccion.add(lblResultado);
        panelSeleccion.add(txtResultado);

        add(panelSeleccion, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Evaluación");
        btnVolver = new JButton("Volver");

        btnGuardar.addActionListener(e -> guardarEvaluacion());
        btnVolver.addActionListener(e -> volverMenuProfesor());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Inicializar datos
        if (comboEstudiantes.getItemCount() > 0) {
            actualizarLearningPaths();
        } else {
            JOptionPane.showMessageDialog(this, "No hay estudiantes disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        setVisible(true);
    }

    private void actualizarLearningPaths() {
        Estudiante estudianteSeleccionado = (Estudiante) comboEstudiantes.getSelectedItem();
        comboLearningPaths.removeAllItems();

        if (estudianteSeleccionado != null) {
            for (LearningPath lp : estudianteSeleccionado.getLearningPaths()) {
                comboLearningPaths.addItem(lp);
            }
            actualizarActividades();
        } else {
            comboActividades.removeAllItems();
        }
    }

    private void actualizarActividades() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(lpSeleccionado.getIdRuta());
        comboActividades.removeAllItems();

        if (lpSeleccionado != null) {
            for (Actividad actividad : lpListaGeneral.getActividades()) {
                comboActividades.addItem(actividad);
            }
        }
    }

    private void guardarEvaluacion() {
        Actividad actividadSeleccionada = (Actividad) comboActividades.getSelectedItem();
        String resultado = txtResultado.getText().trim();

        if (actividadSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un resultado para la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar el resultado de la actividad
        try {
            actividadSeleccionada.setResultado(resultado);
            ConsolaApp.guardarDatos();
            JOptionPane.showMessageDialog(this, "Evaluación guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtResultado.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la evaluación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverMenuProfesor() {
    	MenuProfesorCalificador menu = new MenuProfesorCalificador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
