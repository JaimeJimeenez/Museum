package Presentacion.utility;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TablePanel extends JPanel {

	DefaultTableModel tableModel;
	JTable dataTable;
	
	public TablePanel(Dimension dimension, List<String> columns) {
		init(dimension, columns);
	}
	
	private void init(Dimension dimension, List<String> columns) {		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentX(JComponent.CENTER_ALIGNMENT);
		setOpaque(false);
	    setPreferredSize(dimension);
		setMaximumSize(dimension);

		tableModel = new DefaultTableModel(); 
	    for (String c : columns)
	    	tableModel.addColumn(c);
	    	
		dataTable = new JTable(tableModel);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columns.size(); i++)
			dataTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		add(new JScrollPane(dataTable));
	}
	
	private int getRowById(String id) {
		int foundRow = -1;
		for (int row = 0; row < dataTable.getRowCount(); row++){
			if (tableModel.getValueAt(row, 0).toString().equals(id)) {
				foundRow = row;
				break;
			}
		}
		return foundRow;
	}
	
	public void addRow(Object[] data, boolean update) {
		int row = getRowById(data[0].toString());
		if (row == -1) // no existe, se puede añadir
			tableModel.addRow(data);
		else if (update)
			updateRowAt(Integer.parseInt(data[1].toString()), row, 1);
	}
	
	private void update(int value, int row, int column) {
		int currentValue = Integer.parseInt(tableModel.getValueAt(row, column).toString());
		if (value < 0 && -value >= currentValue)
			tableModel.removeRow(row);
		else
			tableModel.setValueAt(Integer.toString(currentValue + value), row, column);
	}
	
	public void updateRowAt(int value, int row, int column) {
		if (column == 0) return; //Evitar actulizar los id, solo valores
		update(value, row, column);
	}
	
	public void updateRowByIdAt(String id, int value, int column) {
		if (column == 0) return; //Evitar actulizar los id, solo valores
		
		int row = getRowById(id);
		if (row > -1)
			update(value, row, column);
	}
	
	public void clearTable() {
		tableModel.setRowCount(0);
	}
}