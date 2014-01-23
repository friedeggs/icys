package icys.java;

//import
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import static icys.java.Utilities.*;

// VERSION OF BOXBUTTON FOR BLOCKS

public class Tile extends Entity implements Button {
	
	// Initialization
	int state; // X AND Y ARE INDICES, UNLESS IN BOX BUTTON
	Color color [] = {
			new Color (170, 255, 240),  // MOUSE OVER
			Color.WHITE,
			lightblue,					// SELECTED
			Color.WHITE
	};
	
	// More initialization
	int border = 4;
	int xPoints [] = new int [4], yPoints [] = new int [4];
	int xPoints2 [] = new int [4], yPoints2 [] = new int [4];
	Polygon poly, smallPoly;
	
	public Tile (int x, int y) {
		int shift2 = shift * (block_height-2*border) / block_height;
		this.x = x;
		this.y = y;
		
		/*===== X COORDINATES ===== */
		xPoints [0] = coordX(x);
		xPoints [1] = coordX(x)+block_width;
		xPoints [2] = coordX(x)+block_width-shift;
		xPoints [3] = coordX(x)-shift;
		
		/*===== y COORDINATES ===== */
		yPoints [0] = coordY(y);
		yPoints [1] = coordY(y);
		yPoints [2] = coordY(y)+block_height;
		yPoints [3] = coordY(y)+block_height;
		
		// Define Polygon
		poly = new Polygon (xPoints, yPoints, 4);
		
		/*===== X2 COORDINATES ===== */
		xPoints2 [0] = coordX(x)+border;
		xPoints2 [1] = coordX(x)+block_width-2* border;
		xPoints2 [2] = coordX(x)+block_width-shift2-2* border;
		xPoints2 [3] = coordX(x)-shift2+border;
		
		/*===== Y2 COORDINATES ===== */
		yPoints2 [0] = coordY(y)+border;
		yPoints2 [1] = coordY(y)+border;
		yPoints2 [2] = coordY(y)+block_height-border;
		yPoints2 [3] = coordY(y)+block_height-border;
		
		// Polygon 2
		smallPoly = new Polygon (xPoints2, yPoints2, 4);
	}

	//Set where to find/draw
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	//Set size
	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	//Set state on/off
	@Override
	public void setState(int state) {
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

	//so what is in it?
	@Override
	public boolean contains(int x, int y) {
		return poly.contains(x, y);
	}

	//Moar GUI for everyone!
	@Override
	public void draw(Graphics g) {
		if (state != 0) {
			g.setColor(color [state*2 - 2]);
			g.fillPolygon(poly);
			g.setColor(color [state*2 - 1]);
			g.fillPolygon(smallPoly);
		}
//		else
//		{
//			g.setColor(Color.BLACK);
//			g.drawPolygon(poly);
//			g.setColor(Color.WHITE);
//			g.drawPolygon(smallPoly);
//		}
	}

	// get what state it's in. On/Off?
	@Override
	public int getState() {
		return state;
	}

	//Get X value
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Get Y value
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Get width
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Get height
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Display, as usual
	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		draw (g);
	}
	
}