package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.LearningPath;
import logica.Profesor;
import logica.Resenia;
import logica.Actividad;

public class VerResenias extends JFrame {

    private Profesor profesor;
    private JComboBox<LearningPath> comboLearningPaths;
    private JComboBox<Actividad> comboActividades;
    private JTextArea txtResenias;
    private JButton butVolver;

    public VerResenias(Profesor profesor) {
        this.profesor = profesor;

        setTitle("Ver Reseñas");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Ver Reseñas de Actividades", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(6, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboLearningPaths = new JComboBox<>(profesor.getLearningPaths().toArray(new LearningPath[0]));
        comboLearningPaths.addActionListener(e -> cargarActividades((LearningPath) comboLearningPaths.getSelectedItem()));

        comboActividades = new JComboBox<>();
        comboActividades.addActionListener(e -> cargarResenias((Actividad) comboActividades.getSelectedItem()));

        txtResenias = new JTextArea(10, 30);
        txtResenias.setEditable(false);

        panelFormulario.add(new JLabel("Seleccione un Learning Path:"));
        panelFormulario.add(comboLearningPaths);
        panelFormulario.add(new JLabel("Seleccione una Actividad:"));
        panelFormulario.add(comboActividades);
        panelFormulario.add(new JLabel("Reseñas:"));
        panelFormulario.add(new JScrollPane(txtResenias));

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        butVolver = new JButton("Volver");

        butVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenuProfesor();
            }
        });

        panelBotones.add(butVolver);
        add(panelBotones, BorderLayout.SOUTH);

        if (comboLearningPaths.getItemCount() > 0) {
            cargarActividades((LearningPath) comboLearningPaths.getSelectedItem());
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay learning paths creados por el/la profesor(a) " + profesor.getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
        
		
    }

    private void cargarActividades(LearningPath lp) {
        comboActividades.removeAllItems();
        if (lp != null) {
            for (Actividad actividad : lp.getActividades()) {
                comboActividades.addItem(actividad);
            } 
        }
    
            if (comboActividades.getItemCount() > 0) {
                cargarResenias((Actividad) comboActividades.getSelectedItem());
            }
        }

    private void cargarResenias(Actividad actividad) {
        txtResenias.setText("");
        if (actividad != null && !actividad.getResenias().isEmpty()) {
            for (Resenia resenia : actividad.getResenias()) {
                txtResenias.append("Calificación: " + resenia.getRating() + "\n");
                txtResenias.append("Comentario: " + resenia.getComentario() + "\n");
                txtResenias.append("Fecha: " + resenia.getFecha() + "\n");
                txtResenias.append("-------------------------\n");
            }
        } else {
            txtResenias.setText("No hay reseñas para esta actividad.");
        }
    }

    private void volverMenuProfesor() {
        MenuProfesorCreador menu = new MenuProfesorCreador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
