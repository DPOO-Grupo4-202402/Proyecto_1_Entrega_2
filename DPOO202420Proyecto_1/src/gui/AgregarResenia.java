package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import logica.*;
import presentacion.ConsolaApp;

public class AgregarResenia extends JFrame {

    private JComboBox<LearningPath> comboLearningPaths;
    private JComboBox<Actividad> comboActividades;
    private JSpinner spinnerCalificacion;
    private JTextArea txtComentario;
    private JButton btnGuardar;
    private JButton btnVolver;

    private Usuario usuario;

    public AgregarResenia(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Agregar Reseña");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Agregar Reseña", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel de selección de Learning Path y actividad
        JPanel panelSeleccion = new JPanel(new GridLayout(6, 1, 10, 10));
        panelSeleccion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblLearningPath = new JLabel("Seleccione un Learning Path:");
        comboLearningPaths = new JComboBox<>(obtenerLearningPathsUsuario());
        comboLearningPaths.addActionListener(e -> actualizarActividades());

        JLabel lblActividad = new JLabel("Seleccione una Actividad:");
        comboActividades = new JComboBox<>();

        JLabel lblCalificacion = new JLabel("Ingrese una calificación (1-5):");
        spinnerCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

        JLabel lblComentario = new JLabel("Ingrese su comentario:");
        txtComentario = new JTextArea(4, 20);
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);

        panelSeleccion.add(lblLearningPath);
        panelSeleccion.add(comboLearningPaths);
        panelSeleccion.add(lblActividad);
        panelSeleccion.add(comboActividades);
        panelSeleccion.add(lblCalificacion);
        panelSeleccion.add(spinnerCalificacion);
        panelSeleccion.add(lblComentario);
        panelSeleccion.add(new JScrollPane(txtComentario));

        add(panelSeleccion, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Reseña");
        btnVolver = new JButton("Volver");

        btnGuardar.addActionListener(e -> guardarResenia());
        btnVolver.addActionListener(e -> volverMenu());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Inicializar datos
        if (comboLearningPaths.getItemCount() > 0) {
            actualizarActividades();
        } else {
            JOptionPane.showMessageDialog(this, "No hay Learning Paths disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        setVisible(true);
    }

    private LearningPath[] obtenerLearningPathsUsuario() {
        if (usuario instanceof Profesor) {
            return ConsolaApp.getLearningPaths().toArray(new LearningPath[0]);
        } else if (usuario instanceof Estudiante) {
            return ((Estudiante) usuario).getLearningPaths().toArray(new LearningPath[0]);
        }
        return new LearningPath[0];
    }

    private void actualizarActividades() {
    	LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        comboActividades.removeAllItems();

        if (lpSeleccionado != null) {
            LearningPath lpListaGeneral = ConsolaApp.buscarLearningPathPorId(lpSeleccionado.getIdRuta());
            System.out.println(lpListaGeneral);
            if (lpListaGeneral != null) {
                for (Actividad actividad : lpListaGeneral.getActividades()) {
                    comboActividades.addItem(actividad);
                }
            }
        }
    }

    private void guardarResenia() {
        LearningPath lpSeleccionado = (LearningPath) comboLearningPaths.getSelectedItem();
        Actividad actividadSeleccionada = (Actividad) comboActividades.getSelectedItem();
        Actividad actividadEspec = lpSeleccionado.getActividades()
                .stream()
                .filter(a -> a.getIdActividad() == actividadSeleccionada.getIdActividad())
                .findFirst()
                .orElse(null);
        
        int calificacion = (int) spinnerCalificacion.getValue();
        String comentario = txtComentario.getText().trim();

        if (lpSeleccionado == null || actividadSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Learning Path y una Actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (comentario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El comentario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear y guardar la reseña
        try {
            Resenia nuevaResenia = new Resenia(calificacion, comentario, usuario, new Date());
            actividadEspec.agregarResenia(nuevaResenia);
            ConsolaApp.guardarDatos();
            JOptionPane.showMessageDialog(this, "Reseña agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtComentario.setText("");
            spinnerCalificacion.setValue(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar reseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverMenu() {
        if (usuario instanceof Profesor) {
        	MenuProfesorCalificador menu = new MenuProfesorCalificador((Profesor) usuario);
            menu.setVisible(true);
           
         
        } else if (usuario instanceof Estudiante) {
        	MenuEstudiante menu = new MenuEstudiante((Estudiante) usuario);
            menu.setVisible(true);
           
        }
        dispose();
    }
}
