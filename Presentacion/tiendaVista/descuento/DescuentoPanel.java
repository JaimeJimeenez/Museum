package Presentacion.tiendaVista.descuento;

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
import Negocio.Descuento.TDescuento;
import Negocio.Fabricante.TFabricante;


public class DescuentoPanel extends JPanel implements GUI {
	
	private static final long serialVersionUID = 1L;

	private String nombre = "DESCUENTO";
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;

	public DescuentoPanel() {
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
		c.gridx = 0;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscar.png");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPanel();
				System.out.println("Buscar");
			}
		});
		inicioPanel.add(buscarBtn, c);
		// MOSTRAR LISTA
		c.gridx++;
		JButton mostrarListaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		mostrarListaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_DESCUENTO, null));
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
	protected void buscarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_DESCUENTO_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		updatePanel(panel, null);
		
	}

	protected void borrarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_DESCUENTO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		updatePanel(panel, null);
	}

	protected void modificarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID", "PORCENTAJE"));
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					int porcentaje = Integer.parseInt(panel.getFieldText("PORCENTAJE"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_DESCUENTO, new TDescuento(id,porcentaje)));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				} catch (IllegalArgumentException ex) {
					errorPanel.showOutputMessage(ex.getMessage(), false);
				}
			}
		
		});
		updatePanel(panel, null);
	}

	protected void registrarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("PORCENTAJE"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int porcentaje = Integer.parseInt((panel.getFieldText("PORCENTAJE")));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.REGISTRAR_DESCUENTO, new TDescuento(porcentaje)));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				} catch (IllegalArgumentException ex) {
					errorPanel.showOutputMessage(ex.getMessage(), false);
				}
			}
		});
		
		updatePanel(panel, null);
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

	private void mostrarPanel(TDescuento descuento) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID", "PORCENTAJE"));
		panel.setLabelText("ID", Integer.toString(descuento.getId()));
		panel.setLabelText("PORCENTAJE", Integer.toString(descuento.getPorcentaje()));
		updatePanel(panel, 50);
		
	}
	private void mostrarListaPanel(List<TDescuento> descuentos) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "PORCENTAJE"));		
		for (TDescuento descuento : descuentos)
			tablePanel.addRow(new Object[]{Integer.toString(descuento.getId()), Integer.toString(descuento.getPorcentaje())}, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}	
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()){
		case REGISTRAR_DESCUENTO_OK:
		case MODIFICAR_DESCUENTO_OK:
		case BORRAR_DESCUENTO_OK:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
			break;
		case BUSCAR_DESCUENTO_POR_ID_OK:
			mostrarPanel((TDescuento) responseContext.getDataObject());
			break;
		case MOSTRAR_LISTA_DESCUENTO_OK:
			mostrarListaPanel((List<TDescuento>) responseContext.getDataObject());
			break;
		case REGISTRAR_DESCUENTO_KO:
		case MODIFICAR_DESCUENTO_KO:
		case BORRAR_DESCUENTO_KO:
		case BUSCAR_DESCUENTO_POR_ID_KO:
		case MOSTRAR_LISTA_DESCUENTO_KO:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
			break;
		default:
			errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
			break;
		}
	}	

	@Override
	public String getNombre() {
		return nombre;
	}

}
