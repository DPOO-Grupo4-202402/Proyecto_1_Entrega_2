package guiParaEstudiantes;

import javax.swing.*;

import gui.MenuEstudiante;

import java.awt.*;
import logica.LearningPath;
import presentacion.ConsolaProfesorCreador;
import logica.Estudiante;
import logica.Actividad;

public class RevisarMiProgreso extends JFrame {

    private JComboBox<LearningPath> comboLearningPaths;
    private JTextArea txtProgreso;
    private JButton btnVolver;

    private Estudiante estudiante;

    public RevisarMiProgreso(Estudiante estudiante) {
        this.estudiante = estudiante;

        setTitle("Revisar Mi Progreso");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Revisar Mi Progreso", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel para seleccionar el Learning Path
        JPanel panelLearningPath = new JPanel(new GridLayout(2, 1, 10, 10));
        panelLearningPath.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");

        comboLearningPaths = new JComboBox<>(estudiante.getLearningPaths().toArray(new LearningPath[0]));
        comboLearningPaths.addActionListener(e -> mostrarProgreso());

        panelLearningPath.add(lblLearningPath);
        panelLearningPath.add(comboLearningPaths);
        add(panelLearningPath, BorderLayout.NORTH);

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
        btnVolver.addActionListener(e -> volverMenuEstudiante());
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Mostrar progreso inicial
        if (comboLearningPaths.getItemCount() > 0) {
            mostrarProgreso();
        } else {
            txtProgreso.setText("No estás inscrito en ningún Learning Path.");
        }

        setVisible(true);
    }

    public void mostrarProgreso() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(lpSeleccionado.getIdRuta());
        if (lpSeleccionado != null) {
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
        }
    }

    private void volverMenuEstudiante() {
       MenuEstudiante menu = new MenuEstudiante(estudiante);
       menu.setVisible(true);
        dispose();
    }
}
