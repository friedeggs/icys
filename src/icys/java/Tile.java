package icys.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import static icys.java.Utilities.*;

// VERSION OF BOXBUTTON FOR BLOCKS

public class Tile extends Entity implements Button {
	
	int x, y, state; // X AND Y ARE INDICES, UNLESS IN BOX BUTTON
	Color color [] = {
			new Color (170, 255, 240),  // MOUSE OVER
			Color.WHITE,
			lightblue,					// SELECTED
			Color.WHITE
	};
	int border = 4;
	int xPoints [] = new int [4], yPoints [] = new int [4];
	int xPoints2 [] = new int [4], yPoints2 [] = new int [4];
	Polygon poly, smallPoly;
	
	public Tile (int x, int y) {
		int shift2 = shift * border / block_height;
		this.x = x;
		this.y = y;
		xPoints [0] = coordX(x);
		xPoints [1] = coordX(x)+block_width;
		xPoints [2] = coordX(x)+block_width-shift;
		xPoints [3] = coordX(x)-shift;
		yPoints [0] = coordY(y);
		yPoints [1] = coordY(y);
		yPoints [2] = coordY(y)+block_height;
		yPoints [3] = coordY(y)+block_height;
		poly = new Polygon (xPoints, yPoints, 4);

		xPoints2 [0] = coordX(x)+border;
		xPoints2 [1] = coordX(x)+block_width-border;
		xPoints2 [2] = coordX(x)+block_width-shift-border;
		xPoints2 [3] = coordX(x)-shift+border;
		yPoints2 [0] = coordY(y)+border;
		yPoints2 [1] = coordY(y)+border;
		yPoints2 [2] = coordY(y)+block_height-border;
		yPoints2 [3] = coordY(y)+block_height-border;
		smallPoly = new Polygon (xPoints2, yPoints2, 4);
	}

	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public boolean contains(int x, int y) {
		return poly.contains(x, y);
	}

	@Override
	public void draw(Graphics g) {
		if (state != 0) {
			g.setColor(color [state*2 - 2]);
			g.fillPolygon(poly);
			g.setColor(color [state*2 - 1]);
			g.fillPolygon(smallPoly);
		}
	}

	@Override
	public int getState() {
		return state;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		draw (g);
	}
	
}