package Presentacion.utility;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class PanelButton extends RoundButton {
	
	private static int radius = 40;
	private Color defaultColor = new Color(61,133,198);
	
	public PanelButton(String icon) {
		super(radius);
		
		setPreferredSize(new Dimension(120,120));
		setBackground(defaultColor);
		super.setIcon(icon);
	}
	
	public PanelButton(String icon, Color color) {
		super(radius);
		
		setPreferredSize(new Dimension(120,120));
		setBackground(color);
		setIcon(icon);
	}
}