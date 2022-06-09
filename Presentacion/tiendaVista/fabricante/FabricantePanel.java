package Presentacion.tiendaVista.fabricante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.utility.ErrorPanel;
import Presentacion.utility.FormPanel;
import Presentacion.utility.MostrarPanel;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;
import Presentacion.utility.RoundButton;
import Presentacion.utility.TablePanel;
import Presentacion.vistaPrincipal.GUI;
import Negocio.Fabricante.TFabricante;

public class FabricantePanel extends JPanel implements GUI {
	
	private String nombre = "FABRICANTE";
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;

	public FabricantePanel() {
		init();
	}
	
	public int getINICIO_PANEL_HEIGHT() {
		return INICIO_PANEL_HEIGHT;
	}

	public void setINICIO_PANEL_HEIGHT(int INICIO_PANEL_HEIGHT) {
		this.INICIO_PANEL_HEIGHT = INICIO_PANEL_HEIGHT;
	}

	public JPanel getInicioPanel() { 
		return inicioPanel; 
	}

	public void setInicioPanel(JPanel inicioPanel) {
		this.inicioPanel = inicioPanel;
	}

	public JPanel getPanelActual() { 
		return panelActual; 
	}
	
	public void setPanelActual(JPanel panelActual) { 
		this.panelActual = panelActual; 
	}

	public ErrorPanel getErrorPanel() { 
		return errorPanel; 
	}

	private void init() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setOpaque(false);
		inicioPanel.setMaximumSize(new Dimension(1024, INICIO_PANEL_HEIGHT));
		
		GridBagConstraints c = new PanelGridBagConstraint();
		
		// REGISTRAR
		JButton registrarBtn = new PanelButton("resources/icons/operaciones/registrar.png");
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPanel();
				System.out.println("Registrar");
			}
		});
		inicioPanel.add(registrarBtn, c);
		
		// MODIFICAR
		c.gridx++;
		JButton modificarBtn = new PanelButton("resources/icons/operaciones/modificar.png");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPanel();
				System.out.println("Modificar");
			}
		});
		inicioPanel.add(modificarBtn, c);
		
		// BORRAR
		c.gridx++;
		JButton borrarBtn = new PanelButton("resources/icons/operaciones/borrar.png");
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPanel();
				System.out.println("Borrar");
			}
		});
		inicioPanel.add(borrarBtn, c);
		
		// BUSCAR
		c.gridy++;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscar.png");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPanel();
				System.out.println("Buscar");
			}
		});
		inicioPanel.add(buscarBtn, c);
		
		// MOSTRAR LISTA
		c.gridx = 0;
		c.gridx++;
		JButton mostrarListaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		mostrarListaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_FABRICANTES, null));
				System.out.println("Mostrar Lista");
			}
		});
		inicioPanel.add(mostrarListaBtn, c);
		
		
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_TIENDA, null));
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
	
	private void updatePanel(JPanel newPanel, Integer offset) {
		if (panelActual.equals(inicioPanel)) {
			inicioPanel.setVisible(false);
		}
		else {
			remove(panelActual);
		}
		newPanel.setBorder(BorderFactory.createEmptyBorder(offset == null ? (460-INICIO_PANEL_HEIGHT)/2 : offset,0,0,0));
		add(newPanel, BorderLayout.CENTER);
		errorPanel.clear();
		revalidate();
		repaint();
		panelActual = newPanel;
	}
	
	private void registrarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("NOMBRE", "DIRECCION"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = panel.getFieldText("NOMBRE");	
					String direccion = panel.getFieldText("DIRECCION");
					ApplicationController.getInstance().handleRequest(new Context(Eventos.REGISTRAR_FABRICANTE, new TFabricante(nombre, direccion)));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				} catch (IllegalArgumentException ex) {
					errorPanel.showOutputMessage(ex.getMessage(), false);
				}
			}
		});
		updatePanel(panel, null);
	}
	
	private void modificarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID", "NOMBRE", "DIRECCION"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					String nombre = panel.getFieldText("NOMBRE");	
					String direccion = panel.getFieldText("DIRECCION");
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_FABRICANTE, new TFabricante(id, nombre, direccion)));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				} catch (IllegalArgumentException ex) {
					errorPanel.showOutputMessage(ex.getMessage(), false);
				}
			}
		});
		updatePanel(panel, null);
	}
	
	private void borrarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_FABRICANTE, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		updatePanel(panel, null);
	}
	
	private void buscarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_FABRICANTE_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TFabricante fabricante) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID", "NOMBRE", "DIRECCION"));
		panel.setLabelText("ID", Integer.toString(fabricante.getId()));
		panel.setLabelText("NOMBRE", fabricante.getNombre());
		panel.setLabelText("DIRECCION", fabricante.getDireccion());
		updatePanel(panel, 50);
	}	
	
	private void mostrarListaPanel(List<TFabricante> fabricantes) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "NOMBRE", "DIRECCION"));		
		for (TFabricante fabricante : fabricantes)
			tablePanel.addRow(new Object[]{Integer.toString(fabricante.getId()), fabricante.getNombre(), fabricante.getDireccion()}, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}	

	public JPanel getPanel() {
		return this;
	}

	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()){
		case REGISTRAR_FABRICANTE_OK:
		case MODIFICAR_FABRICANTE_OK:
		case BORRAR_FABRICANTE_OK:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
			break;
		case BUSCAR_FABRICANTE_POR_ID_OK:
			mostrarPanel((TFabricante) responseContext.getDataObject());
			break;
		case MOSTRAR_LISTA_FABRICANTES_OK:
			mostrarListaPanel((List<TFabricante>) responseContext.getDataObject());
			break;
		case REGISTRAR_FABRICANTE_KO:
		case MODIFICAR_FABRICANTE_KO:
		case BORRAR_FABRICANTE_KO:
		case BUSCAR_FABRICANTE_POR_ID_KO:
		case MOSTRAR_LISTA_FABRICANTES_KO:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
			break;
		default:
			errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
			break;
		}
	}

	public String getNombre() {
		return nombre;
	}
}