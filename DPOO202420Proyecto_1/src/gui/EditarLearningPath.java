package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.LearningPath;
import logica.Profesor;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class EditarLearningPath extends JFrame {

    private Profesor profesor;
    private JComboBox<LearningPath> comboLearningPaths;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextArea txtObjetivos;
    private JTextField txtDificultad;
    private JButton butGuardar;
    private JButton butVolver;

    public EditarLearningPath(Profesor profesor) {
        this.profesor = profesor;

        setTitle("Editar Learning Path");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Editar Learning Path", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(10, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        comboLearningPaths = new JComboBox<>(profesor.getLearningPaths().toArray(new LearningPath[0]));
        comboLearningPaths.addActionListener(e -> cargarLearningPath((LearningPath) comboLearningPaths.getSelectedItem()));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JLabel lblObjetivos = new JLabel("Objetivos:");
        txtObjetivos = new JTextArea(3, 20);
        txtObjetivos.setLineWrap(true);
        txtObjetivos.setWrapStyleWord(true);

        JLabel lblDificultad = new JLabel("Dificultad:");
        txtDificultad = new JTextField();

        panelFormulario.add(new JLabel("Seleccione un Learning Path:"));
        panelFormulario.add(comboLearningPaths);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblDescripcion);
        panelFormulario.add(new JScrollPane(txtDescripcion));
        panelFormulario.add(lblObjetivos);
        panelFormulario.add(new JScrollPane(txtObjetivos));
        panelFormulario.add(lblDificultad);
        panelFormulario.add(txtDificultad);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        butGuardar = new JButton("Guardar Cambios");
        butVolver = new JButton("Volver");

        butGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        butVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenuProfesor();
            }
        });

        panelBotones.add(butGuardar);
        panelBotones.add(butVolver);
        add(panelBotones, BorderLayout.SOUTH);

        if (comboLearningPaths.getItemCount() > 0) {
            cargarLearningPath((LearningPath) comboLearningPaths.getSelectedItem());
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay learning paths creados por el/la profesor(a) " + profesor.getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    private void cargarLearningPath(LearningPath lp) {
        if (lp != null) {
            txtNombre.setText(lp.getTitulo());
            txtDescripcion.setText(lp.getDescripcion());
            txtObjetivos.setText(lp.getObjetivos());
            txtDificultad.setText(lp.getDificultad());
        }
     
    }

    private void guardarCambios() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(lpSeleccionado.getIdRuta());
        
        lpSeleccionado.setTitulo(txtNombre.getText().trim());
		lpSeleccionado.setDescripcion(txtDescripcion.getText().trim());
		lpSeleccionado.setObjetivos(txtObjetivos.getText().trim());
		lpSeleccionado.setDificultad(txtDificultad.getText().trim());
		
		lpListaGeneral.setTitulo(txtNombre.getText().trim());
		lpListaGeneral.setDescripcion(txtDescripcion.getText().trim());
		lpListaGeneral.setObjetivos(txtObjetivos.getText().trim());
		lpListaGeneral.setDificultad(txtDificultad.getText().trim());

		JOptionPane.showMessageDialog(this, "Learning Path actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		ConsolaApp.guardarDatos();
    }

    private void volverMenuProfesor() {
        MenuProfesorCreador menu = new MenuProfesorCreador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
