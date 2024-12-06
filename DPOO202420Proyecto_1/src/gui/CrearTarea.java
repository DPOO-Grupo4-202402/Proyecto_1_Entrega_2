package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import logica.Actividad;
import logica.LearningPath;
import logica.Profesor;
import logica.Tarea;
import presentacion.ConsolaApp;
import presentacion.ConsolaProfesorCreador;

public class CrearTarea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtObjetivos;
    private JTextField txtDificultad;
    private JTextField txtDuracion;
    private JTextField txtFechaEntrega;
    private JButton btnGuardar;
    private JButton btnVolver;

    private Profesor profesor;
    private LearningPath learningPath;

    public CrearTarea(Profesor profesor, LearningPath learningPath) {
        this.profesor = profesor;
        this.learningPath = learningPath;

        setTitle("Crear Tarea - " + learningPath.getTitulo());
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTituloVentana = new JLabel("Crear Tarea", SwingConstants.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTituloVentana, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(8, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField();
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        txtObjetivos = new JTextField();
        txtDificultad = new JTextField();
        txtDuracion = new JTextField();
        txtFechaEntrega = new JTextField();

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

        panelFormulario.add(new JLabel("Fecha de entrega (dd/MM/yyyy):"));
        panelFormulario.add(txtFechaEntrega);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar Tarea");
        btnVolver = new JButton("Volver");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarTarea();
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

    private void guardarTarea() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String objetivos = txtObjetivos.getText().trim();
        String dificultad = txtDificultad.getText().trim();
        String duracionStr = txtDuracion.getText().trim();
        String fechaEntregaStr = txtFechaEntrega.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || objetivos.isEmpty() || dificultad.isEmpty()
                || duracionStr.isEmpty() || fechaEntregaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        LocalDate fechaEntrega;
        try {
            duracion = Integer.parseInt(duracionStr);
            fechaEntrega = parsearFecha(fechaEntregaStr);
            if (fechaEntrega == null) return; // Fecha inválida
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear la nueva tarea
        Actividad nuevaTarea = new Tarea(
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
                fechaEntrega
        );

        try {
            LearningPath lpListaGeneral = ConsolaProfesorCreador.buscarLearningPathPorId(learningPath.getIdRuta());
            lpListaGeneral.agregarActividad(nuevaTarea);
            profesor.agregarActividadAlLearningPath(learningPath, nuevaTarea);
            ConsolaApp.getActividades().add(nuevaTarea);
            ConsolaApp.guardarDatos();
            JOptionPane.showMessageDialog(this, "Tarea creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDate parsearFecha(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido, debe ser dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtObjetivos.setText("");
        txtDificultad.setText("");
        txtDuracion.setText("");
        txtFechaEntrega.setText("");
    }

    private void volverCrearActividad() {
        new CrearActividad(profesor);
        dispose();
    }
}

