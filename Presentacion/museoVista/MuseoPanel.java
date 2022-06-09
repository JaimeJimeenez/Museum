package Presentacion.museoVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.museoVista.empleado.EmpleadoGUI;
import Presentacion.museoVista.entrada.EntradaGui;
import Presentacion.museoVista.facturaMuseo.FacturaMuseoGUI;
import Presentacion.museoVista.turnoEmpleado.TurnoEmpleadoGui;
import Presentacion.tiendaVista.cliente.ClienteGui;
import Presentacion.tiendaVista.fabricante.FabricanteGUI;
import Presentacion.tiendaVista.producto.ProductoGui;
import Presentacion.utility.ErrorPanel;
import Presentacion.utility.FormPanel;
import Presentacion.utility.MostrarPanel;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;
import Presentacion.utility.RoundButton;
import Presentacion.utility.TablePanel;
import Presentacion.vistaPrincipal.GUI;
import Presentacion.vistaPrincipal.PrincipalGUI;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TCorriente;
import Negocio.Cliente.TSocio;

public class MuseoPanel extends JPanel implements GUI {

	private static final long serialVersionUID = 1L;
	
	private String nombre = "MUSEO";
	
	private GUI currentGui;
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JLabel menuTitle;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;

	public MuseoPanel() {
		init();
	}
	
	public int getINICIO_PANEL_HEIGHT() {
		return INICIO_PANEL_HEIGHT;
	}

	public void setINICIO_PANEL_HEIGHT(int INICIO_PANEL_HEIGHT) {
		this.INICIO_PANEL_HEIGHT = INICIO_PANEL_HEIGHT;
	}

	public JPanel getInicioPanel() { return inicioPanel; }

	public void setInicioPanel(JPanel inicioPanel) {
		this.inicioPanel = inicioPanel;
	}

	public JPanel getPanelActual() { return panelActual; }
	
	public void setPanelActual(JPanel panelActual) { this.panelActual = panelActual; }

	public ErrorPanel getErrorPanel() { return errorPanel; }

	private void init() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setOpaque(false);
		inicioPanel.setMaximumSize(new Dimension(1024, INICIO_PANEL_HEIGHT));
		
		GridBagConstraints c = new PanelGridBagConstraint();
		
		//EMPLEADO
		JButton empleadoBtn = new PanelButton("resources/icons/inicio/museo/empleadoMuseo.png");
		empleadoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mostrar Empleado");
				updateFrame(EmpleadoGUI.getInstancia());
			}
		});
		inicioPanel.add(empleadoBtn, c);
				
		//TURNO EMPLEADO 
		c.gridx++;
		JButton turnoEmpleadoBtn = new PanelButton("resources/icons/inicio/museo/TurnoEmpleado.png");
		turnoEmpleadoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mostrar Turno Empleado");
				updateFrame(TurnoEmpleadoGui.getInstance());
			}
		});
		inicioPanel.add(turnoEmpleadoBtn, c);
		
		//Entrada
		c.gridx = 0;
		c.gridy++;
		JButton entradaBtn = new PanelButton("resources/icons/inicio/museo/entradas.png");
		entradaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mostrar Entrada");
				updateFrame(EntradaGui.getInstance());
			}
		});
		inicioPanel.add(entradaBtn, c);
		
		//Factura
		c.gridx++;
		JButton facturaBtn = new PanelButton("resources/icons/inicio/museo/factura.png");
		facturaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mostrar Factura Gui");
				updateFrame(FacturaMuseoGUI.getInstance());
			}
		});
		inicioPanel.add(facturaBtn, c);
		
		c.gridx++;
		c.gridx++;
		c.gridx++;
		
		//--
		
		errorPanel = new ErrorPanel(460 - INICIO_PANEL_HEIGHT);

		RoundButton backBtn = new RoundButton(100);
		backBtn.setMaximumSize(new Dimension(70,70));
		backBtn.setBackground(new Color(51,83,148));
		backBtn.setBorder(null);
		backBtn.setFocusPainted(false);
		backBtn.setIcon("resources/icons/back.png");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Volver");
				
				errorPanel.clear();
				if (panelActual.equals(inicioPanel))
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_INICIO, null));
				else {
					remove(panelActual);
					inicioPanel.setVisible(true);
					panelActual = inicioPanel;
				}
			}
		});
		errorPanel.add(backBtn);
		
		inicioPanel.setBorder(BorderFactory.createEmptyBorder((460-INICIO_PANEL_HEIGHT)/2,0,0,0));
		add(inicioPanel, BorderLayout.CENTER);
		add(errorPanel, BorderLayout.PAGE_END);
		panelActual = inicioPanel;
	}

	private void updateFrame(GUI newGui) {
		if (currentGui == null || currentGui.getNombre().equals(this.nombre)) {
			inicioPanel.setVisible(false);
			remove(errorPanel);
			add(newGui.getPanel());
		} 
		else {
			remove(currentGui.getPanel());
			inicioPanel.setVisible(true);
		}
		currentGui = newGui;
		//menuTitle.setText(newGui.getNombre());
		repaint();
		revalidate();
	}
	

	@Override
	public String getNombre() { return nombre; }
	
	@Override
	public JPanel getPanel() { return this; }

	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
			case MOSTRAR_MUSEO:
				updateFrame(MuseoGui.getInstancia());
				System.out.println("Retornando a Vista museo");
				break;
			default:
				errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
				break;
		}
	}
}
