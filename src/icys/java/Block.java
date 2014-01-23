package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value;
	public LifeForm lifeform, targeter;
	public Polygon poly;

	public Block(int value1, int i, int j) {
		value = value1;
		x = i;
		y = j;
		int [] xPoint = {coordX(x), coordX(x)+block_width, 
				coordX(x)+block_width-shift, coordX(x)-shift};
		int [] yPoint = {coordY(y), coordY(y), 
				coordY(y)+block_height, coordY(y)+block_height};
		poly = new Polygon (xPoint, yPoint, xPoint.length);
		System.out.println(x + " " + coordX(x));
	}

	public void set(LifeForm l) {
		lifeform = l;
		//if (lifeform == null)
			//System.out.println ("NULL LIFE FORM @" + x + " " + y);
	}	
	
	public void setTargeter(LifeForm l) {
		targeter = l;
	}
	
	public void update (Graphics g) {
		if (TIMER % sleep == 0 && value == LAND &&
				!(targeter == null && (lifeform instanceof Egg 
				|| lifeform instanceof Fish || lifeform instanceof Player))) {
		//if (targeter != null) {
			lifeform = targeter;
			targeter = null;
		}
		//}
		show (g);
	}
	
	public void changeValue (char x){
		if (x == '0')
			value = Utilities.WATER;
		else if (x =='1')
			value = Utilities.LAND;
		else if (x =='2')
			value = Utilities.UNUSED;
		else if (x =='3')
			value = Utilities.POLLUTED;
	}

	public void show(Graphics g) // and char a??
	{
		Color //aqua, //new Color(175, 217, 255), 
				land = new Color(255, 255, 255), use;

		if (value != 1) {
			if (value == 3) {
				use = dark; // water pollution
			}
			else
				use = water;
		} else
			use = land;
		if (use == land) {
			use = new Color (255-x*3,255, 255-y*3);
		}

		int [] xPoint = {coordX(x), coordX(x)+block_width, 
				coordX(x)+block_width-shift, coordX(x)-shift};
		int [] yPoint = {coordY(y), coordY(y), 
				coordY(y)+block_height, coordY(y)+block_height};
		g.setColor(use);
		poly = new Polygon (xPoint, yPoint, xPoint.length);
		g.fillPolygon (poly);
		if (value == LAND) {
			g.setColor (blue);
			g.drawPolygon (poly);
		}
		else if (y > 0 && blocks [x][y-1].value == 1) {
			int ledge = 5;
			int [] threedX = {coordX(x), coordX(x)+block_width, 
					coordX(x)+block_width, coordX(x)};
			int [] threedY = {coordY(y), coordY(y), 
					coordY(y)+ledge, coordY(y)+ledge};
			g.setColor(Color.GRAY);
			Polygon threed = new Polygon (threedX, threedY, threedX.length);
			g.fillPolygon (threed);
		}		
		if (value != 1 && x > 0 && blocks [x-1][y].value == 1) {
			int ledge = 5;
			int [] threedX = {coordX(x), coordX(x), 
					coordX(x)-shift, coordX(x)-shift};
			int [] threedY = {coordY(y), coordY(y)+ledge, 
					coordY(y)+block_height+ledge, coordY(y)+block_height};
			g.setColor(Color.GRAY);
			Polygon threed = new Polygon (threedX, threedY, threedX.length);
			g.fillPolygon (threed);
		}
		
		if (currentScreen instanceof Game && value == LAND) {
			tile [x][y].draw(g);
			if (tile [x][y].getState () != 0)
				System.out.println(x + " " + y);
		}
		
		if (lifeform != null) {
			//System.out.println(x+" "+y+" "+lifeform);
			lifeform.sink(); // does nothing if glacier is not melting
			lifeform.show(g);
		}
	}
}
