package Presentacion.utility;

import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class PanelGridBagConstraint extends GridBagConstraints {
	public PanelGridBagConstraint() {
		gridx = 0;
		gridy = 0;
	    weightx = 0;
	    weighty = 0;
	    fill = GridBagConstraints.CENTER;
		insets = new Insets(0,30, 30, 30); //top left bottom right
	}
}