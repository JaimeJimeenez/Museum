package Presentacion.museoVista.empleado;

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
import negocio.cliente.TCliente;
import negocio.empleado.TEmpleado;
import negocio.empleado.TJornadaCompleta;
import negocio.empleado.TJornadaParcial;

public class EmpleadoPanel extends JPanel implements GUI{

	private String nombre = "EMPLEADO";
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;
	
	public EmpleadoPanel(){
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
	
	private void init(){
		setLayout(new BorderLayout());
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		inicioPanel = new JPanel(new GridBagLayout());
		inicioPanel.setOpaque(false);
		inicioPanel.setMaximumSize(new Dimension(1024, INICIO_PANEL_HEIGHT));
		
		GridBagConstraints c = new PanelGridBagConstraint();
		
		// Registrar
		JButton registrarBtn = new PanelButton("resources/icons/operaciones/registrar.png");
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isJornadaCompleta = jornadaCompletaOptionPane(); 
				if (isJornadaCompleta  == JOptionPane.YES_OPTION)
					registrarPanel(true);
				else
					registrarPanel(false);
				System.out.println("Registrar");
			}
		});
		inicioPanel.add(registrarBtn, c);
		
		// Modificar
		c.gridx++;
		JButton modificarBtn = new PanelButton("resources/icons/operaciones/modificar.png");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isJornadaCompleta = jornadaCompletaOptionPane();
				if (isJornadaCompleta == JOptionPane.YES_OPTION)
					modificarPanel(true);
				else
					modificarPanel(false);
				System.out.println("Modificar");
			}
		});
		inicioPanel.add(modificarBtn, c);
		
		// Borrar
		c.gridx++;
		JButton borrarBtn = new PanelButton("resources/icons/operaciones/borrar.png");
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPanel();
				System.out.println("Borrar");
			}
		});
		inicioPanel.add(borrarBtn, c);
		
		// Buscar por ID
		c.gridx++;
		JButton buscarBtn = new PanelButton("resources/icons/operaciones/buscar.png");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPanel();
				System.out.println("Buscar");
			}
		});
		inicioPanel.add(buscarBtn, c);
		
		// Mostrar lista
		c.gridx = 0;
		c.gridy++;
		JButton mostrarListaBtn = new PanelButton("resources/icons/operaciones/listar.png");
		mostrarListaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_LISTA_EMPLEADOS, null));
			}
		});
		inicioPanel.add(mostrarListaBtn, c);
		
		// Listar por jornada
		c.gridx++;
		JButton listarPorTipoBtn = new PanelButton("resources/icons/operaciones/listarTipoEmpleado.png");
		listarPorTipoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int isJornadaCompleta = jornadaCompletaOptionPane();
				if (isJornadaCompleta == JOptionPane.NO_OPTION)
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LISTAR_EMPLEADOS_POR_JORNADA, true));
				else
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LISTAR_EMPLEADOS_POR_JORNADA, false));
			}
		});
		inicioPanel.add(listarPorTipoBtn, c);
		
		// Leer por ID turno
		c.gridx++;
		JButton leerPorIdTurnoBtn = new PanelButton("resources/icons/operaciones/buscarEmpleadoPorId.png");
		leerPorIdTurnoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leerPorIdTurno();
				System.out.println("Leer por ID turno");
			}
		});
		inicioPanel.add(leerPorIdTurnoBtn, c);
		
		// Calculo de nomina
		c.gridx++;
		JButton mostrarNominaEmpleadoBtn = new PanelButton("resources/icons/operaciones/buscarNomina.png");
		mostrarNominaEmpleadoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarNominaEmpleado();
				System.out.println("Mostrar nomina empleado");
			}
		});
		inicioPanel.add(mostrarNominaEmpleadoBtn, c);
		
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
	
	private void mainFormPanel(boolean isJornadaCompleta, final Eventos event) {
		final FormPanel panel;
		
		if (isJornadaCompleta)
			panel = new FormPanel(Arrays.asList("DNI", "NOMBRE", "SUELDO", "COMPLEMENTOS", "ID_TURNO"));
		else
			panel = new FormPanel(Arrays.asList("DNI", "NOMBRE", "SUELDO_HORAS", "HORAS", "ID_TURNO"));
				
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String dni = panel.getFieldText("DNI");
					String nombre = panel.getFieldText("NOMBRE");
					int idTurno = Integer.parseInt(panel.getFieldText("ID_TURNO"));
					if (isJornadaCompleta) {
						double sueldo = Double.parseDouble(panel.getFieldText("SUELDO"));
						String complementos = panel.getFieldText("COMPLEMENTOS");
						ApplicationController.getInstance().handleRequest(new Context(event, new TJornadaCompleta(dni, nombre, idTurno, sueldo, complementos)));
					}
					else {
						double sueldoHoras = Double.parseDouble(panel.getFieldText("SUELDO_HORAS"));
						int horas = Integer.parseInt(panel.getFieldText("HORAS"));
						ApplicationController.getInstance().handleRequest(new Context(event, new TJornadaParcial(dni, nombre, idTurno, sueldoHoras, horas)));
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
	
	private int jornadaCompletaOptionPane() {
		final JComponent[] input = new JComponent[] { new JLabel("Seleccione el tipo de empleado") };
		Object[] options = {"JornadaCompleta", "JornadaParcial" };
		return JOptionPane.showOptionDialog(null, input, "Tipo Empleado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
	
	private void registrarPanel(boolean isJornadaCompleta) {
		mainFormPanel(isJornadaCompleta, Eventos.REGISTRAR_EMPLEADO);
	}
	
	private void modificarPanel(final boolean isJornadaCompleta) {
		final FormPanel panel;
		
		if (isJornadaCompleta){
			panel = new FormPanel(Arrays.asList("ID", "NOMBRE", "SUELDO", "COMPLEMENTOS", "ID_TURNO"));
		}
		else 
			panel = new FormPanel(Arrays.asList("ID", "NOMBRE", "SUELDO_HORAS", "HORAS", "ID_TURNO"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					String dni = "99999999A";
					String nombre = panel.getFieldText("NOMBRE");
					int idTurno = Integer.parseInt(panel.getFieldText("ID_TURNO"));
					if (isJornadaCompleta) {
						double sueldo = Double.parseDouble(panel.getFieldText("SUELDO"));
						String complementos = panel.getFieldText("COMPLEMENTOS");
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_EMPLEADO, new TJornadaCompleta(id, dni, nombre, idTurno, sueldo, complementos)));
					}
					else {
						double sueldoHoras = Double.parseDouble(panel.getFieldText("SUELDO_HORAS"));
						int horas = Integer.parseInt(panel.getFieldText("HORAS"));
						ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_EMPLEADO, new TJornadaParcial(id, dni, nombre, idTurno, sueldoHoras, horas)));
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_EMPLEADO, id));
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_EMPLEADO_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mostrarListaPanel(List<TEmpleado> empleados) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "DNI", "NOMBRE", "JORNADA", "SUELDO", "COMPLEMENTOS", "SUELDO_HORAS", "HORAS"));
		for (TEmpleado tEmpleado : empleados) {
			Object[] datos = new Object[]{ };
			if (tEmpleado.getSueldo() != 0) {
				 datos = new Object[] { Integer.toString(tEmpleado.getId()), tEmpleado.getDni(), tEmpleado.getNombre(), "Jornada Completa", tEmpleado.getSueldo(), tEmpleado.getComplementos(), null, null };
			}
			else {
				datos = new Object[] {Integer.toString(tEmpleado.getId()), tEmpleado.getDni(), tEmpleado.getNombre(), "Jornada Parcial", null, null, tEmpleado.getSueldoHoras(), tEmpleado.getHoras() };
			}
			tablePanel.addRow(datos, false);
		}
		
		panel.add(tablePanel);
		
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TEmpleado tEmpleado) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID", "DNI", "NOMBRE", "TIPO", "SUELDO", "COMPLEMENTOS", "SUELDO_HORAS", "HORAS"));
		
		if (tEmpleado.getSueldo() != 0) {
			panel.setLabelText("TIPO", "Jornada completa");
			panel.setLabelText("SUELDO", Double.toString(tEmpleado.getSueldo()));
			panel.setLabelText("COMPLEMENTOS", tEmpleado.getComplementos());
			panel.setLabelText("SUELDO_HORAS", null);
			panel.setLabelText("HORAS", null);
		} else {	
			panel.setLabelText("TIPO", "Jornada parcial");
			panel.setLabelText("SUELDO_HORAS", Double.toString(tEmpleado.getSueldoHoras()));
			panel.setLabelText("HORAS", Integer.toString(tEmpleado.getHoras()));
			panel.setLabelText("SUELDO", null);
			panel.setLabelText("COMPLEMENTOS", null);
		}

		panel.setLabelText("ID", Integer.toString(tEmpleado.getId()));
		panel.setLabelText("DNI", tEmpleado.getDni());
		panel.setLabelText("NOMBRE", tEmpleado.getNombre());
		updatePanel(panel, 50);
	}
	
	private void leerPorIdTurno() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID TURNO"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID TURNO"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.LEER_POR_ID_TURNO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});

		updatePanel(panel, null);
	}
	
	private void mostrarNominaEmpleado(){
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MOSTRAR_NOMINA_EMPLEADO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void mostrarPanelNomina(Double nomina) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("NOMINA"));
		
		panel.setLabelText("NOMINA", nomina.toString());

		updatePanel(panel, 50);
	}
	
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()){
			case REGISTRAR_EMPLEADO_OK:
			case MODIFICAR_EMPLEADO_OK:
			case BORRAR_EMPLEADO_OK:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
				break;
			case BUSCAR_EMPLEADO_POR_ID_OK:
				mostrarPanel((TEmpleado) responseContext.getDataObject());
				break;
			case MOSTRAR_LISTA_EMPLEADOS_OK:
			case LISTAR_EMPLEADOS_POR_JORNADA_OK:
			case LEER_POR_ID_TURNO_OK:
				mostrarListaPanel((List<TEmpleado>) responseContext.getDataObject());
				break;
			case MOSTRAR_NOMINA_EMPLEADO_OK:
				mostrarPanelNomina((Double) responseContext.getDataObject());
				break;
			case REGISTRAR_EMPLEADO_KO:
			case MODIFICAR_EMPLEADO_KO:
			case BORRAR_EMPLEADO_KO:
			case MOSTRAR_LISTA_EMPLEADOS_KO:
			case BUSCAR_EMPLEADO_POR_ID_KO:
			case LISTAR_EMPLEADOS_POR_JORNADA_KO:
			case LEER_POR_ID_TURNO_KO:
			case MOSTRAR_NOMINA_EMPLEADO_KO:
				System.out.println((String) responseContext.getDataObject());
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
