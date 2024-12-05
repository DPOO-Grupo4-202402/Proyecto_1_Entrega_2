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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegistrarProfesor extends JFrame{
	
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblContrasenia;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JPasswordField txtContrasenia;
	private JButton butRegistrar;
	private JButton butVolver;
	
	public RegistrarProfesor() {
		
		setTitle("Registrar Profesor");
		setSize(400, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Registro de Profesor", SwingConstants.CENTER);
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
		
		panelFormulario.add(lblNombre);
		panelFormulario.add(txtNombre);
		panelFormulario.add(lblEmail);
		panelFormulario.add(txtEmail);
		panelFormulario.add(lblContrasenia);
		panelFormulario.add(txtContrasenia);
		
		add(panelFormulario, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel(new FlowLayout());
		butRegistrar = new JButton("Registrar");
		butVolver = new JButton("Volver al Menu Principal");
		
		butRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registrarProfesor();	
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
	
	private void registrarProfesor() {
		JOptionPane.showMessageDialog(this, "Registrar profesor (Pendiente de Implementar)");
	}

	private void volverMenuPrincipal() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		
		new RegistrarProfesor();
	}
	

}
