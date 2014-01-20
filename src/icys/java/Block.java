package icys.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import java.awt.Graphics;

public class Block extends Entity {

	public int value, width;
	public LifeForm lifeform;

	public Block(int value1, int i, int j) {
		value = value1;
	}

	public void set(LifeForm l) {
		lifeform = l;
	}

	public void show(Graphics g) // and char a??
	{
		char a = 'a'; // TEMPORARY
		if (lifeform != null) {
			lifeform.show(g);
		}
		Color water = new Color(175, 217, 255), land = new Color(0, 0, 0), dark = new Color(
				11, 23, 59), use;

		if (value != 1) {
			if (a == 'x')
				use = dark;
			else
				use = water;
		} else
			use = land;

		// int [] xPoint = {row*x, row*x+increaseX, row*x - increaseX, row +
		// increaseX};
		// int [] yPoint = {column*y, column*y+increaseY, column*y-increaseY,
		// column - increaseY};
		g.setColor(use);
		// Polygon poly = new Polygon (xPoint, yPoint, xPoint.length);
		// g.drawPolygon (poly);
	}
}
