package icys.java;

//Imports
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Egg extends LifeForm{

	//Initialization
	boolean alive;
	int timeAlive;
	
	//Constructor
	public Egg (int index)
	{
		super(index);
		//IMG of egg
		try {
            image = ImageIO.read(new File ("egggg.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}

	//Modified constructor
	public Egg(int index, int x, int y)
	{
		super (index);
		//set position
		this.x = x;
		this.y = y;
		//set this lifeform on the block it's standing on
		blocks [x][y].set(this);
		alive = true;
		timeAlive = 0;
		try {
            image = ImageIO.read(new File ("egggg.png"));
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	//update the sate of the egg
	public void update(Graphics g)
	{
		timeAlive ++; //increase on time until it "hatches"
		if (grownUp())
		{
			//remove egg and add penguin when "hatch"
			remove ();
			Penguin penS = new Penguin (penguins.size(), x, y);
			penguins.add(penS);
		}
	}
	
	//Sets time for it to grow up
	public boolean grownUp()
	{
		return (timeAlive == 20);
	}

	//Removes the egg
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
