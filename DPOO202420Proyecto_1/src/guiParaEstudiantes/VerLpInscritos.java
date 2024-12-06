package guiParaEstudiantes;

import javax.swing.*;

import gui.MenuEstudiante;

import java.awt.*;
import logica.LearningPath;
import logica.Estudiante;
import presentacion.ConsolaApp;

public class VerLpInscritos extends JFrame {

    private Estudiante estudiante;
    private JList<LearningPath> listaLearningPaths;
    private JButton btnVolver;

    public VerLpInscritos(Estudiante estudiante) {
        this.estudiante = estudiante;

        setTitle("Learning Paths Inscritos");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTituloVentana = new JLabel("Learning Paths Inscritos", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        // Panel con la lista de Learning Paths
        DefaultListModel<LearningPath> listModel = new DefaultListModel<>();
        for (LearningPath lp : estudiante.getLearningPaths()) {
            listModel.addElement(lp);
        }

        listaLearningPaths = new JList<>(listModel);
        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaLearningPaths);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para regresar
        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver al Menú");

        btnVolver.addActionListener(e -> volverMenu());

        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void volverMenu() {
        MenuEstudiante menu = new MenuEstudiante(estudiante);
        menu.setVisible(true);
        dispose();
    }

}
