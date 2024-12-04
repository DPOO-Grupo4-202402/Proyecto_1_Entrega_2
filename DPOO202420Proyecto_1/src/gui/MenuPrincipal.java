package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class MenuPrincipal  extends JFrame{
	
	private JLabel lblTitulo;
	private JButton butRegistrarEstudiante;
	private JButton butRegistrarProfesor;
	private JButton butIniciarSesionEstudiante;
	private JButton butIniciarSesionProfesorCalificador;
	private JButton butiniciarSesionProfesorCreador;
	
	public MenuPrincipal() {
		
		setTitle("Menu Principal");
		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(5, 1, 10, 10));
		
		butRegistrarEstudiante = new JButton("Registrar Como Estudiante");
		butRegistrarProfesor = new JButton("Registrar Como Profesor");
		butIniciarSesionEstudiante = new JButton("Iniciar Sesion Como Estudiante");
		butIniciarSesionProfesorCalificador = new JButton("Iniciar Sesion Como Profesor Calificador");
		butiniciarSesionProfesorCreador = new JButton("Iniciar Sesion Como Profesor Creador");
		
		panelBotones.add(butRegistrarEstudiante);
		panelBotones.add(butRegistrarProfesor);
		panelBotones.add(butIniciarSesionEstudiante);
		panelBotones.add(butIniciarSesionProfesorCalificador);
		panelBotones.add(butiniciarSesionProfesorCreador);
		
		add(lblTitulo, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MenuPrincipal();
	}

}
