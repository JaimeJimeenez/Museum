package Presentacion.utility;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import negocio.factura.TCarrito;
import negocio.factura.TFacturaTienda;
import negocio.factura.TLineaFactura;
import negocio.facturaMuseo.TCarritoMuseo;
import negocio.facturaMuseo.TFacturaMuseo;
import negocio.facturaMuseo.TLineaFacturaMuseo;

@SuppressWarnings("serial")
public class FacturaTable extends JPanel {

	JLabel facturaID, fecha, precioTotal;
	TablePanel lineaTable;
	
	public FacturaTable(Dimension tableDim) {
		init(tableDim, Arrays.asList("ID PRODUCTO", "ID FABRICANTE")); 
	}
	
	public FacturaTable(Dimension tableDim, List<String> columns) {
		init(tableDim, columns);
	}
	
	private void init(Dimension tableDim, List<String> columns) {
		setOpaque(false);
		setMaximumSize(new Dimension(1024, 460));
		
		JPanel facturaPanel = new JPanel();
		facturaPanel.setLayout(new BoxLayout(facturaPanel, BoxLayout.Y_AXIS));
		facturaPanel.setOpaque(false);
		facturaPanel.setMaximumSize(new Dimension(1024, 450));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setOpaque(false);
		topPanel.setMaximumSize(new Dimension(600, 30));
		
		facturaID = new JLabel("ID FACTURA: 1");
		fecha = new JLabel("DD/MM/YYYY");
		
		topPanel.add(facturaID);
		topPanel.add(Box.createRigidArea(new Dimension(320, 0)));		
		topPanel.add(fecha);
		
		
		List<String> modifiedColumns = new ArrayList<String>(columns);
		modifiedColumns.add("CANTIDAD");
		modifiedColumns.add("PRECIO");
		
		lineaTable = new TablePanel(tableDim, modifiedColumns);
		facturaPanel.add(lineaTable);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setOpaque(false);
		bottomPanel.setMaximumSize(new Dimension(600, 30));
		
		precioTotal = new JLabel("PRECIO TOTAL: 1000.00");
		precioTotal.setAlignmentX(SwingConstants.RIGHT);
		bottomPanel.add(precioTotal);
		
		
		facturaPanel.add(topPanel);
		facturaPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		facturaPanel.add(lineaTable);
		facturaPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		facturaPanel.add(bottomPanel);
		
		add(facturaPanel);
	}
	
	public void MostrarFacturaPorCarritoTienda(TCarrito c) {
		TFacturaTienda f = c.getFactura();
		facturaID.setText("ID FACTURA: " + f.getId());
		fecha.setText(f.getFecha().toString());
		precioTotal.setText("PRECIO TOTAL: " + c.getPrecioTotal());

		for (TLineaFactura l : c.getLineaFactura()) {
			lineaTable.addRow(new Object[]{l.getProducto().getId(), "x" + l.getCantidad(), l.getPrecio()}, false);
		}
	}
	
	public void MostrarFacturaPorCarritoMuseo(TCarritoMuseo c) {
		TFacturaMuseo f = c.getFacturaMuseo();
		facturaID.setText("ID FACTURA: " + f.getId());
		fecha.setText(f.getFecha().toString());
		precioTotal.setText("PRECIO TOTAL: " + c.getPrecio());

		for (TLineaFacturaMuseo l : c.getLineasFacturaMuseo()) {
			lineaTable.addRow(new Object[]{l.getIdEntrada(), "x" + l.getCantidad(), l.getPrecio()}, false);
		}
	}
}