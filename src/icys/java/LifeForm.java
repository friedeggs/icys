package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static icys.java.Utilities.*;

public class LifeForm {

	boolean alive;
	int x, y;
	BufferedImage image;
	
	public void checkAlive()
	{
	}
	
	public void move()
	{
	}
	
	public void show(Graphics g)
	{
		g.drawImage (image, blocks[x][y].x, blocks[x][y].y, null); 
		System.out.println (image);
	}
	
	public Block randomBlock () {
		int x, y;
		do {
			x = (int)(Math.random () * blocks.length);
			y = (int)(Math.random () * blocks.length);
		} while (blocks[x][y].isOccupied || blocks[x][y].value != LAND);
		return blocks [x][y];
	}
	
}




