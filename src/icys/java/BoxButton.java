package icys.java;

import java.awt.Color;
import java.awt.Graphics;

public class BoxButton implements Button {
	
	int x, y, width, height, state;
	Color color [] = new Color [2];
	
	public BoxButton () {
		this (0, 0, null, null);
	}
	
	public BoxButton (int width, int height) {
		this (width, height, null, null);
	}
	
	public BoxButton (int width, int height, Color color1, Color color2) {
		this.width = width;
		this.height = height;
		color [0] = color1;
		color [1] = color2;
	}
	
	public void setLocation (int x, int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	public void setSize (int width, int height) {
		this.width = width;
		this.height = height;
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
	}
	
	public boolean contains (int x, int y) {
		return (x >= this.x && x < this.x + width &&
				y >= this.y && y < this.y + height);
	}
	
	public void draw (Graphics g) {
		g.setColor (color [state]);
		g.fillRect (x, y, width, height);
	}

	public int getState () {
		return state;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
}