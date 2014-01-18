package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Egg extends LifeForm{

	boolean alive;
	int x, y;
	int timeAlive;
	BufferedImage minipencil;
	
	public Egg(int x, int y)
	{
		this.x = x;
		this.y = y;
		alive = true;
		timeAlive = 0;
		try {
            minipencil = ImageIO.read(new File ("eggggggggg.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void update(ArrayList<Penguin> penGUI, Block[][] iii, Graphics g)
	{
		timeAlive ++;
		if (grownUp())
		{
			alive = false;
			Penguin penS = new Penguin (this.x, this.y);
			penGUI.add(penS);
		}
		show(iii, g);
	}
	
	public boolean grownUp()
	{
		if (timeAlive == 20)
			return true; 
		else
			return false;
	}
	
	public void show(Block[][] enviro, Graphics g)
	{
		g.drawImage (minipencil, enviro[x][y].x, enviro[x][y].y, null); 
	}
	
}
