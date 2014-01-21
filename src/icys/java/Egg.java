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
	int timeAlive;
	
	public Egg(int index, int x, int y)
	{
		super (index);
		this.x = x;
		this.y = y;
		blocks [x][y].set(this);
		alive = true;
		timeAlive = 0;
		try {
            image = ImageIO.read(new File ("egggg.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void update(Graphics g)
	{
		timeAlive ++;
		if (grownUp())
		{
			remove ();
			Penguin penS = new Penguin (penguins.size(), x, y);
			penguins.add(penS);
		}
		show(g);
	}
	
	public boolean grownUp()
	{
		return (timeAlive == 20);
	}

	@Override
	public void remove() {
		eggs.remove(index);
		for (int i = index ; i < eggs.size() ; i++)
			eggs.get(i).index--;
	}
	
}
