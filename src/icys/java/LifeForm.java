package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static icys.java.Utilities.*;

public abstract class LifeForm extends Entity {

	int index;
	BufferedImage image;
	
	public LifeForm (int index) {
		this.index = index;
	}	
	
	public LifeForm (int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	public void checkAlive()
	{
	}
	
	public void move()
	{
	}
	
	public void show(Graphics g)
	{ // DRAW IN MIDDLE OF BLOCK
		g.drawImage (image, coordX(x), coordY(y) - block_height / 2, null);
	}
	
	public Block randomBlock () {
		int x, y;
		do {
			x = (int)(Math.random () * blocks.length);
			y = (int)(Math.random () * blocks[0].length);
		} while (blocks[x][y].lifeform != null || blocks[x][y].value != LAND);
		return blocks [x][y];
	}
	
	public abstract void remove ();
	
}




