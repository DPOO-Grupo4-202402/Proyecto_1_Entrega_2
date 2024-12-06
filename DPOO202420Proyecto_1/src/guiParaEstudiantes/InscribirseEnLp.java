package guiParaEstudiantes;


import javax.swing.*;

import gui.MenuEstudiante;
import gui.MenuProfesorCreador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import logica.LearningPath;
import logica.Estudiante;
import presentacion.ConsolaApp;
import presentacion.ConsolaEstudiante;

public class InscribirseEnLp extends JFrame {

    private JComboBox<LearningPath> comboLearningPaths;
    private JButton btnInscribirse;
    private JButton btnVolver;
    private Estudiante estudiante;

    public InscribirseEnLp(Estudiante estudiante) {
        this.estudiante = estudiante;

        setTitle("Inscribirse en Learning Path");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTituloVentana = new JLabel("Inscribirse en un Learning Path", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        // Panel para seleccionar Learning Path
        JPanel panelFormulario = new JPanel(new GridLayout(2, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path para inscribirse:");
        comboLearningPaths = new JComboBox<>(ConsolaApp.getLearningPaths().toArray(new LearningPath[0]));

        panelFormulario.add(lblLearningPath);
        panelFormulario.add(comboLearningPaths);

        add(panelFormulario, BorderLayout.CENTER);

        // Panel con botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnInscribirse = new JButton("Inscribirse");
        btnVolver = new JButton("Volver");

        btnInscribirse.addActionListener(this::actionInscribirse);
        btnVolver.addActionListener(e -> volverMenu());

        panelBotones.add(btnInscribirse);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

 
    private void actionInscribirse(ActionEvent e) {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        if (lpSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            estudiante.inscribirseEnLearningPath(lpSeleccionado);
            ConsolaApp.guardarDatos();
            JOptionPane.showMessageDialog(this, "¡Te has inscrito en el Learning Path exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo inscribir en el Learning Path: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Regresar al menú anterior
     */
    private void volverMenu() {
    	MenuEstudiante menu = new MenuEstudiante(estudiante);
        menu.setVisible(true);
        dispose();
    }

}
