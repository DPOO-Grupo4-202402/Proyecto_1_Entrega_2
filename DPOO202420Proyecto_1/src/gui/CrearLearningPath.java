package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.LearningPath;
import logica.Profesor;
import presentacion.ConsolaApp;

public class CrearLearningPath extends JFrame {

    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblObjetivos;
    private JLabel lblDificultad;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextArea txtObjetivos;
    private JTextField txtDificultad;
    private JButton butCrear;
    private JButton butVolver;

    private Profesor profesor;

    public CrearLearningPath(Profesor profesor) {
        this.profesor = profesor;

        setTitle("Crear Learning Path");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Crear un nuevo Learning Path", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(8, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblNombre = new JLabel("Nombre del Learning Path:");
        txtNombre = new JTextField();

        lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        lblObjetivos = new JLabel("Objetivos:");
        txtObjetivos = new JTextArea(3, 20);
        txtObjetivos.setLineWrap(true);
        txtObjetivos.setWrapStyleWord(true);

        lblDificultad = new JLabel("Dificultad:");
        txtDificultad = new JTextField();

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
        butCrear = new JButton("Crear");
        butVolver = new JButton("Volver");

        butCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearLearningPath();
            }
        });

        butVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenuProfesor();
            }
        });

        panelBotones.add(butCrear);
        panelBotones.add(butVolver);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void crearLearningPath() {
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String objetivos = txtObjetivos.getText().trim();
        String dificultad = txtDificultad.getText().trim();

        // Validar campos
        if (nombre.isEmpty() || descripcion.isEmpty() || objetivos.isEmpty() || dificultad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el nuevo Learning Path
        LearningPath nuevoLP = new LearningPath(ConsolaApp.getLearningPaths().size() + 1,nombre,descripcion,objetivos,dificultad);

        // Añadir el Learning Path a las listas
        ConsolaApp.getLearningPaths().add(nuevoLP);
        profesor.crearLearningPath(nuevoLP.getIdRuta(),nuevoLP.getTitulo(),nuevoLP.getDescripcion(),nuevoLP.getObjetivos(),nuevoLP.getDificultad());

        // Guardar los datos
        ConsolaApp.guardarDatos();

        JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar los campos del formulario
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtObjetivos.setText("");
        txtDificultad.setText("");
    }

    private void volverMenuProfesor() {
        MenuProfesorCreador menu = new MenuProfesorCreador(profesor);
        menu.setVisible(true);
        dispose();
    }
}
