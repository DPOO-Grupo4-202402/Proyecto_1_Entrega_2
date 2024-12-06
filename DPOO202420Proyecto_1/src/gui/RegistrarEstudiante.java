package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logica.Estudiante;
import presentacion.ConsolaApp;

public class RegistrarEstudiante extends JFrame{
	
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblContrasenia;
	private JLabel lblIntereses;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JPasswordField txtContrasenia;
	private JTextArea txtIntereses;
	private JButton butRegistrar;
	private JButton butVolver;
	
	public RegistrarEstudiante() {
		
		setTitle("Registrar Estudiante");
		setSize(400, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Registro de Estudiante", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelFormulario = new JPanel(new GridLayout(8, 1, 5, 5));
		
		lblNombre = new JLabel("Nombre: ");
		txtNombre = new JTextField();
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField();
		
		lblContrasenia = new JLabel("Contrasenia: ");
		txtContrasenia = new JPasswordField();
		
		lblIntereses = new JLabel("Intereses: ");
		txtIntereses = new JTextArea(3, 20);
		txtIntereses.setLineWrap(true);
		txtIntereses.setWrapStyleWord(true);
		JScrollPane scrollIntereses = new JScrollPane(txtIntereses);
		
		panelFormulario.add(lblNombre);
		panelFormulario.add(txtNombre);
		panelFormulario.add(lblEmail);
		panelFormulario.add(txtEmail);
		panelFormulario.add(lblContrasenia);
		panelFormulario.add(txtContrasenia);
		panelFormulario.add(lblIntereses);
		panelFormulario.add(scrollIntereses);
		
		add(panelFormulario, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel(new FlowLayout());
		butRegistrar = new JButton("Registrar");
		butVolver = new JButton("Volver al Menu Principal");
		
		butRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registrarEstudiante();	
			}
		});
		
		butVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volverMenuPrincipal();
				
			}
		});
		
		panelBotones.add(butRegistrar);
		panelBotones.add(butVolver);
		add(panelBotones, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void registrarEstudiante() {
		
		String nombre = txtNombre.getText().trim();
	    String email = txtEmail.getText().trim();
	    String contrasenia = new String(txtContrasenia.getPassword()).trim();
	    String intereses = txtIntereses.getText().trim();
	    
	    if (nombre.isEmpty() || email.isEmpty() || contrasenia.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    Estudiante nuevoEstudiante = new Estudiante(ConsolaApp.getEstudiantes().size() + 1, nombre, email, contrasenia, intereses);
	    ConsolaApp.getEstudiantes().add(nuevoEstudiante);
	    
	    ConsolaApp.guardarDatos();
	    JOptionPane.showMessageDialog(this, "Estudiante registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		limpiarCampos();
	}
	
	public void limpiarCampos() {
	    txtNombre.setText("");
	    txtEmail.setText("");
	    txtContrasenia.setText("");
	    txtIntereses.setText("");
	}

	private void volverMenuPrincipal() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		
		new RegistrarEstudiante();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
