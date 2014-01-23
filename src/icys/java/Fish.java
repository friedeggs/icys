package icys.java;

//Imports
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static icys.java.Utilities.*;

public class Fish extends LifeForm {
	
	//Initialization
	Block pos;
	
	//Constructor
	public Fish (int index)
	{
		super (index);
		//sets position
		pos = randomBlock ();
		if (pos == null) 
			remove ();
		else {
		x = pos.x; //set x
		y = pos.y; //set y
		pos.set(this);
		try {
            image = ImageIO.read(new File ("fishh.png")); //img file
		} catch (IOException e) {
            e.printStackTrace();
		}
		}
	}
	
	//where will the fish appear next?
	public Block randomBlock () {
		int counter = 0;
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks[i][j].lifeform == null && blocks[i][j].value== LAND
				&& byWater (blocks [i][j]))
					counter++;

		//Make sure the fish appears beside water
		int x = (int)(Math.random () * counter);
		for (int i = 0 ; i < blocks.length ; i++)
			for (int j = 0 ; j < blocks[0].length ; j++)
				if (blocks[i][j].lifeform == null && blocks[i][j].value== LAND
				&& byWater (blocks [i][j]))
				{
					counter--;
					if (x == counter)
						return blocks [i][j];
				}
		return null;
	}
	
	//Beside water, not in the middle of land.
	//it doesn't rain fish here, y'know?
	public boolean byWater (Block block) {
		return (block.x == 0 || block.y == 0 || 
				block.x == blocks.length-1 || block.y == blocks.length-1 ||
				blocks [block.x-1][block.y].value != LAND || 
				blocks [block.x+1][block.y].value != LAND ||
				blocks [block.x][block.y-1].value != LAND || 
				blocks [block.x][block.y+1].value != LAND);
	}	
	
	//if this fish = that fish
	public boolean equals (Fish f) {
		return (f.x == x && f.y == y);
	}

	//remove fish (not by penguin eating)
	@Override
	public void remove() {
		blocks [x][y].set(null);
		ArrayList <Fish> newlist = new ArrayList <Fish> ();
		for (int i = 0 ; i < fish.size() ; i ++) {
			if (equals(fish.get(i)))
				fish.remove(i);
		}
		
		for (int i = 0 ; i < fish.size () - 1 ; i++) {
				newlist.add(fish.get(i));
				newlist.get(i).index = i;
		}
	}

	//GUI
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
