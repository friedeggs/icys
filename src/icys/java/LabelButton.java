package icys.java;
import static icys.java.Utilities.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class LabelButton extends JLabel {
	Color fore[], back[];
	int state; // 0 or 1
	boolean clicked = false;
	Font font;
	
	public LabelButton (String text, Font font, Color fore1, Color back1) {
		this (text, font, fore1, back1, null, null);
	}
	
	public LabelButton (String text, Font font, 
			Color fore1, Color back1, Color fore2, Color back2) {
		super (text);
		this.font = font;
		fore = new Color [2];
		back = new Color [2];
		this.fore[0] = fore1;
		this.back[0] = back1;
		this.fore[1] = fore2;
		this.back[1] = back2;
		setFont (font);
		setOpaque (true);
		setBorder (new EmptyBorder (0, 6, 0, 6)); // add 6 pixel padding on sides
		setState (0); // set to state 0
	}
	
	public void applyGraphics (Graphics g) {
		FontMetrics metrics = g.getFontMetrics (font);
		setSize(metrics.stringWidth(getText())+12, // 12: padding * 2
				metrics.getHeight());
		//System.out.println(getText() + " " + getX() + " " + getY());
	}
	
	public boolean contains (int x, int y) {
		return (x >= getX() && x < getX() + getWidth() &&
				y >= getY() && y < getY() + getHeight());
	}
	
	public void setState (int state) {
		if (state == -1) { // switch states
			if (this.state == 0)
				this.state = 1;
			else
				this.state = 0;
		}
		else {
			this.state = state;
		}
		setForeground (fore [this.state]);
		setBackground (back [this.state]);
	}
	
	public int getState () {
		return state;
	}
	
	public void setClicked (boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setLocation (int x, int y) {
		super.setLocation(x - getWidth() / 2, y - getHeight() / 2);
	}
}