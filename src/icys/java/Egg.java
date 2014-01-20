package icys.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Egg extends LifeForm{

	boolean alive;
	int x, y;
	int timeAlive;
	
	public Egg(int index, int x, int y)
	{
		super (index);
		this.x = x;
		this.y = y;
		alive = true;
		timeAlive = 0;
		try {
            image = ImageIO.read(new File ("egg.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void update(ArrayList<Penguin> penGUI, Block[][] iii, Graphics g)
	{
		timeAlive ++;
		if (grownUp())
		{
			remove ();
			Penguin penS = new Penguin (penguins.size(), x, y);
			penGUI.add(penS);
		}
		show(g);
	}
	
	public boolean grownUp()
	{
		return (timeAlive == 20);
	}

	@Override
	public void remove() {
		fish.remove(index);
		for (int i = index ; i < fish.size() ; i++)
			fish.get(i).index--;
	}
	
}
