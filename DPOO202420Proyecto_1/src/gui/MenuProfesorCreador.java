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

public class MenuProfesorCreador extends JFrame{
	
	private JLabel lblTitulo;
	private JButton butCrearLearningPaths;
	private JButton butEditarLearningPaths;
	private JButton butCrearActividad;
	private JButton butVerResenias;
	private JButton butCerrarSesion;
	
	public MenuProfesorCreador() {

		
		setTitle("Menu de Profesor Creador");
		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		lblTitulo = new JLabel("Menu del Profesor", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
		
		butCrearLearningPaths = new JButton("Crear un Nuevo Learning Path");
		butEditarLearningPaths = new JButton("Editar un Learning Path");
		butCrearActividad = new JButton("Crear una Nueva Actividad");
		butVerResenias = new JButton("Ver Resenias de una Actividad");
		butCerrarSesion = new JButton("Cerrar Sesion");
		
		butCrearLearningPaths.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				crearLearningPaths();
				
			}
		});
		
		butEditarLearningPaths.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editarLearningPaths();
				
			}
		});
		
		butCrearActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				crearActividad();
				
			}
		});
		
		butVerResenias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				verResenias();
				
			}
		});
		
		butCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
				
			}
		});
		
		panelBotones.add(butCrearLearningPaths);
		panelBotones.add(butEditarLearningPaths);
		panelBotones.add(butCrearActividad);
		panelBotones.add(butVerResenias);
		panelBotones.add(butCerrarSesion);
		
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void crearLearningPaths() {
		JOptionPane.showMessageDialog(this, "Crear un Nuevo Learning Path (Pendiente de Implementar)");
	
	}
	
	private void editarLearningPaths() {
		JOptionPane.showMessageDialog(this, "Editar un Learning Path (Pendiente de Implementar)");
	}
	
	private void crearActividad() {
		JOptionPane.showMessageDialog(this, "Crear una nueva Actividad (Pendiente de Implementar)");
	}
	
	private void verResenias() {
		JOptionPane.showMessageDialog(this, "Ver Resenias de una Actividad (Pendiente de Implementar)");

	}
	
	private void cerrarSesion() {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		dispose();
	}
	
	public static void main(String[] args) {
		
		new MenuProfesorCreador();
	}
	

}
