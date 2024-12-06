package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import logica.LearningPath;
import logica.Profesor;
import logica.Estudiante;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCalificador;
import presentacion.ConsolaProfesorCreador;

public class VerEstEnLearningPath extends JFrame {
	private Profesor profesor;
    private JComboBox<LearningPath> comboLearningPaths;
    private JButton btnBuscar;
    private JTextArea txtEstudiantes;
    private JButton btnVolver;

    public VerEstEnLearningPath(Profesor profesor) {
    	this.profesor = profesor;
    	
        setTitle("Estudiantes en Learning Path");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título de la Ventana
        JLabel lblTituloVentana = new JLabel("Estudiantes en Learning Path", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        // Panel para seleccionar Learning Path
        JPanel panelFormulario = new JPanel(new GridLayout(2, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");
        comboLearningPaths = new JComboBox<>(ConsolaApp.getLearningPaths().toArray(new LearningPath[0]));

        btnBuscar = new JButton("Buscar Estudiantes");

        panelFormulario.add(lblLearningPath);
        panelFormulario.add(comboLearningPaths);
        panelFormulario.add(btnBuscar);

        add(panelFormulario, BorderLayout.NORTH);

        // Área para mostrar los estudiantes
        txtEstudiantes = new JTextArea(15, 40);
        txtEstudiantes.setEditable(false);
        txtEstudiantes.setLineWrap(true);
        txtEstudiantes.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtEstudiantes);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para volver
        btnVolver = new JButton("Volver");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Agregar listeners
        btnBuscar.addActionListener(this::actionBuscar);
        btnVolver.addActionListener(e -> volverMenu());

        setVisible(true);
    }

    /**
     * Acción para buscar estudiantes inscritos en el Learning Path.
     */
    private void actionBuscar(ActionEvent e) {
        LearningPath learningPathSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(learningPathSeleccionado.getIdRuta());
        StringBuilder sb = new StringBuilder();
        
        sb.append("Estudiantes inscritos en el Learning Path: ").append(learningPathSeleccionado.getTitulo()).append("\n\n");

        for (Estudiante estudiante : ConsolaApp.getEstudiantes()) {
        	System.out.println(estudiante.getLearningPaths() + "\n");
        	System.out.println(lpListaGeneral + "\n");
            if (estudiante.getLearningPaths().contains(lpListaGeneral)) {
                sb.append("ID: ").append(estudiante.getId())
                  .append(" | Nombre: ").append(estudiante.getNombre())
                  .append("\n");
            } else {
				System.out.println("que fue que no");
			}
        }

        if (sb.toString().trim().endsWith("\n")) {
            sb.delete(sb.length() - 2, sb.length());
        }

        txtEstudiantes.setText(sb.toString());
    }


    private void volverMenu() {
    	MenuProfesorCalificador menu = new MenuProfesorCalificador(profesor);
        menu.setVisible(true);
        dispose();
    }

}

