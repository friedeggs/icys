package icys.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value, width = 30, height = 30, shift = 5;
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
		if (lifeform != null) {
			//lifeform.show(g);
		}
		Color water = new Color(175, 217, 255), 
				land = new Color(255, 255, 255),
				dark = new Color(11, 23, 59), use;

		if (value != 1) {
			if (value == 2)
				use = dark;
			else
				use = water;
		} else
			use = land;

		int [] xPoint = {coordX(x), coordX(x)+width, 
				coordX(x)-shift, coordX(x)+width-shift};
		int [] yPoint = {coordY(y), coordY(y), coordY(y)+height, coordY(y)+height};
		g.setColor(use);
		Polygon poly = new Polygon (xPoint, yPoint, xPoint.length);
		g.fillPolygon (poly);
		//g.drawRect(coordX(x), coordY(y), coordX(1)/1, coordY(1)/1);
	}
}
