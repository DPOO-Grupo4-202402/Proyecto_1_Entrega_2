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

import guiParaEstudiantes.InscribirseEnLp;
import guiParaEstudiantes.RevisarMiProgreso;
import guiParaEstudiantes.VerLpInscritos;
import logica.Estudiante;

public class MenuEstudiante extends JFrame{
	
	private JLabel lblTitulo;
	private JButton butVerLearningPaths;
	private JButton butInscribirLearningPath;
	private JButton butRevisarProgreso;
	private JButton butAgregarResenia;
	private JButton butCerrarSesion;
	private Estudiante estudiante;
	
	public MenuEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
		
		setTitle("Menu del Estudiante " + estudiante.getNombre());
		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Menu del Estudiante", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
		
		butVerLearningPaths = new JButton("Ver Learning Paths Inscritos");
		butInscribirLearningPath = new JButton("Inscribirse en un Learning Path");
		butRevisarProgreso = new JButton("Revisar Progreso");
		butAgregarResenia = new JButton("Agregar Resenia a una Actividad");
		butCerrarSesion = new JButton("Cerrar Sesion");
		
		butVerLearningPaths.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				verLearningPaths();
				
			}
		});
		
		butInscribirLearningPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inscribirLearningPath();
				
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
		panelBotones.add(butInscribirLearningPath);
		panelBotones.add(butRevisarProgreso);
		panelBotones.add(butAgregarResenia);
		panelBotones.add(butCerrarSesion);
		
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void verLearningPaths() {
		
		VerLpInscritos verLP = new VerLpInscritos(estudiante);
		verLP.setVisible(true);
		dispose();
	
	}
	
	private void inscribirLearningPath() {
		
		InscribirseEnLp inscribirLP = new InscribirseEnLp(estudiante);
		inscribirLP.setVisible(true);
		dispose();
	}
	
	private void revisarProgreso() {
		
		RevisarMiProgreso revisarProgreso = new RevisarMiProgreso(estudiante);
		revisarProgreso.setVisible(true);
		dispose();
	}
	
	private void agregarResenia() {
		
		AgregarResenia agregarR = new AgregarResenia(estudiante);
		agregarR.setVisible(true);
		dispose();

	}
	
	private void cerrarSesion() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	
}
