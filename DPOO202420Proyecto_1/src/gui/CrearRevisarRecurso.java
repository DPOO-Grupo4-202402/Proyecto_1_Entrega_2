package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.RevisarRecurso;
import logica.LearningPath;
import logica.Profesor;
import logica.Actividad;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class CrearRevisarRecurso extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtObjetivos;
    private JTextField txtDificultad;
    private JTextField txtDuracion;
    private JTextField txtTipoRecurso;
    private JButton btnGuardar;
    private JButton btnVolver;

    private Profesor profesor;
    private LearningPath learningPath;

    public CrearRevisarRecurso(Profesor profesor, LearningPath learningPath) {
        this.profesor = profesor;
        this.learningPath = learningPath;

        setTitle("Crear Recurso - " + learningPath.getTitulo());
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTituloVentana = new JLabel("Crear Recurso", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(7, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField();
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        txtObjetivos = new JTextField();
        txtDificultad = new JTextField();
        txtDuracion = new JTextField();
        txtTipoRecurso = new JTextField();

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

        panelFormulario.add(new JLabel("Tipo de recurso:"));
        panelFormulario.add(txtTipoRecurso);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Recurso");
        btnVolver = new JButton("Volver");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRecurso();
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

    private void guardarRecurso() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String objetivos = txtObjetivos.getText().trim();
        String dificultad = txtDificultad.getText().trim();
        String duracionStr = txtDuracion.getText().trim();
        String tipoRecurso = txtTipoRecurso.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || objetivos.isEmpty() || dificultad.isEmpty()
                || duracionStr.isEmpty() || tipoRecurso.isEmpty()) {
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

        Actividad nuevoRecurso = new RevisarRecurso(
                Actividad.getActividadesExistentes().size() + 1,
                titulo,
                descripcion,
                objetivos,
                dificultad,
                duracion,
                "pendiente",
                null,
                null,
                null,
                tipoRecurso
        );

        try {
            LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(learningPath.getIdRuta());
            lpListaGeneral.agregarActividad(nuevoRecurso);
            profesor.agregarActividadAlLearningPath(learningPath, nuevoRecurso);
            ConsolaApp.getActividades().add(nuevoRecurso);
            ConsolaApp.guardarDatos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el recurso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Recurso creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtObjetivos.setText("");
        txtDificultad.setText("");
        txtDuracion.setText("");
        txtTipoRecurso.setText("");
    }

    private void volverCrearActividad() {
        new CrearActividad(profesor);
        dispose();
    }
}
