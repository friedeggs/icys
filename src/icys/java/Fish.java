import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		timeAppeared = null;
		try {
            fish = ImageIO.read(new File ("fysh.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public int chooseX ()
	{
		//How to choose X coordinate?
	}
	
	public int chooseY ()
	{
		//How to choose Y coordinate? 
	}
	
	public void show(int time, Block[][] environment, Graphics g)
	{
		g.drawimage (fish, block[x][y].x, block[x][y].y, null); 
		timeAppeared = time;
	}

}
