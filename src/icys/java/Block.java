package icys.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value, width, height;
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

	public void show(Graphics g) // and char a??
	{
		char a = Utilities.value [x][y];
		if (lifeform != null) {
			//lifeform.show(g);
		}
		Color water = new Color(175, 217, 255), land = new Color(0, 0, 0), dark = new Color(
				11, 23, 59), use;

		if (value != 1) {
			if (a == '2')
				use = dark;
			else
				use = water;
		} else
			use = land;

		int [] xPoint = {row*x, row*x+increaseX, row*x - increaseX, row +
		increaseX};
		int [] yPoint = {column*y, column*y+increaseY, column*y-increaseY,
		column - increaseY};
		g.setColor(use);
		Polygon poly = new Polygon (xPoint, yPoint, xPoint.length);
		g.fillPolygon (poly);
		//g.drawRect(coordX(x), coordY(y), coordX(1)/1, coordY(1)/1);
	}
}
