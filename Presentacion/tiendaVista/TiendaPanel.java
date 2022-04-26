package Presentacion.tiendaVista;

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
import Presentacion.tiendaVista.cliente.ClienteGui;
import Presentacion.tiendaVista.descuento.DescuentoGui;
import Presentacion.tiendaVista.fabricante.FabricanteGUI;
import Presentacion.tiendaVista.factura.FacturaGUI;
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
import negocio.cliente.TCliente;
import negocio.cliente.TCorriente;
import negocio.cliente.TSocio;

public class TiendaPanel extends JPanel implements GUI {
private static final long serialVersionUID = 1L;
	
	private String nombre = "TIENDA";
	
	private GUI currentGui;
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private JLabel menuTitle;
	private ErrorPanel errorPanel;

	public TiendaPanel() {
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
		
		//Cliente 
		JButton clienteBtn = new PanelButton("resources/icons/inicio/tienda/cliente.png");
		clienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel(ClienteGui.getInstance());
				System.out.println("Cliente");
			}
		});
		inicioPanel.add(clienteBtn, c);
		
		// Descuento
		c.gridx++;
		JButton descuentoBtn = new PanelButton("resources/icons/inicio/tienda/descuento.png");
		descuentoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel(DescuentoGui.getInstancia());
				System.out.println("Modificar");
			}
		});
		inicioPanel.add(descuentoBtn, c);
		
		//Fabricante
		c.gridx++;
		JButton fabricanteBtn = new PanelButton("resources/icons/inicio/tienda/fabricante.png");
		fabricanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel(FabricanteGUI.getInstancia());
				System.out.println("Borrar");
			}
		});
		inicioPanel.add(fabricanteBtn, c);
		
		//Factura
		c.gridx = 0;
		c.gridy++;
		JButton facturaBtn = new PanelButton("resources/icons/inicio/tienda/factura.png");
		facturaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel(FacturaGUI.getInstancia());
				System.out.println("Buscar");
			}
		});
		inicioPanel.add(facturaBtn, c);
		
		//Producto
		c.gridx++;
		JButton productoBtn = new PanelButton("resources/icons/inicio/tienda/producto.png");
		productoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel(ProductoGui.getInstance());
			}
		});
		inicioPanel.add(productoBtn, c);
		
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
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_INICIO, null));
			}
		});
		errorPanel.add(backBtn);
		
		inicioPanel.setBorder(BorderFactory.createEmptyBorder((460-INICIO_PANEL_HEIGHT)/2,0,0,0));
		add(inicioPanel, BorderLayout.CENTER);
		add(errorPanel, BorderLayout.PAGE_END);
		panelActual = inicioPanel;
	}
	
	//TODO
	private void updatePanel(GUI newGui) {
		/*if (panelActual.equals(inicioPanel))
			inicioPanel.setVisible(false);
		else
			remove(panelActual);
		*/
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
		revalidate();
		repaint();
	}
	
	@Override
	public String getNombre() { return nombre; }
	
	@Override
	public JPanel getPanel() { return this; }

	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
		case MOSTRAR_TIENDA:
			updatePanel(TiendaGui.getInstancia());
			break;
		default:
			errorPanel.showOutputMessage("Evento desconocidoº: " + responseContext.getEvent().toString(), false);
			break;
		}
	}
}