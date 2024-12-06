package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.Profesor;
import presentacion.ConsolaApp;

public class IniciarSesionProfesorCreador extends JFrame{
	
	private JLabel lblTitulo;
	private JLabel lblEmail;
	private JLabel lblContrasenia;
	private JTextField txtEmail;
	private JPasswordField txtContrasenia;
	private JButton butIngresar;
	private JButton butVolver;
	
	public IniciarSesionProfesorCreador() {
		
		setTitle("Iniciar Sesion como Profesor Creador");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Inicio de Sesion para Profesores Creadores", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelFormulario = new JPanel(new GridLayout(4, 1, 5, 5));
		
		lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField();
		
		lblContrasenia =  new JLabel("Contrasenia: ");
		txtContrasenia = new JPasswordField();
		
		panelFormulario.add(lblEmail);
		panelFormulario.add(txtEmail);
		panelFormulario.add(lblContrasenia);
		panelFormulario.add(txtContrasenia);
		
		add(panelFormulario, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel(new FlowLayout());
		
		butIngresar = new JButton("Ingresar");
		butVolver = new JButton("Volver al Menu Principal");
		
		butIngresar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();	
			}
		});
		
		butVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volverMenuPrincipal();
				
			}
		});
		
		panelBotones.add(butIngresar);
		panelBotones.add(butVolver);
		
		add(panelBotones, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private void iniciarSesion(){
		
		String email = txtEmail.getText().trim();
	    String contrasenia = new String(txtContrasenia.getPassword()).trim();

	    
	    if (email.isEmpty() || contrasenia.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    
	    for (Profesor profesor : ConsolaApp.getProfesores()) {
	        if (profesor.getEmail().equalsIgnoreCase(email)) {
	            if (profesor.getContrasenia().equals(contrasenia)) {
	                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. Bienvenido(a), " + profesor.getNombre() + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                
	                MenuProfesorCreador menuCreador = new MenuProfesorCreador();
	                menuCreador.setVisible(true);
	                
	                return; 
	            } else {
	                JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }
	    }

	    
	    JOptionPane.showMessageDialog(this, "No se encuentra un profesor con ese email.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	private void volverMenuPrincipal() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		
		new IniciarSesionProfesorCreador();
	}



}
