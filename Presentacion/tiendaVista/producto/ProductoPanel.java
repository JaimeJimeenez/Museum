package Presentacion.tiendaVista.producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import Presentacion.utility.ErrorPanel;
import Presentacion.utility.FormPanel;
import Presentacion.utility.MostrarPanel;
import Presentacion.utility.PanelButton;
import Presentacion.utility.PanelGridBagConstraint;
import Presentacion.utility.RoundButton;
import Presentacion.utility.TablePanel;
import Presentacion.vistaPrincipal.GUI;
import negocio.producto.TProducto;
import negocio.producto.TFotoObra;
import negocio.producto.TLibro;

@SuppressWarnings("serial")
public class ProductoPanel extends JPanel implements GUI  {

	private static final long serialVersionUID = 1L;
	
	private String nombre = "PRODUCTO";
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;
	
	public ProductoPanel(){
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

	private void init(){
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
				int isLibro = tipoOptionPane(); 
				if (isLibro  == JOptionPane.YES_OPTION)
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
				int resTipo = tipoOptionPane(); 
				if (resTipo == JOptionPane.YES_OPTION)
					modificarPanel(true);
				else if (resTipo == JOptionPane.NO_OPTION)
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
		
		//BuscarPorID
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
		
		//MostrarTodo
		c.gridx++;
		JButton mostrarListaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		mostrarListaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_PRODUCTOS, null));
				System.out.println("Mostrar Lista");
			}
		});
		inicioPanel.add(mostrarListaBtn, c);
		
		//MostrarPorIDFabricante
		c.gridx++;
		JButton mostrarProductosPorIDFabricanteBtn = new PanelButton("resources/icons/operaciones/mostrarIDFabricante.png");
		mostrarProductosPorIDFabricanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarProductosPorIDFabricantePanel();
				System.out.println("Mostrar Productos por ID Fabricante");
			}
		});
		inicioPanel.add(mostrarProductosPorIDFabricanteBtn, c);
		
		//MostrarPorNombreFabricante
		c.gridx++;
		JButton mostrarProductosPorNombreFabricanteBtn = new PanelButton("resources/icons/operaciones/mostrarNombreFabricante.png");
		mostrarProductosPorNombreFabricanteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarProductosPorNombreFabricantePanel();
				System.out.println("Mostrar Productos por Nombre Fabricante");
			}
		});
		inicioPanel.add(mostrarProductosPorNombreFabricanteBtn, c);
		
		
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
	
	
	private void mainFormPanel(final boolean esLibro, final Eventos event) {
		final FormPanel panel;
		
		if (esLibro == true)
			panel = new FormPanel(Arrays.asList("ID FABRICANTE", "STOCK", "PRECIO", "NOMBRE", "CATEGORIA"));
		else
			panel = new FormPanel(Arrays.asList("ID FABRICANTE", "STOCK", "PRECIO", "NOMBRE", "ESTILO", "TAMAÑO"));
				
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id_fabricante = Integer.parseInt(panel.getFieldText("ID FABRICANTE"));
					int stock = Integer.parseInt(panel.getFieldText("STOCK"));
					float precio = Float.parseFloat(panel.getFieldText("PRECIO"));
					String nombre = panel.getFieldText(("NOMBRE"));
					if (esLibro == true) {
						String categoria = panel.getFieldText(("CATEGORIA"));
						ApplicationController.getInstance().handleRequest(new Context(event, new TLibro(id_fabricante, stock, precio, nombre, categoria)));
					} else {
						String estilo = panel.getFieldText(("ESTILO"));
						String tamanio = panel.getFieldText(("TAMAÑO"));
						ApplicationController.getInstance().handleRequest(new Context(event, new TFotoObra(id_fabricante, stock, precio, nombre, estilo, tamanio)));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no validos o inexistentes.", false);
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
	
	public int tipoOptionPane() {
		final JComponent[] input = new JComponent[] { new JLabel("Seleccione el tipo de producto") };
		Object[] options = {"Libro", "FotoObra" };
		return JOptionPane.showOptionDialog(null, input, "Tipo Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	private void registrarPanel(final boolean isLibro) {
		mainFormPanel(isLibro, Eventos.REGISTRAR_PRODUCTO);
	}
	
	private void modificarPanel(final boolean isLibro) {
		final FormPanel panel;
		
		if (isLibro == true)
			panel = new FormPanel(Arrays.asList("ID","ID FABRICANTE", "STOCK", "PRECIO", "NOMBRE", "CATEGORIA"));
		else
			panel = new FormPanel(Arrays.asList("ID","ID FABRICANTE", "STOCK", "PRECIO", "NOMBRE", "ESTILO", "TAMAÑO"));
				
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					int id_fabricante = Integer.parseInt(panel.getFieldText("ID FABRICANTE"));
					int stock = Integer.parseInt(panel.getFieldText("STOCK"));
					float precio = Float.parseFloat(panel.getFieldText("PRECIO"));
					String nombre = panel.getFieldText(("NOMBRE"));
					if (isLibro == true) {
						String categoria = panel.getFieldText(("CATEGORIA"));
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_PRODUCTO, new TLibro(id,id_fabricante, stock, precio, nombre, categoria)));
					} else {
						String estilo = panel.getFieldText(("ESTILO"));
						String tamanio = panel.getFieldText(("TAMAÑO"));
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_PRODUCTO, new TFotoObra(id,id_fabricante, stock, precio, nombre, estilo, tamanio)));
					}
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no validos o inexistentes.", false);
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_PRODUCTO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no valido o inexistente.", false);
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_PRODUCTO_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no vÃ¡lido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	private void mostrarProductosPorIDFabricantePanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID FABRICANTE"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID FABRICANTE"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LEER_POR_ID_FABRICANTE, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no vÃ¡lido o inexistente.", false);
				}
			}
		});

		updatePanel(panel, null);
	}

	private void mostrarProductosPorNombreFabricantePanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("NOMBRE FABRICANTE"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = panel.getFieldText("NOMBRE FABRICANTE");	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE, nombre));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no vÃ¡lido o inexistente.", false);
				}
			}
		});

		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TProducto p) { // Muestra un transfer dado
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID","ID FABRICANTE", "NOMBRE", "STOCK", "PRECIO", "CATEGORIA", "ESTILO", "TAMAÑO"));
		
		if(p.getCategoria() != null){
			panel.setLabelText("CATEGORIA", p.getCategoria());
		}
		else{
			panel.setLabelText("ESTILO", p.getEstilo());
			panel.setLabelText("TAMAÑO", p.getTamanio());
		}
		
		panel.setLabelText("ID", Integer.toString(p.getId()));
		panel.setLabelText("ID FABRICANTE", Integer.toString(p.getIdFabricante()));
		panel.setLabelText("STOCK", Integer.toString(p.getStock()));
		panel.setLabelText("PRECIO", Double.toString(p.getPrecio()));
		panel.setLabelText("NOMBRE", p.getNombre());
		updatePanel(panel, 50);
	}
	private void mostrarListaPanel(List<TProducto> lista) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275),Arrays.asList("ID","ID FABRICANTE", "NOMBRE", "STOCK", "PRECIO", "CATEGORIA", "ESTILO", "TAMAÑO"));		
		for (TProducto p : lista){
			Object[] datos = new Object[]{ };
			if(p.getCategoria() != null){
				datos = new Object[]{Integer.toString(p.getId()),Integer.toString(p.getIdFabricante()),p.getNombre(), Integer.toString(p.getStock()), Double.toString(p.getPrecio()), p.getCategoria(), null, null};
			}
			else{
				datos = new Object[]{Integer.toString(p.getId()),Integer.toString(p.getIdFabricante()),p.getNombre(), Integer.toString(p.getStock()), Double.toString(p.getPrecio()),null, p.getEstilo(), p.getTamanio()};
			}
			tablePanel.addRow(datos, false);
		}
		
		panel.add(tablePanel);
		
		updatePanel(panel, null);
	}
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public String getNombre() {
		return nombre;
	}
	@SuppressWarnings("unchecked")
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
		
		//SUCCESS
		case REGISTRAR_PRODUCTO_OK:
		case MODIFICAR_PRODUCTO_OK:
		case BORRAR_PRODUCTO_OK:
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
			break;
		case BUSCAR_PRODUCTO_POR_ID_OK:
			mostrarPanel((TProducto) responseContext.getDataObject());
			break;
		case MOSTRAR_LISTA_PRODUCTOS_OK:
		case LEER_POR_ID_FABRICANTE_OK:
			mostrarListaPanel((List<TProducto>) responseContext.getDataObject());
			break;
		case MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE_OK:
			mostrarListaPanel((List<TProducto>) responseContext.getDataObject());
			break;
			
		//ERROR
		case REGISTRAR_PRODUCTO_KO:
		case MODIFICAR_PRODUCTO_KO:
		case BORRAR_PRODUCTO_KO:
		case BUSCAR_PRODUCTO_POR_ID_KO:
		case LEER_POR_ID_FABRICANTE_KO:
		case MOSTRAR_PRODUCTO_POR_NOMBRE_FABRICANTE_KO:
			
			errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
			break;
		default:
			errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
			break;
	}
		
	}

	
	
}