package Presentacion.vistaPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentacion.Controller.Context;
import Presentacion.museoVista.MuseoGui;
import Presentacion.tiendaVista.TiendaGui;
import Presentacion.tiendaVista.cliente.ClienteGui;
import Presentacion.tiendaVista.descuento.DescuentoGui;
import Presentacion.tiendaVista.fabricante.FabricanteGUI;
import Presentacion.tiendaVista.factura.FacturaGUI;
import Presentacion.tiendaVista.producto.ProductoGui;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;

public class PrincipalFrame extends JFrame implements GUI {
	
	private static final long serialVersionUID = 1L;

	private String nombre = "INICIO";
	
	private GUI currentGui;
	
	private JPanel inicioPanel;
	private JLabel menuTitle;
	
	public PrincipalFrame(){
		init();
	}
	
	private void init(){
		setBackground(new Color(255, 255, 255));
		setUndecorated(true);
		setBounds(100, 100, 1024, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel topBar = new JPanel();
		topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
		topBar.setBackground(new Color(7, 55, 99));
		topBar.setMaximumSize(new Dimension(1024, 50));
		
		topBar.add(Box.createRigidArea(new Dimension(20,0)));
		
		//JLabel groupLogo = new JLabel();
		//groupLogo.setIcon(new ImageIcon("resources/icons/iconosPrueba/museo.png")); //TODO
		//topBar.add(groupLogo);
		
		topBar.add(Box.createRigidArea(new Dimension(8,0)));
		
		JLabel appName = new JLabel("MUSEO");
		appName.setFont(new Font("Arial", Font.BOLD, 14));
		appName.setForeground(new Color(215,215,215));
		topBar.add(appName);
		
		topBar.add(Box.createRigidArea(new Dimension(900-23,0)));
		
		JButton exitBtn = new JButton("X");
		exitBtn.setFocusPainted(false);
		exitBtn.setBorder(null);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setPreferredSize(new Dimension(30, 30));
		exitBtn.setFont(new Font("Corbel", Font.BOLD, 19));
		exitBtn.setForeground(new Color(215,215,215));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JComponent[] input = new JComponent[] { new JLabel("¿Desea cerrar el programa?") };
				Object[] options = {"Si", "No"};
				int result = JOptionPane.showOptionDialog(null, input, "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (result == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		topBar.add(exitBtn);
		
		
		menuTitle = new JLabel(nombre);
		menuTitle.setFont(new Font("Arial", Font.BOLD, 30));
		menuTitle.setForeground(new Color(7, 55, 99));
		menuTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel decorationLine = new JPanel();
		decorationLine.setBackground(new Color(7, 55, 99));
		decorationLine.setMaximumSize(new Dimension(800, 5));
		

		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setBackground(new Color(243, 243, 243));
		inicioPanel.setMaximumSize(new Dimension(1024, 460));
		
		// TIENDA
		GridBagConstraints c = new PanelGridBagConstraint();
		JButton tiendaBtn = new PanelButton("resources/icons/inicio/tiendaMuseo.png");
		tiendaBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Mostrar TiendaGUI");
				updateFrame(TiendaGui.getInstancia());
			}
		});
		inicioPanel.add(tiendaBtn, c);
		
		// MUSEO
		c.gridx++;
		JButton museoBtn = new PanelButton("resources/icons/inicio/museo.png");
		museoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Mostrar MuseoGUI");
				updateFrame(MuseoGui.getInstancia());
			}
		});
		inicioPanel.add(museoBtn, c);	
			
		
		add(topBar);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(menuTitle);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(decorationLine);
		add(inicioPanel);
	
		setLocationRelativeTo(null);	
		setVisible(true);
		
	}
	
	private void updateFrame(GUI newGui) {
		if (currentGui == null || currentGui.getNombre().equals(this.nombre)) {
			inicioPanel.setVisible(false);
			add(newGui.getPanel());
		} 
		else {
			remove(currentGui.getPanel());
			inicioPanel.setVisible(true);
		}
		
		currentGui = newGui;
		menuTitle.setText(newGui.getNombre());
		repaint();
		revalidate();   
	}

	public JPanel getPanel() {
		return inicioPanel;
	}

	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
		case MOSTRAR_INICIO:
			updateFrame(PrincipalGUI.getInstancia());
			System.out.println("Retornando a Vista principal");
			break;
		default:
			System.out.println("Evento desconocido: " + responseContext.getEvent().toString());
			break;
		}
	}

	public String getNombre() {
		return nombre;
	}
	
}
