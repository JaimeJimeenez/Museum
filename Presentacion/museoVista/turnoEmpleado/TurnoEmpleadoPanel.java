package Presentacion.museoVista.turnoEmpleado;

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
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TCorriente;
import Negocio.Cliente.TSocio;
import Negocio.TurnoEmpleado.TTurnoEmpleado;

public class TurnoEmpleadoPanel extends JPanel implements GUI {

	private static final long serialVersionUID = 1L;
	
	private String nombre = "TURNO EMPLEADO";
	
	private int INICIO_PANEL_HEIGHT = 300;
	private JPanel inicioPanel, panelActual;
	private ErrorPanel errorPanel;
	
	public TurnoEmpleadoPanel() {
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
				registrarPanel();
				System.out.println("Registrar");
			}
		});
		inicioPanel.add(registrarBtn, c);
		
		//Modificar 
		c.gridx++;
		JButton modificarBtn = new PanelButton("resources/icons/operaciones/modificar.png");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPanel();
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
		
		//Listar
		c.gridx++;
		JButton listarBtn = new PanelButton("resources/icons/operaciones/listar.png");
		listarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().handleRequest(new Context(Eventos.LISTAR_TURNOS, null));
			}
		});
		inicioPanel.add(listarBtn, c);
		
		c.gridx++;
		JButton calculoNominaBtn = new PanelButton("resources/icons/operaciones/calcularNomina.png");
		calculoNominaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				nominaPanel();
				System.out.println("Calculo nomina del turno");
			}
		});
		inicioPanel.add(calculoNominaBtn, c);
		
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
	
	@Override
	public JPanel getPanel() { return this; }
	
	@Override
	public String getNombre() { return nombre; }

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Context responseContext) {
		switch(responseContext.getEvent()) {
			case REGISTRAR_TURNO_OK:
			case MODIFICAR_TURNO_OK:
			case BORRAR_TURNO_OK:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), true);
				break;
			case BUSCAR_TURNO_POR_ID_OK:
				mostrarPanel((TTurnoEmpleado) responseContext.getDataObject());
				break;
			case CALCULO_NOMINA_TURNO_OK:
				mostrarPanelNomina((double) responseContext.getDataObject());
				break;
			case LISTAR_TURNOS_OK:
				mostrarListaPanel((List<TTurnoEmpleado>) responseContext.getDataObject());
				break;
			case REGISTRAR_TURNO_KO:
			case MODIFICAR_TURNO_KO:
			case BORRAR_TURNO_KO:
			case BUSCAR_TURNO_POR_ID_KO:
			case LISTAR_TURNOS_KO:
			case CALCULO_NOMINA_TURNO_KO:
				errorPanel.showOutputMessage((String) responseContext.getDataObject(), false);
				break;
			default:
				errorPanel.showOutputMessage("Evento desconocido: " + responseContext.getEvent().toString(), false);
				break;
		}
	}
	
	private void registrarPanel() {
		mainFormPanel(Eventos.REGISTRAR_TURNO);
	}

	private void modificarPanel() {
		final FormPanel panel = new FormPanel(Arrays.asList("ID", "HORA ENTRADA", "HORA SALIDA"));
		
		panel.addEnterActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));
					String horaEntrada = panel.getFieldText("HORA ENTRADA");
					String horaSalida = panel.getFieldText("HORA SALIDA");
					ApplicationController.getInstance().handleRequest(new Context(Eventos.MODIFICAR_TURNO, new TTurnoEmpleado(id, horaEntrada, horaSalida)));
				}
				catch (NumberFormatException exception) {
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BORRAR_TURNO, id));
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
					ApplicationController.getInstance().handleRequest(new Context(Eventos.BUSCAR_TURNO_POR_ID, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
	}
	
	private void nominaPanel(){
		
		final FormPanel panel = new FormPanel(Arrays.asList("ID"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(panel.getFieldText("ID"));	
					ApplicationController.getInstance().handleRequest(new Context(Eventos.CALCULO_NOMINA_TURNO, id));
				} catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipo de dato no válido o inexistente.", false);
				}
			}
		});
		
		updatePanel(panel, null);
		
	}
	
	private void mostrarListaPanel(List<TTurnoEmpleado> turnos) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setMaximumSize(new Dimension(1024, 460));
		
		TablePanel tablePanel = new TablePanel(new Dimension(775, 275), Arrays.asList("ID", "HORA ENTRADA", "HORA SALIDA"));
		for (TTurnoEmpleado tTurnoEmpleado: turnos) {
			Object[] datos = new Object[]{ tTurnoEmpleado.getId(),tTurnoEmpleado.getHoraEntrada(), tTurnoEmpleado.getHoraSalida() };
			tablePanel.addRow(datos, false);
		}
		
		panel.add(tablePanel);
		updatePanel(panel, null);
	}
	
	private void mostrarPanel(TTurnoEmpleado tTurnoEmpleado) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("ID", "HORA ENTRADA", "HORA SALIDA"));
		
		panel.setLabelText("ID", Integer.toString(tTurnoEmpleado.getId()));
		panel.setLabelText("HORA ENTRADA", tTurnoEmpleado.getHoraEntrada());
		panel.setLabelText("HORA SALIDA", tTurnoEmpleado.getHoraSalida());
		
		updatePanel(panel, 50);
	}
	
	private void mostrarPanelNomina(double sumaNomina) {
		MostrarPanel panel = new MostrarPanel(Arrays.asList("CALCULO NOMINA"));
		
		panel.setLabelText("CALCULO NOMINA", Double.toString(sumaNomina));
		
		updatePanel(panel, null);
	}
	
	private void mainFormPanel(final Eventos event) {
		final FormPanel panel = new FormPanel(Arrays.asList("HORA ENTRADA", "HORA SALIDA"));
		
		panel.addEnterActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String horaEntrada = panel.getFieldText("HORA ENTRADA");
					String horaSalida = panel.getFieldText("HORA SALIDA");
					ApplicationController.getInstance().handleRequest(new Context(event, new TTurnoEmpleado(0, horaEntrada, horaSalida)));
				} 
				catch (NumberFormatException ex) {
					errorPanel.showOutputMessage("Tipos de datos no válidos o inexistentes.", false);
				} 
				catch (IllegalArgumentException ex) {
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
}
