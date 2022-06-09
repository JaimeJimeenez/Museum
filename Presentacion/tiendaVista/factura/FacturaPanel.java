package Presentacion.tiendaVista.factura;

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
import java.util.List;

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
import Presentacion.utility.ErrorPanel;
import Presentacion.utility.FacturaTable;
import Presentacion.utility.FormPanel;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;
import Presentacion.utility.RoundButton;
import Presentacion.utility.TablePanel;
import Presentacion.vistaPrincipal.GUI;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFacturaTienda;
import Negocio.Factura.TFacturaTiendaCompleta;
import Negocio.Factura.TLineaFactura;
import Negocio.Producto.TFotoObra;
import Negocio.Producto.TLibro;
import Negocio.Producto.TProducto;

public class FacturaPanel extends JPanel implements GUI{

	private static final long serialVersionUID = 1L;

	private int INICIO_PANEL_HEIGHT = 300;
	
	private String nombre = "FACTURA TIENDA";
	
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;
	
	private TFacturaTienda facturaTienda;
	private TCarrito carritoAux;
	private TablePanel carritoTable;
	private JLabel carritoPrecio;
	
	public FacturaPanel() {
		init();
	}
	
	public void init() {
		setLayout(new BorderLayout());
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setOpaque(false);
		inicioPanel.setMaximumSize(new Dimension(1024, INICIO_PANEL_HEIGHT));
		
		GridBagConstraints c = new PanelGridBagConstraint();
		
		//Abrir factura
		JButton abrirFacturaBtn = new PanelButton("resources/icons/operaciones/abrir-factura.png");
		abrirFacturaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.ABRIR_FACTURA, null));
				carritoPanel();
				System.out.println("Abrir Factura");
			}
		});
		inicioPanel.add(abrirFacturaBtn, c);
		
		//Devolver producto
		c.gridx++;
		JButton devolverProductoBtn = new PanelButton("resources/icons/operaciones/devolver-producto.png");
		devolverProductoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devolverProductoPanel();
				System.out.println("Devolver Producto");
			}
		});
		inicioPanel.add(devolverProductoBtn, c);
		
		//Buscar Factura
		c.gridx++;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscarFacturaPorId.png");
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
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_FACTURAS, null));
				System.out.println("Listar Facturas Tienda");
			}
		});
		inicioPanel.add(listarBtn, c);
		
		//Leer por IdCliente y IdDescuento
		c.gridx++;
		JButton leerBtn = new PanelButton("resources/icons/operaciones/listar.png");
		leerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idCliente = clienteOptionPane();
				if(idCliente == JOptionPane.YES_OPTION){
					selectIDCliente();
					System.out.println("Leer por ID de cliente");
				}
				else{
					selectIDDescuento();
					System.out.println("Leer por ID de descuento");
				}
			}
		});
		inicioPanel.add(leerBtn, c);
		
		//MostrarFacturaConMayorPorcentajeDescuento
		c.gridx++;
		JButton mayorPorcentajeBtn = new PanelButton("resources/icons/operaciones/buscar.png"); 
		mayorPorcentajeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO, null));
				System.out.println("Mostrar Factura Con Mayor Porcentaje");
			}
		});
		inicioPanel.add(mayorPorcentajeBtn, c);
		
		//MostrarProductoMasComprado
		c.gridx++;
		JButton ProductoMasCompradoBtn = new PanelButton("resources/icons/operaciones/buscar.png"); 
		ProductoMasCompradoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_PRODUCTO_MAS_COMPRADO, null));				
				System.out.println("Mostrar producto mas comprado");
			}
		});
		inicioPanel.add(ProductoMasCompradoBtn, c);

		// MostrarFacturaTiendaCompleta
		c.gridx++;
		JButton CompletaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		CompletaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_FACTURA_TIENDA_COMPLETA, null));
				System.out.println("MostrarFacturaTiendaCompleta");
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
	
	//-- METODOS AUXILIARES
	
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
	
	private int clienteOptionPane(){
		final JComponent[] input = new JComponent[] { new JLabel("Selecciona entre IdCliente o IdDescuento") };
		Object[] options = {"IdCliente", "IdDescuento"};
		return JOptionPane.showOptionDialog(null, input, "Tipo de leer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
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
	
	private Pair<Integer, Integer> productoOptionPane(String operacion) {
		JTextField idProductoField = new JTextField();
		JTextField cantidadPaseField = new JTextField();
		final JComponent[] input = new JComponent[] {
		        new JLabel("ID Producto"),
		        idProductoField,
		        new JLabel("Cantidad"),
		        cantidadPaseField,
		};
		Object[] options = {operacion, "Cancelar"};
		int result = JOptionPane.showOptionDialog(null, input, operacion + " Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if (result == JOptionPane.OK_OPTION) {
			int idProducto = Integer.parseInt(idProductoField.getText());
			int cantidadPase = Integer.parseInt(cantidadPaseField.getText());
			return new Pair<Integer, Integer>(idProducto, cantidadPase);
		}
		
		return null;
	}
	

	
	//-- PANELES
	
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
		
		//--
		
		TablePanel productoTable = new TablePanel(new Dimension(600, 320), Arrays.asList("ID PRODUCTO", "CANTIDAD"));//, "PRECIO"));
		
		//--
		
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
		
		JButton añadirProductoBtn = createToolButton("resources/icons/operaciones/añadir-producto.png", new Color(63, 164, 31), "Añade un producto a la factura");
		añadirProductoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pair<Integer, Integer> productos = productoOptionPane("Añadir");
					if (productos != null) {
						carritoAux.setIdProducto(productos.getFirst());
						carritoAux.setCantidad(productos.getSecond());
						ApplicationController.getInstance().handleRequest(new Context(Eventos.AÑADIR_PRODUCTO, carritoAux));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				}
			}
		});
		toolPanel.add(añadirProductoBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton quitarProductoBtn = createToolButton("resources/icons/operaciones/quitar-producto.png", new Color(220, 34, 34), "Quita un producto de la factura");
		quitarProductoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pair<Integer, Integer> productos = productoOptionPane("Quitar");
					if (productos != null) {
						carritoAux.setIdProducto(productos.getFirst());
						carritoAux.setCantidad(productos.getSecond());
						ApplicationController.getInstance().handleRequest(new Context(Eventos.QUITAR_PRODUCTO, carritoAux));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				}
			}
		});
		toolPanel.add(quitarProductoBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton meterDescuentoBtn = createToolButton("", new Color(220, 34, 34), "Añade un descuento a una factura");
		meterDescuentoBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					JTextField idDescuentoField = new JTextField();
					final JComponent[] input = new JComponent[] { new JLabel("ID Descuento"),idDescuentoField };
					Object[] options = {"Descuento", "Cancelar"};
					int result = JOptionPane.showOptionDialog(null, input, "Añadir descuento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					
					if (result == JOptionPane.OK_OPTION) {
						int idDescuento = Integer.parseInt(idDescuentoField.getText());
						carritoAux.getFactura().setIdDescuento(idDescuento);
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				}
			}
		});
		toolPanel.add(meterDescuentoBtn);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
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
						JTextField idClienteField = new JTextField();
						final JComponent[] input2 = new JComponent[] {
								new JLabel("Añadir cliente"), idClienteField
						};
						Object[] options2 = {"Si", "No" };
						int result2 = JOptionPane.showOptionDialog(null, input2, "Añadir Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
						if (result2 == JOptionPane.OK_OPTION) {
							int idCliente = Integer.parseInt(idClienteField.getText());
							carritoAux.getFactura().setIdCliente(idCliente);
							ApplicationController.getInstance().handleRequest(new Context(Eventos.CERRAR_FACTURA, carritoAux));
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
		
		//--
		
		facturaPanel.add(Box.createRigidArea(new Dimension(170, 0)));
		facturaPanel.add(productoTable);
		facturaPanel.add(toolPanel);

		panel.add(facturaPanel);
		
		carritoTable = productoTable;
		carritoPrecio = totalPrecio;
		
		updatePanel(panel, 40);
	}
	
	private void devolverProductoPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID FACTURA", "ID PRODUCTO", "CANTIDAD"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer idFactura = Integer.valueOf(panel.getFieldText("ID FACTURA"));
					Integer idProducto = Integer.valueOf(panel.getFieldText("ID PRODUCTO"));
					Integer cantidad = Integer.valueOf(panel.getFieldText("CANTIDAD"));
					int tipo = tipoOptionPane();
					if (tipo == JOptionPane.YES_OPTION) 
						ApplicationController.getInstance().handleRequest(new Context(Eventos.DEVOLVER_PRODUCTO, new TLineaFactura(idFactura, new TFotoObra(idProducto), cantidad)));
					else
						ApplicationController.getInstance().handleRequest(new Context(Eventos.DEVOLVER_PRODUCTO, new TLineaFactura(idFactura, new TLibro(idProducto), cantidad)));
					
					
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private int tipoOptionPane() {
		final JComponent[] input = new JComponent[] { new JLabel("Seleccione el tipo del producto") };
		Object[] options = { "FotoObra", "Libro" };
		return JOptionPane.showOptionDialog(null, input, "Tipo Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	private void buscarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_FACTURA_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TCarrito carrito) {
		FacturaTable fTable = new FacturaTable(new Dimension(600, 175), Arrays.asList("PRODUCTO")); //false
		fTable.MostrarFacturaPorCarritoTienda(carrito);
		updatePanel(fTable, null);
	}
	
	private void listarTodoPanel(List<TCarrito> carritos) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel listarPanel = new JPanel();
		listarPanel.setLayout(new BoxLayout(listarPanel, BoxLayout.Y_AXIS));
		listarPanel.setOpaque(false);
		listarPanel.setMaximumSize(new Dimension(1024, 400));
		
		for (TCarrito c : carritos) {
			FacturaTable fTable = new FacturaTable(new Dimension(600, 150), Arrays.asList("PRODUCTO")); //false
			fTable.MostrarFacturaPorCarritoTienda(c);
			listarPanel.add(fTable);
			listarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		}
		panel.add(listarPanel);
		
		JScrollPane scrollPane = new JScrollPane(listarPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension(700, 300));
		scrollPane.setMaximumSize(new Dimension(700, 300));
		scrollPane.setOpaque(false);
		panel.add(scrollPane);
		
		updatePanel(panel, 50);
	}
	
	private void selectIDCliente() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID CLIENTE"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID CLIENTE"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LEER_POR_ID_CLIENTE, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void selectIDDescuento() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID DESCUENTO"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID DESCUENTO"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LEER_POR_ID_DESCUENTO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mayorPorcentajePanel(List<TFacturaTienda> facturas){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID"));
		for (TFacturaTienda factura : facturas)
			tablePanel.addRow(new Object[] { factura.getId() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void ProductoMasCompradoPanel(List<TProducto> productos){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("NOMBRE"));
		for (TProducto producto : productos)
			tablePanel.addRow(new Object[] { producto.getNombre() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void listarIDCliente(List<TFacturaTienda> listaClientes) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "ID CLIENTE"));
		for (TFacturaTienda cliente : listaClientes)
			tablePanel.addRow(new Object[] { cliente.getId(), cliente.getIdCliente() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void listarIDDescuento(List<TFacturaTienda> listaDescuento) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "ID DESCUENTO"));
		for (TFacturaTienda descuento : listaDescuento)
			tablePanel.addRow(new Object[] { descuento.getId(), descuento.getIdDescuento() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void listarFacturaTiendaCompletaPanel(LinkedList<TFacturaTiendaCompleta> factura) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID FACTURA", "ID PRODUCTO","ID CLIENTE"));
		for (TFacturaTiendaCompleta c : factura)
			tablePanel.addRow(new Object[] { c.getIdFactura(), c.getIdProducto(), c.getIdCliente() }, false);
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	//-- METODOS IMPLEMENTADOS
	
	public String getNombre() {
		return nombre;
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
		
			//SUCCESS
			case ABRIR_FACTURA:
				carritoAux = (TCarrito) responseContext.getDataObject();
				break;
				
			case AÑADIR_PRODUCTO_OK:
			case QUITAR_PRODUCTO_OK:
				carritoAux = (TCarrito) responseContext.getDataObject();
				if (carritoTable != null && carritoPrecio != null) {
					carritoTable.clearTable();
					for (TLineaFactura l : carritoAux.getLineaFactura()) {
						carritoTable.addRow(new Object[]{l.getProducto().getId(), l.getCantidad()}, false);//aqui falla
					}
					errorPanel.clear();
				}
				break;
				
			case CERRAR_FACTURA_OK:
				//Volver a Inicio
				remove(panelActual);
				carritoAux = null;
				carritoTable = null;
				carritoPrecio = null;
				inicioPanel.setVisible(true);
				panelActual = inicioPanel;
				errorPanel.showOutputMessage((String) responseContext.getDataObject().toString(), true);
				break;
				
			case DEVOLVER_PRODUCTO_OK:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
				break;
				
			case BUSCAR_FACTURA_POR_ID_OK:
				mostrarPanel((TCarrito) responseContext.getDataObject());
				break;
			case MOSTRAR_LISTA_FACTURAS_OK:
				listarTodoPanel((List<TCarrito>) responseContext.getDataObject());
				break;
			case LEER_POR_ID_CLIENTE_OK:
				listarIDCliente((List<TFacturaTienda>) responseContext.getDataObject());
				break;
			case LEER_POR_ID_DESCUENTO_OK:
				listarIDDescuento((List<TFacturaTienda>) responseContext.getDataObject());
				break;
			case MOSTRAR_PRODUCTO_MAS_COMPRADO_OK:
				ProductoMasCompradoPanel((List<TProducto>) responseContext.getDataObject());
				break;
			case MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO_OK:
				mayorPorcentajePanel((List<TFacturaTienda>) responseContext.getDataObject());
				break;
			case MOSTRAR_FACTURA_TIENDA_COMPLETA_OK:
				listarFacturaTiendaCompletaPanel((LinkedList<TFacturaTiendaCompleta>) responseContext.getDataObject());
				break;
			
				//ERROR
			case AÑADIR_PRODUCTO_KO:
			case QUITAR_PRODUCTO_KO:
			case CERRAR_FACTURA_KO:
			case DEVOLVER_PRODUCTO_KO:
			case BUSCAR_FACTURA_POR_ID_KO:
			case MOSTRAR_LISTA_FACTURAS_KO:
			case LEER_POR_ID_CLIENTE_KO:
			case LEER_POR_ID_DESCUENTO_KO:
			case MOSTRAR_PRODUCTO_MAS_COMPRADO_KO:
			case MOSTRAR_FACTURA_CON_MAYOR_PORCENTAJE_DESCUENTO_KO:
			case MOSTRAR_FACTURA_TIENDA_COMPLETA_KO:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
				break;
			default:
				errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
				break;
		}		
	}
}
