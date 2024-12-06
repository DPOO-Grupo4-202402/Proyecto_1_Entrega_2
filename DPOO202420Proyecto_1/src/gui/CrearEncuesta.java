package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.Encuesta;
import logica.LearningPath;
import logica.Profesor;
import logica.Actividad;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class CrearEncuesta extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtObjetivos;
    private JTextField txtDificultad;
    private JTextField txtDuracion;
    private JButton btnGuardar;
    private JButton btnVolver;

    private Profesor profesor;
    private LearningPath learningPath;

    public CrearEncuesta(Profesor profesor, LearningPath learningPath) {
        this.profesor = profesor;
        this.learningPath = learningPath;

        setTitle("Crear Encuesta - " + learningPath.getTitulo());
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTituloVentana = new JLabel("Crear Encuesta", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(6, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField();
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        txtObjetivos = new JTextField();
        txtDificultad = new JTextField();
        txtDuracion = new JTextField();

        panelFormulario.add(new JLabel("Título:"));
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Descripción:"));
        panelFormulario.add(new JScrollPane(txtDescripcion));

        panelFormulario.add(new JLabel("Objetivos:"));
        panelFormulario.add(txtObjetivos);

        panelFormulario.add(new JLabel("Nivel de dificultad:"));
        panelFormulario.add(txtDificultad);

        panelFormulario.add(new JLabel("Duración estimada (min):"));
        panelFormulario.add(txtDuracion);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Encuesta");
        btnVolver = new JButton("Volver");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEncuesta();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverCrearActividad();
            }
        });

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void guardarEncuesta() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String objetivos = txtObjetivos.getText().trim();
        String dificultad = txtDificultad.getText().trim();
        String duracionStr = txtDuracion.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || objetivos.isEmpty() || dificultad.isEmpty()
                || duracionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Actividad nuevaEncuesta = new Encuesta(
                Actividad.getActividadesExistentes().size() + 1,
                titulo,
                descripcion,
                objetivos,
                dificultad,
                duracion,
                "pendiente",
                null,
                null,
                null
        );

        try {
            LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(learningPath.getIdRuta());
            lpListaGeneral.agregarActividad(nuevaEncuesta);
            profesor.agregarActividadAlLearningPath(learningPath, nuevaEncuesta);
            ConsolaApp.getActividades().add(nuevaEncuesta);
            ConsolaApp.guardarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar la encuesta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Encuesta creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtObjetivos.setText("");
        txtDificultad.setText("");
        txtDuracion.setText("");
    }

    private void volverCrearActividad() {
        new CrearActividad(profesor);
        dispose();
    }
}
