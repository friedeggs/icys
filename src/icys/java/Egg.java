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
	
	public Egg (int index)
	{
		super(index);
	}
	//arbitrary commmnets
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
		//blocks[x][y].setTargeter(this);
	}
	
	public boolean grownUp()
	{
		return (timeAlive == 20);
	}

	@Override
	public void remove() {
		blocks [x][y].set(null);
		ArrayList <Egg> newlist = new ArrayList <Egg> ();
		for (int i = 0 ; i < eggs.size() ; i ++) {
			if (equals(eggs.get(i)))
				eggs.remove(i);
		}
		
		for (int i = 0 ; i < eggs.size () - 1 ; i++) {
				newlist.add(eggs.get(i));
				newlist.get(i).index = i;
				System.out.println (i);
		}
	}
	
}
