package Presentacion.tiendaVista.cliente;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.utility.*;
import Presentacion.vistaPrincipal.GUI;
import negocio.cliente.TCliente;
import negocio.cliente.TCorriente;
import negocio.cliente.TSocio;

@SuppressWarnings("serial")
public class ClientePanel extends JPanel implements GUI {

	private static final long serialVersionUID = 1L;
	
	private String nombre = "CLIENTE";
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;

	public ClientePanel() {
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
		
		//Registrar 
		JButton registrarBtn = new PanelButton("resources/icons/operaciones/registrar.png");
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isCorriente = corrienteOptionPane(); 
				if (isCorriente  == JOptionPane.YES_OPTION)
					registrarPanel(true);
				else
					registrarPanel(false);
				System.out.println("Registrar");
			}
		});
		inicioPanel.add(registrarBtn, c);
		
		//Modificar 
		c.gridx++;
		JButton modificarBtn = new PanelButton("resources/icons/operaciones/modificar.png");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isCorriente = corrienteOptionPane();
				if (isCorriente == JOptionPane.YES_OPTION)
					modificarPanel(true);
				else
					modificarPanel(false);
				System.out.println("Modificar");
			}
		});
		inicioPanel.add(modificarBtn, c);
		
		//Borrar 
		c.gridx++;
		JButton borrarBtn = new PanelButton("resources/icons/operaciones/borrar.png");
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPanel();
				System.out.println("Borrar");
			}
		});
		inicioPanel.add(borrarBtn, c);
		
		//Buscar 
		c.gridx = 0;
		c.gridy++;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscar.png");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPanel();
				System.out.println("Buscar");
			}
		});
		inicioPanel.add(buscarBtn, c);
		
		//Listar Button
		c.gridx++;
		JButton mostrarListaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		mostrarListaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_CLIENTES, null));
			}
		});
		inicioPanel.add(mostrarListaBtn, c);
		
		c.gridx++;
		JButton listarPorTipoBtn = new PanelButton("resources/icons/operaciones/mostrar-por-jornada.png");
		listarPorTipoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int isCorriente = corrienteOptionPane();
				if (isCorriente == JOptionPane.YES_OPTION)
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LISTAR_CLIENTES_POR_TIPO, true));
				else
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LISTAR_CLIENTES_POR_TIPO, false));
			}
		});
		inicioPanel.add(listarPorTipoBtn, c);
		
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
		
		private void mainFormPanel(boolean isCorriente, final Eventos event) {
			final FormPanel panel;
			
			if (isCorriente)
				panel = new FormPanel(Arrays.asList("DNI", "DIRECCION", "GASTOS ENVIO"));
			else
				panel = new FormPanel(Arrays.asList("DNI", "DIRECCION", "NUMERO", "CUOTA"));
					
			panel.addEnterActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String dni = panel.getFieldText("DNI");
						String direccion = panel.getFieldText("DIRECCION");
						if (isCorriente) {
							float gastosEnvio = Float.parseFloat(panel.getFieldText("GASTOS ENVIO"));
							ApplicationController.getInstance().handleRequest(new Context(event, new TCorriente(dni, direccion, gastosEnvio)));
						}
						else {
							int numero = Integer.parseInt(panel.getFieldText("NUMERO"));
							float cuota = Float.parseFloat(panel.getFieldText("CUOTA"));
							ApplicationController.getInstance().handleRequest(new Context(event, new TSocio(dni, direccion, numero, cuota)));
						}
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
		if (panelActual.equals(inicioPanel))
			inicioPanel.setVisible(false);
		else
			remove(panelActual);
		
		newPanel.setBorder(BorderFactory.createEmptyBorder(offset == null ? (460-INICIO_PANEL_HEIGHT)/2 : offset,0,0,0));
		add(newPanel, BorderLayout.CENTER);
		errorPanel.clear();
		revalidate();
		repaint();
		panelActual = newPanel;
	}
	
	private int corrienteOptionPane() {
		final JComponent[] input = new JComponent[] { new JLabel("Seleccione el tipo de cliente") };
		Object[] options = {"Corriente", "Socio" };
		return JOptionPane.showOptionDialog(null, input, "Tipo Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}


	private void registrarPanel(boolean isCorriente) {
		mainFormPanel(isCorriente, Eventos.REGISTRAR_CLIENTE);
	}

	private void modificarPanel(final boolean isCorriente) {
		final FormPanel panel;
		
		if (isCorriente)
			panel = new FormPanel(Arrays.asList("ID", "DIRECCION", "GASTOS_ENVIO"));
		else 
			panel = new FormPanel(Arrays.asList("ID", "DIRECCION", "NUMERO", "CUOTA"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idCliente = Integer.parseInt(panel.getFieldText("ID"));
					String direccionCliente = panel.getFieldText("DIRECCION");
					String dniCliente = "99999999X";
					if (isCorriente) {
						double gastosEnvio = Double.parseDouble(panel.getFieldText("GASTOS_ENVIO"));
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_CLIENTE, new TCorriente(idCliente, dniCliente, direccionCliente, gastosEnvio)));
					}
					else {
						int numero = Integer.parseInt(panel.getFieldText("NUMERO"));
						double cuota = Double.parseDouble(panel.getFieldText("CUOTA"));
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_CLIENTE, new TSocio(idCliente, dniCliente,direccionCliente, numero, cuota)));
					}
				} catch (NumberFormatException exception) {
					errorPanel.showOutputMessage("Tipos de datos no validos o inexistentes", false);
				} catch (IllegalArgumentException ex) {
					errorPanel.showOutputMessage(ex.getLocalizedMessage(), false);
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_CLIENTE, id));
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_CLIENTE_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mostrarListaPanel(List<TCliente> clientes) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "DNI", "DIRECCION", "TIPO", "NUMERO", "CUOTA", "GASTOS ENVIO"));
		for (TCliente tCliente : clientes) {
			Object[] datos = new Object[]{ };
			if (tCliente.getGastosEnvio() != 0) {
				 datos = new Object[] { Integer.toString(tCliente.getId()), tCliente.getDni(), tCliente.getDireccion(), "Corriente", null, null, tCliente.getGastosEnvio() };
			}
			else {
				datos = new Object[] {Integer.toString(tCliente.getId()), tCliente.getDni(), tCliente.getDireccion(), "Socio", tCliente.getNumero(), tCliente.getCuota(), null };
			}
			tablePanel.addRow(datos, false);
		}
		
		panel.add(tablePanel);
		
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TCliente tCliente) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID", "DNI", "DIRECCION", "GASTOS ENVIO", "NUMERO", "CUOTA"));
		
		if (tCliente.getGastosEnvio() != 0) {
			panel.setLabelText("GASTOS ENVIO", Double.toString(tCliente.getGastosEnvio()));
			panel.setLabelText("NUMERO", null);
			panel.setLabelText("CUOTA", null);
		} else {	
			panel.setLabelText("NUMERO", Integer.toString(tCliente.getNumero()));
			panel.setLabelText("CUOTA", Double.toString(tCliente.getCuota()));
			panel.setLabelText("GASTOS ENVIO", null);
		}

		panel.setLabelText("ID", Integer.toString(tCliente.getId()));
		panel.setLabelText("DNI", tCliente.getDni());
		panel.setLabelText("DIRECCION", tCliente.getDireccion());
		updatePanel(panel, 50);
	}

	@Override
	public String getNombre() { return nombre; }
	
	@Override
	public JPanel getPanel() { return this; }

	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
			case REGISTRAR_CLIENTE_OK:
			case MODIFICAR_CLIENTE_OK:
			case BORRAR_CLIENTE_OK:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
				break;
			case BUSCAR_CLIENTE_POR_ID_OK:
				mostrarPanel((TCliente) responseContext.getDataObject());
				break;
			case MOSTRAR_LISTA_CLIENTES_OK:
			case LISTAR_CLIENTES_POR_TIPO_OK:
				mostrarListaPanel((List<TCliente>) responseContext.getDataObject());
				break;
			case REGISTRAR_CLIENTE_KO:
			case MODIFICAR_CLIENTE_KO:
			case BORRAR_CLIENTE_KO:
			case BUSCAR_CLIENTE_POR_ID_KO:
			case MOSTRAR_LISTA_CLIENTES_KO:
			case LISTAR_CLIENTES_POR_TIPO_KO:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
				break;
			default:
				errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
				break;
		}
	}
}