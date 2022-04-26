package Presentacion.utility;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RoundButton extends JButton {
	
	private int radius = 0;
	private String text = null;
	private BufferedImage image = null;
	
	public RoundButton(int radius) {
		super();
		this.radius = radius;
		init();
	}

	public RoundButton(int radius, String text) {
		super();
		this.radius = radius;
		this.text = text;
		init();
	}
	
	private void init() {
		setContentAreaFilled(false);
		setBorder(null);
		setFocusPainted(false);
	}
	
	public void setIcon(String icon) {
		try {
			image = ImageIO.read(new File(icon));
		} catch(IOException e) {
			
		}
	}
	
	private void drawString(Graphics2D g, int width, int height) {
        if (text == null) return;
        		
        FontMetrics fm = g.getFontMetrics();
        double textWidth = fm.getStringBounds(text, g).getWidth();
        g.setColor(getForeground());
        g.drawString(text, (int) (width/2 - textWidth/2), (int) (height/2 + fm.getMaxAscent()/2));
	}
	
	private void drawImage(Graphics2D g, int width, int height) {
        if (image == null) return;
        
        AffineTransform at = new AffineTransform();
        at.translate((int) (width/2 - image.getWidth()/2), (int) (height/2 - image.getHeight()/2));
        g.drawImage(image, at, null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel model = getModel();
        if (model.isPressed()) {
			Color c = getBackground();
			graphics.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 80));
		} else {
            graphics.setColor(getBackground());
		}
        
        graphics.fillRoundRect(0, 0, width, height, radius, radius);
        
        drawString(graphics, width, height);
        drawImage(graphics, width, height);
	}
}