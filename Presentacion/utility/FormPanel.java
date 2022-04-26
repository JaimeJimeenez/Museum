package Presentacion.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormPanel extends JPanel {
	private int PANEL_HEIGHT = 300;
	private int FIELD_HEIGHT = 20;
	private int FIELD_OFFSET = 8;
	private int ENTER_HEIGHT = 30;
	private int ENTER_OFFSET = 30-8; 
	
	HashMap<String, JTextField> fields;
	JButton enterBtn;
	
	public FormPanel(List<String> fieldList) {
		fields = new HashMap<String, JTextField>();
		init(fieldList);
	}
	
	private void init(List<String> fieldList) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		setMaximumSize(new Dimension(1024, PANEL_HEIGHT));
		
		// distancia para centrar el panel
		int totalFormHeight = (2*FIELD_HEIGHT+FIELD_OFFSET)*fieldList.size()+ENTER_HEIGHT+ENTER_OFFSET;
		add(Box.createRigidArea(new Dimension(0, (PANEL_HEIGHT-totalFormHeight)/2)));
		
		// crea y registra los campos en el hash map
		for (String f : fieldList)
			createFormField(f);
		
		add(Box.createRigidArea(new Dimension(0, ENTER_OFFSET)));
		
		enterBtn = new RoundButton(10, "ENTER");
		enterBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		enterBtn.setBackground(new Color(11,83,148));
		enterBtn.setForeground(new Color(240,240,240));
		enterBtn.setMaximumSize(new Dimension(125, ENTER_HEIGHT));
		enterBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		add(enterBtn);
	}
	
	private void createFormField(String name) {
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
		fieldPanel.setBackground(new Color(61,133,198));
		fieldPanel.setMaximumSize(new Dimension(350,FIELD_HEIGHT*2));
		
		JLabel label = new JLabel(name.toUpperCase());
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		label.setMaximumSize(new Dimension(150,FIELD_HEIGHT));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(240,240,240));
		
		JTextField field = new JTextField(15);
		field.setMaximumSize(new Dimension(150,FIELD_HEIGHT));
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setForeground(new Color(240,240,240));
		field.setBackground(new Color(11,83,148));
		field.setCaretColor(field.getForeground());
		field.setBorder(null);
		
		fields.put(name.toUpperCase(), field);
		
		fieldPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		fieldPanel.add(label);
		fieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fieldPanel.add(field);
		
		add(fieldPanel);
		add(Box.createRigidArea(new Dimension(0, 8)));
	}
	
	public String getFieldText(String name) {
		return fields.get(name.toUpperCase()).getText();
	}
	
	public void addEnterActionListener(ActionListener l) {
		enterBtn.addActionListener(l);
	}
}
