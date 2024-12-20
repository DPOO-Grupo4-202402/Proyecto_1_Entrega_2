package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import presentacion.ConsolaApp;

public class MenuPrincipal  extends JFrame{
	
	private JLabel lblTitulo;
	private JButton butRegistrarEstudiante;
	private JButton butRegistrarProfesor;
	private JButton butIniciarSesionEstudiante;
	private JButton butIniciarSesionProfesorCalificador;
	private JButton butiniciarSesionProfesorCreador;
	private JButton butCargarDatos;
	
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
		panelBotones.setLayout(new GridLayout(6, 1, 10, 10));
		
		butRegistrarEstudiante = new JButton("Registrar Como Estudiante");
		butRegistrarProfesor = new JButton("Registrar Como Profesor");
		butIniciarSesionEstudiante = new JButton("Iniciar Sesion Como Estudiante");
		butIniciarSesionProfesorCalificador = new JButton("Iniciar Sesion Como Profesor Calificador");
		butiniciarSesionProfesorCreador = new JButton("Iniciar Sesion Como Profesor Creador");
		butCargarDatos = new JButton("Cargar datos al sistema");
		
		butRegistrarEstudiante.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirRegistrarEstudiante();
			}
		});
		
		butRegistrarProfesor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirRegistrarProfesor();
				
			}
		});
		
		butIniciarSesionEstudiante.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirIniciarSesionEstudiante();
				
			}
		});
		
		butIniciarSesionProfesorCalificador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirIniciarSesionProfesorCalificador();
				
			}
		});
		
		butiniciarSesionProfesorCreador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirIniciarSesionProfesorCreador();
				
			}
		});
		
		butCargarDatos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsolaApp.cargarDatos();
				JOptionPane.showMessageDialog(MenuPrincipal.this, "Carga de datos exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		panelBotones.add(butRegistrarEstudiante);
		panelBotones.add(butRegistrarProfesor);
		panelBotones.add(butIniciarSesionEstudiante);
		panelBotones.add(butIniciarSesionProfesorCalificador);
		panelBotones.add(butiniciarSesionProfesorCreador);
		panelBotones.add(butCargarDatos);
		
		add(lblTitulo, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public void abrirRegistrarEstudiante() {
		new RegistrarEstudiante().setVisible(true);
		dispose();
	}
	
	public void abrirRegistrarProfesor() {
		new RegistrarProfesor().setVisible(true);
		dispose();
	}
	
	public void abrirIniciarSesionEstudiante() {
		new IniciarSesionEstudiante().setVisible(true);
		dispose();
	}
	
	public void abrirIniciarSesionProfesorCalificador(){
		new IniciarSesionProfesorCalificador().setVisible(true);
		dispose();
	}
	
	public void abrirIniciarSesionProfesorCreador() {
		new IniciarSesionProfesorCreador().setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		new MenuPrincipal();
	}

}
