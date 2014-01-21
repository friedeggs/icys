package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value;
	public LifeForm lifeform, targeter;

	public Block(int value1, int i, int j) {
		value = value1;
		x = i;
		y = j;
	}

	public void set(LifeForm l) {
		lifeform = l;
	}	
	
	public void setTargeter(LifeForm l) {
		targeter = l;
	}
	
	public void update (Graphics g) {
		if (targeter != null) {
			lifeform = targeter;
			targeter = null;
		}
		show (g);
	}
	
	public void changeValue (char x){
		if (x == '0')
			value = Utilities.WATER;
		else if (x =='1')
			value = Utilities.LAND;
		else //if (x =='2')
			value = Utilities.UNUSED;
	}

	public void show(Graphics g) // and char a??
	{
		Color //aqua, //new Color(175, 217, 255), 
				land = new Color(255, 255, 255),
				dark = new Color(11, 23, 59), use;

		if (value != 1) {
			if (value == 3)
				use = dark; // water pollution
			else
				use = water;
		} else
			use = land;
		if (use == land) {
			use = new Color (255-x*10,255-x*10, 255-y*10);
		}

		int [] xPoint = {coordX(x), coordX(x)+block_width, 
				coordX(x)+block_width-shift, coordX(x)-shift};
		int [] yPoint = {coordY(y), coordY(y), 
				coordY(y)+block_height, coordY(y)+block_height};
		g.setColor(use);
		Polygon poly = new Polygon (xPoint, yPoint, xPoint.length);
		g.fillPolygon (poly);
		if (value == 1) {
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
		
		if (lifeform != null) {
			lifeform.show(g);
		}
	}
}
