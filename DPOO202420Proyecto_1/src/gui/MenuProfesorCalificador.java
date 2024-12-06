package gui;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;

import logica.Profesor;

public class MenuProfesorCalificador extends JFrame{
	
	private JLabel lblTitulo;
	private JButton butVerLearningPaths;
	private JButton butEvaluarActividades;
	private JButton butRevisarProgreso;
	private JButton butAgregarResenia;
	private JButton butCerrarSesion;
	private Profesor profesor;
	
	public MenuProfesorCalificador(Profesor profesor) {
		this.profesor = profesor;

		
		setTitle("Menu de Profesor Calificador " + profesor.getNombre());
		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Menu del Profesor", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
		
		butVerLearningPaths = new JButton("Ver Estudiantes Inscritos en un Learning Path");
		butEvaluarActividades = new JButton("Evaluar Actividades de un Estudiante");
		butRevisarProgreso = new JButton("Revisar Progreso de un Estudiante");
		butAgregarResenia = new JButton("Agregar Resenia a una Actividad");
		butCerrarSesion = new JButton("Cerrar Sesion");
		
		butVerLearningPaths.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				verLearningPaths();
				
			}
		});
		
		butEvaluarActividades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				evaluarActividades();
				
			}
		});
		
		butRevisarProgreso.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				revisarProgreso();
				
			}
		});
		
		butAgregarResenia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarResenia();
				
			}
		});
		
		butCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
				
			}
		});
		
		panelBotones.add(butVerLearningPaths);
		panelBotones.add(butEvaluarActividades);
		panelBotones.add(butRevisarProgreso);
		panelBotones.add(butAgregarResenia);
		panelBotones.add(butCerrarSesion);
		
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void verLearningPaths() {
		
		VerEstEnLearningPath verLP = new VerEstEnLearningPath(profesor);
		verLP.setVisible(true);
		dispose();
	
	}
	
	private void evaluarActividades() {
		
		EvaluarActividades evaluarAct = new EvaluarActividades(profesor);
		evaluarAct.setVisible(true);
		dispose();
	}
	
	private void revisarProgreso() {
		
		RevisarProgresoEst revisarProgreso = new RevisarProgresoEst(profesor);
		revisarProgreso.setVisible(true);
		dispose();
	}
	
	private void agregarResenia() {
		
		AgregarResenia agregarR = new AgregarResenia(profesor);
		agregarR.setVisible(true);
		dispose();


	}
	
	private void cerrarSesion() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
}
