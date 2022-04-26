package Presentacion.museoVista.facturaMuseo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Context;
import Presentacion.Controller.Eventos;
import Presentacion.tiendaVista.factura.Pair;
import Presentacion.utility.ErrorPanel;
import Presentacion.utility.FacturaTable;
import Presentacion.utility.FormPanel;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;
import Presentacion.utility.RoundButton;
import Presentacion.utility.TablePanel;
import Presentacion.vistaPrincipal.GUI;
import negocio.facturaMuseo.TCarritoMuseo;
import negocio.facturaMuseo.TFacturaCompleta;
import negocio.facturaMuseo.TFacturaMuseo;
import negocio.facturaMuseo.TLineaFacturaMuseo;

@SuppressWarnings("serial")
public class FacturaMuseoPanel extends JPanel implements GUI {
	
	private String nombre = "FACTURA MUSEO";
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;
	
	private TCarritoMuseo carritoAux;
	private TablePanel carritoTable;
	private JLabel carritoPrecio;

	public FacturaMuseoPanel(){
		init();
	}
	private void init() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setOpaque(false);
		inicioPanel.setMaximumSize(new Dimension(1024, INICIO_PANEL_HEIGHT));
		
		GridBagConstraints c = new PanelGridBagConstraint();
		
		//Abrir
		JButton abrirFacturaBtn = new PanelButton("resources/icons/operaciones/abrir-factura.png");
		abrirFacturaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.ABRIR_FACTURA_MUSEO, null));
				carritoPanel();
				System.out.println("Abrir Factura");
			}
		});
		inicioPanel.add(abrirFacturaBtn, c);
		
		//Devolver entrada
		c.gridx++;
		JButton devolverEntradaBtn = new PanelButton("resources/icons/operaciones/devolverEntrada.png");
		devolverEntradaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devolverEntradaPanel();
				System.out.println("Devolver Entrada");
			}

		});
		inicioPanel.add(devolverEntradaBtn, c);
		
		//Buscar Factura
		c.gridx++;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscar.png");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPanel();
				System.out.println("Buscar Factura");
			}
		});
		inicioPanel.add(buscarBtn, c);
		
		//Listar
		c.gridx=0;
		c.gridy++;
		JButton listarBtn = new PanelButton("resources/icons/operaciones/listar.png");
		listarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_FACTURAS_MUSEO, null));
				System.out.println("Listar Facturas Museo");
			}
		});
		inicioPanel.add(listarBtn, c);
		
		//Leer por IdEmpleado
		c.gridx++;
		JButton leerBtn = new PanelButton("resources/icons/operaciones/listar.png");
		leerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leerPorIDEmpleado();
				System.out.println("Leer por ID de empleado");
			}
		});
		inicioPanel.add(leerBtn, c);
		
		//MostrarFacturaCompleta
		c.gridx++;
		JButton CompletaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		CompletaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_FACTURA_COMPLETA, null));
				System.out.println("MostrarFacturaCompleta");
			}

		});
		inicioPanel.add(CompletaBtn, c);
		
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_MUSEO, null));
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

	private void carritoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel facturaPanel = new JPanel();
		facturaPanel.setLayout(new BoxLayout(facturaPanel, BoxLayout.X_AXIS));
		facturaPanel.setOpaque(false);
		facturaPanel.setMaximumSize(new Dimension(1024, 320));
		
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
		toolPanel.setBackground(new Color(70, 70, 70));
		toolPanel.setMaximumSize(new Dimension(80, 320));
		
		TablePanel entradaTable = new TablePanel(new Dimension(600, 320), Arrays.asList("ID ENTRADA", "CANTIDAD"));
		
		JLabel totalTitle = new JLabel("TOTAL: ");
		totalTitle.setForeground(new Color(230,230,230));
		totalTitle.setFont(new Font("Arial", Font.BOLD, 12));
		totalTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		totalTitle.setForeground(new Color(70, 70, 70));

		JLabel totalPrecio = new JLabel("0.0");
		totalPrecio.setForeground(new Color(230,230,230));
		totalPrecio.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		totalPrecio.setForeground(new Color(70, 70, 70));
		
		toolPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JButton añadirEntradaBtn = createToolButton("resources/icons/operaciones/añadir-producto.png", new Color(63, 164, 31), "Añade un entrada a la factura");
		añadirEntradaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pair<Integer, Integer> entrada = entradaOptionPane("Añadir");
					if (entrada != null) {
						carritoAux.setIdEntrada(entrada.getFirst());
						carritoAux.setCantidad(entrada.getSecond());
						ApplicationController.getInstance().handleRequest(new Context(Eventos.AÑADIR_ENTRADA, carritoAux));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				}
			}
		});
		toolPanel.add(añadirEntradaBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		//Quitar
		JButton quitarEntradaBtn = createToolButton("resources/icons/operaciones/quitar-producto.png", new Color(220, 34, 34), "Quita un entrada de la factura");
		quitarEntradaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pair<Integer, Integer> entrada = entradaOptionPane("Quitar");
					if (entrada != null) {
						carritoAux.setIdEntrada(entrada.getFirst());
						carritoAux.setCantidad(entrada.getSecond());
						ApplicationController.getInstance().handleRequest(new Context(Eventos.QUITAR_ENTRADA, carritoAux));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no vÃ¡lidos o inexistentes.", false);
				}
			}
		});
		toolPanel.add(quitarEntradaBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Cerrar
		JButton cerrarBtn = createToolButton("resources/icons/operaciones/cerrar-factura.png", new Color(38, 180, 237), "Confirma y cierra la factura");
		cerrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final JComponent[] input = new JComponent[] {
				        new JLabel("¿Desea confirmar y cerrar la factura?"),
				};
				Object[] options = {"Si", "No"};
				int result = JOptionPane.showOptionDialog(null, input, "Confirmar Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					try {
						JTextField idEmpleadoField = new JTextField();
						final JComponent[] input2 = new JComponent[] {
								
								new JLabel("Añadir empleado"), idEmpleadoField
						};
						Object[] options2 = {"Si", "No" };
						int result2 = JOptionPane.showOptionDialog(null, input2, "Añadir Empleado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
						if (result2 == JOptionPane.OK_OPTION) {
							int idEmpleado = Integer.parseInt(idEmpleadoField.getText());
							carritoAux.getFacturaMuseo().setIdEmpleado(idEmpleado);
							ApplicationController.getInstance().handleRequest(new Context(Eventos.CERRAR_FACTURA_MUSEO, carritoAux));
						}
					} catch (NumberFormatException ex) {
						errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
					}
				}
			}
		});
		toolPanel.add(cerrarBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 80)));
		toolPanel.add(totalTitle);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		toolPanel.add(totalPrecio);
		
		facturaPanel.add(Box.createRigidArea(new Dimension(170, 0)));
		facturaPanel.add(entradaTable);
		facturaPanel.add(toolPanel);

		panel.add(facturaPanel);
		
		carritoTable = entradaTable;
		carritoPrecio = totalPrecio;
		
		updatePanel(panel, 40);
		
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
	
	private JButton createToolButton(String iconPath, Color color, String tooltip) {
		RoundButton button = new RoundButton(100);
		button.setMaximumSize(new Dimension(35, 35));
		button.setBackground(color);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		button.setToolTipText(tooltip);
		button.setIcon(iconPath);
		
		return button;
	}
	
	private Pair<Integer, Integer> entradaOptionPane(String operacion) {
		JTextField idEntradaField = new JTextField();
		JTextField cantidadField = new JTextField();
		final JComponent[] input = new JComponent[] {
		        new JLabel("ID Entrada"),
		        idEntradaField,
		        new JLabel("Cantidad"),
		        cantidadField,
		};
		Object[] options = {operacion, "Cancelar"};
		int result = JOptionPane.showOptionDialog(null, input, operacion + " Entrada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if (result == JOptionPane.OK_OPTION) {
			int idEntrada = Integer.parseInt(idEntradaField.getText());
			int cantidad = Integer.parseInt(cantidadField.getText());
			return new Pair<Integer, Integer>(idEntrada, cantidad);
		}
		
		return null;
	}

	private void devolverEntradaPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID FACTURA", "ID ENTRADA", "CANTIDAD"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer idFactura = Integer.valueOf(panel.getFieldText("ID FACTURA"));
					Integer idEntrada = Integer.valueOf(panel.getFieldText("ID ENTRADA"));
					Integer cantidad = Integer.valueOf(panel.getFieldText("CANTIDAD"));
					ApplicationController.getInstance().handleRequest(new Context(Eventos.DEVOLVER_ENTRADA, new TLineaFacturaMuseo(idFactura, idEntrada, cantidad)));
					
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_FACTURA_MUSEO_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void leerPorIDEmpleado() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID EMPLEADO"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID EMPLEADO"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LEER_POR_ID_EMPLEADO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TCarritoMuseo carrito) {
		FacturaTable fTable = new FacturaTable(new Dimension(600, 175), Arrays.asList("ENTRADA"));
		fTable.MostrarFacturaPorCarritoMuseo(carrito);
		updatePanel(fTable, null);
	}
	
	private void listarPanel(LinkedList<TCarritoMuseo> carritos) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel listarPanel = new JPanel();
		listarPanel.setLayout(new BoxLayout(listarPanel, BoxLayout.Y_AXIS));
		listarPanel.setOpaque(false);
		listarPanel.setMaximumSize(new Dimension(1024, 400));
		
		for (TCarritoMuseo c : carritos) {
			FacturaTable fTable = new FacturaTable(new Dimension(600, 150), Arrays.asList("ID ENTRADA"));
			fTable.MostrarFacturaPorCarritoMuseo(c);
			listarPanel.add(fTable);
			listarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		}
		
		JScrollPane scrollPane = new JScrollPane(listarPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension(700, 300));
		scrollPane.setMaximumSize(new Dimension(700, 300));
		scrollPane.setOpaque(false);
		panel.add(scrollPane);
		
		updatePanel(panel, 50);
	}
	
	private void listarEmpleadoPanel(LinkedList<TFacturaMuseo> factura) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID_FACTURA", "ID EMPLEADO", "PRECIO_TOTAL", "FECHA"));
		for (TFacturaMuseo c : factura)
			tablePanel.addRow(new Object[] { c.getId(), c.getIdEmpleado(), c.getPrecioTotal(), c.getFecha() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void listarFacturaCompletaPanel(LinkedList<TFacturaCompleta> factura) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID_FACTURA", "ID EMPLEADO","ID ENTRADA"));
		for (TFacturaCompleta c : factura)
			tablePanel.addRow(new Object[] { c.getIdFactura(), c.getIdEmpleado(), c.getIdEntrada() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	
	
	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this;
	}

	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
		
		//SUCCESS
		case ABRIR_FACTURA_MUSEO:
			carritoAux = (TCarritoMuseo) responseContext.getDataObject();
			break;
			
		case AÑADIR_ENTRADA_OK:
		case QUITAR_ENTRADA_OK:
			carritoAux = (TCarritoMuseo) responseContext.getDataObject();
			if (carritoTable != null && carritoPrecio != null) {
				carritoTable.clearTable();
				for (TLineaFacturaMuseo l : carritoAux.getLineasFacturaMuseo()) {
					carritoTable.addRow(new Object[]{l.getIdEntrada(), l.getCantidad()}, false);
				}
				errorPanel.clear();
			}
			break;
			
		case CERRAR_FACTURA_MUSEO_OK:
			//Volver a Inicio
			remove(panelActual);
			carritoAux = null;
			carritoTable = null;
			carritoPrecio = null;
			inicioPanel.setVisible(true);
			panelActual = inicioPanel;
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
			break;
			
		case DEVOLVER_ENTRADA_OK:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
			break;
			
		case BUSCAR_FACTURA_MUSEO_POR_ID_OK:
			mostrarPanel((TCarritoMuseo) responseContext.getDataObject());
			break;
		case MOSTRAR_LISTA_FACTURAS_MUSEO_OK:
			listarPanel((LinkedList<TCarritoMuseo>) responseContext.getDataObject());
			break;
		case LEER_POR_ID_EMPLEADO_OK:
			listarEmpleadoPanel((LinkedList<TFacturaMuseo>) responseContext.getDataObject());
			break;
		case MOSTRAR_FACTURA_COMPLETA_OK:
			listarFacturaCompletaPanel((LinkedList<TFacturaCompleta>) responseContext.getDataObject());
			break;
			
		//ERROR
		case AÑADIR_ENTRADA_KO:
		case QUITAR_ENTRADA_KO:
		case CERRAR_FACTURA_MUSEO_KO:
		case DEVOLVER_ENTRADA_KO:
		case BUSCAR_FACTURA_MUSEO_POR_ID_KO:
		case MOSTRAR_LISTA_FACTURAS_MUSEO_KO:
		case LEER_POR_ID_EMPLEADO_KO:
		case MOSTRAR_FACTURA_COMPLETA_KO:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
			break;
		default:
			errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
			break;
	}	
		
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

}
