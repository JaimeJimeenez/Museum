package Presentacion.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ErrorPanel extends JPanel {

	boolean debuggingMessage = false; // true = Muestra un mensaje para visualizar la barra de errores
	
	JLabel errorLabel;
	Color successColor = new Color(76,187,23);
	Color errorColor = new Color(186,0,0);
	
	public ErrorPanel(int height) {
		init(height);
	}

	private void init(int height) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setOpaque(false);
		setMaximumSize(new Dimension(1024,height));
		
		errorLabel = debuggingMessage ? new JLabel("Mensaje barra output.") : new JLabel();
		errorLabel.setFont(new Font("Arial", Font.BOLD, 17));
		errorLabel.setForeground(new Color(7, 55, 99));
		errorLabel.setMaximumSize(new Dimension(650,height));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(Box.createRigidArea(new Dimension((1024/2)-(650/2), height/2)));
		add(errorLabel);
		add(Box.createRigidArea(new Dimension(870-((1024/2)+(650/2)), 0)));
	}
	
	public void showOutputMessage(String msg, boolean success) {
		errorLabel.setText(msg);
		if (success)
			errorLabel.setForeground(successColor);
		else
			errorLabel.setForeground(errorColor);
	}
	
	public void clear() {
		errorLabel.setText("");
	}
}