package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Fish extends LifeForm{
 
	boolean alive;
	int x, y;
	boolean canAppear;
	int timeAppeared;
	BufferedImage fish;
	
	public Fish (int timer)
	{
		x = chooseX();
		y = chooseY();
		canAppear = true;
		timeAppeared = 5;
		try {
            fish = ImageIO.read(new File ("fish.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public int chooseX ()
	{
		return 15;
		//How to choose X coordinate?
	}
	
	public int chooseY ()
	{
		return 15;
		//How to choose Y coordinate? 
	}
	
	public void show(int time, Block[][] block, Graphics g)
	{
		g.drawImage (fish, block[x][y].x, block[x][y].y, null); 
		timeAppeared = time;
	}

}
