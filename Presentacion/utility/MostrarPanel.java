package Presentacion.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MostrarPanel extends JPanel {
	
	private int PANEL_HEIGHT = 300;
	private int LABEL_HEIGHT = 20;
	private int LABEL_OFFSET = 8;
	
	HashMap<String, JLabel> labels;
	
	public MostrarPanel(List<String> fieldList) {
		labels = new HashMap<String, JLabel>();
		init(fieldList);
	}
	
	private void init(List<String> fieldList) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		setMaximumSize(new Dimension(1024, PANEL_HEIGHT));
		
		//Distancia para centrar el panel
		int totalFormHeight = (2*LABEL_HEIGHT+LABEL_OFFSET)*fieldList.size();
		add(Box.createRigidArea(new Dimension(0, (PANEL_HEIGHT-totalFormHeight)/2)));
		
		//Crea y registra los campos en el hash map
		for (String f : fieldList)
			createLabel(f);
	}
	
	private void createLabel(String name) {
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
		fieldPanel.setBackground(new Color(61,133,198));
		fieldPanel.setMaximumSize(new Dimension(350,LABEL_HEIGHT*2));
		
		JLabel label = new JLabel(name.toUpperCase());
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setMaximumSize(new Dimension(150,LABEL_HEIGHT));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(240,240,240));
		
		JLabel info = new JLabel();
		info.setMaximumSize(new Dimension(150,LABEL_HEIGHT));
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setForeground(new Color(240,240,240));
		info.setBackground(new Color(11,83,148));
		info.setOpaque(true);
		info.setBorder(null);
		
		labels.put(name.toUpperCase(), info);
		
		fieldPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		fieldPanel.add(label);
		fieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fieldPanel.add(info);
		
		add(fieldPanel);
		add(Box.createRigidArea(new Dimension(0, 8)));
	}
	
	public void setLabelText(String name, String text) {
		labels.get(name.toUpperCase()).setText(text);
	}
}