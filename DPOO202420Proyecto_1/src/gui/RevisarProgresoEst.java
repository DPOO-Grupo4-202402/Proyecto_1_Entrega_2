package gui;

import javax.swing.*;
import java.awt.*;
import logica.Estudiante;
import logica.LearningPath;
import logica.Profesor;
import logica.Actividad;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class RevisarProgresoEst extends JFrame {

    private JComboBox<Estudiante> comboEstudiantes;
    private JComboBox<LearningPath> comboLearningPaths;
    private JTextArea txtProgreso;
    private JButton btnVolver;
    private Profesor profesor;

    public RevisarProgresoEst(Profesor profesor) {
    	this.profesor = profesor;
    	
        setTitle("Revisar Progreso de Estudiante");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Revisar Progreso de Estudiante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel para seleccionar estudiante y Learning Path
        JPanel panelSeleccion = new JPanel(new GridLayout(4, 1, 10, 10));
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblEstudiante = new JLabel("Seleccione un Estudiante:");
        comboEstudiantes = new JComboBox<>(ConsolaApp.getEstudiantes().toArray(new Estudiante[0]));
        comboEstudiantes.addActionListener(e -> actualizarLearningPaths());

        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");
        comboLearningPaths = new JComboBox<>();
        comboLearningPaths.addActionListener(e -> mostrarProgreso());

        panelSeleccion.add(lblEstudiante);
        panelSeleccion.add(comboEstudiantes);
        panelSeleccion.add(lblLearningPath);
        panelSeleccion.add(comboLearningPaths);
        add(panelSeleccion, BorderLayout.NORTH);

        // Área de texto para mostrar el progreso
        txtProgreso = new JTextArea();
        txtProgreso.setEditable(false);
        txtProgreso.setLineWrap(true);
        txtProgreso.setWrapStyleWord(true);
        txtProgreso.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(txtProgreso), BorderLayout.CENTER);

        // Botón para volver
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volverMenuProfesor());
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Inicializar datos
        if (comboEstudiantes.getItemCount() > 0) {
            actualizarLearningPaths();
        } else {
            txtProgreso.setText("No hay estudiantes disponibles.");
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
            mostrarProgreso();
        } else {
            txtProgreso.setText("Seleccione un estudiante para ver sus Learning Paths.");
        }
    }

    private void mostrarProgreso() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        
        if (lpSeleccionado != null) {
        	LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(lpSeleccionado.getIdRuta());
            StringBuilder sb = new StringBuilder();
            sb.append("Progreso en el Learning Path: ").append(lpSeleccionado.getTitulo()).append("\n\n");
            for (Actividad actividad : lpListaGeneral.getActividades()) {
            	
            	//System.out.println(lpSeleccionado.getActividades() + "\n");
            	System.out.println(lpListaGeneral.getActividades() + "\n");
            	
                sb.append("Actividad: ").append(actividad.getTitulo())
                  .append(" | Estado: ").append(actividad.obtenerResultado() == null ? "Pendiente" : actividad.obtenerResultado())
                  .append("\n");
            }
            txtProgreso.setText(sb.toString());
        } else {
            txtProgreso.setText("Seleccione un Learning Path para ver el progreso.");
        }
    }

    private void volverMenuProfesor() {
    	MenuProfesorCalificador menu = new MenuProfesorCalificador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
