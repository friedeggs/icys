package icys.java;

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
	
	public void show()
	{	
	}
	
	public Block randomBlock () {
		int x, y;
		do {
			x = (int)(Math.random () * blocks.length);
			y = (int)(Math.random () * blocks.length);
		} while (blocks[x][y].isOccupied || blocks[x][y].value != 1);
		return blocks [x][y];
	}
	
}




