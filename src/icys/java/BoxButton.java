package icys.java;

//Imports
import java.awt.Color;
import java.awt.Graphics;

public class BoxButton implements Button {
	
	//Initialization
	int x, y, width, height, state;
	Color color [] = new Color [2];
	
	//Constructor
	public BoxButton () {
		this (0, 0, null, null);
	}
	
	//Modified Constructor
	public BoxButton (int width, int height) {
		this (width, height, null, null);
	}
	
	//EVEN MORE modified constructor
	public BoxButton (int width, int height, Color color1, Color color2) {
		this.width = width;
		this.height = height;
		color [0] = color1;
		color [1] = color2;
	}
	
	//Set location of where it should appear
	public void setLocation (int x, int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	//set size of the button
	public void setSize (int width, int height) {
		this.width = width;
		this.height = height;
	}

	//Set state of button. Either ON/OFF
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
	
	//Whether it contains the value of parameter
	public boolean contains (int x, int y) {
		return (x >= this.x && x < this.x + width &&
				y >= this.y && y < this.y + height);
	}
	
	//GUI
	public void draw (Graphics g) {
		g.setColor (color [state]);
		g.fillRect (x, y, width, height);
	}

	//Return state of box button --> On/Off
	public int getState () {
		return state;
	}
	
	//Return X position
	public int getX () {
		return x;
	}
	
	//Return Y position
	public int getY () {
		return y;
	}
	
	//Return width
	public int getWidth () {
		return width;
	}
	
	//Return heights
	public int getHeight () {
		return height;
	}
}