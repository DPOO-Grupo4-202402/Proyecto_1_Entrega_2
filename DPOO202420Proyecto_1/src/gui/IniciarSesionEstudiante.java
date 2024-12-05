package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesionEstudiante extends JFrame{
	
	private JLabel lblTitulo;
	private JLabel lblEmail;
	private JLabel lblContrasenia;
	private JTextField txtEmail;
	private JPasswordField txtContrasenia;
	private JButton butIngresar;
	private JButton butVolver;
	
	public IniciarSesionEstudiante() {
		
		setTitle("Iniciar Sesion como Estudiante");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Inicio de Sesion para Estudiantes", SwingConstants.CENTER);
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
		JOptionPane.showMessageDialog(this, "Iniciar Sesion (Pendiente de Implementar)");
	}
	
	private void volverMenuPrincipal() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		
		new IniciarSesionEstudiante();
	}

}
