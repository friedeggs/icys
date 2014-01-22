package icys.java;

import static icys.java.Utilities.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value, nudgeX = 0, nudgeY = 0;
	public LifeForm lifeform, targeter;

	public Block(int value1, int i, int j) {
		value = value1;
		x = i;
		y = j;
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
		if (!(targeter == null && (lifeform instanceof Egg 
				|| lifeform instanceof Fish))) {
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
		else //if (x =='2')
			value = Utilities.UNUSED;
	}

	public void nudgeChange (int i, int j)
	{
		nudgeX = i;
		nudgeY = j;
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
			use = new Color (255-x*3,255, 255-y*3);
		}

		int [] xPoint = {coordX(x) + nudgeX, coordX(x)+block_width + nudgeX, 
				coordX(x)+block_width-shift + nudgeX, coordX(x)-shift + nudgeX};
		int [] yPoint = {coordY(y) + nudgeY, coordY(y) + nudgeY, 
				coordY(y)+block_height + nudgeY, coordY(y)+block_height + nudgeY};
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
			//System.out.println(x+" "+y+" "+lifeform);
			lifeform.sink(); // does nothing if glacier is not melting
			lifeform.show(g);
		}
	}
}
